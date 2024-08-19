package com.ebdesk.auth.service;

import com.ebdesk.auth.exception.ErrorException;
import com.ebdesk.auth.model.User;
import com.ebdesk.auth.repository.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.regex.Pattern;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public void registerUser(String username, String password, String email) {
        if (userRepository.existsByUsername(username) || userRepository.existsByEmail(email)) {
            throw new ErrorException("Username or email address already in use", HttpStatus.CONFLICT); // 409 Conflict
        }
        if (!isValidEmail(email)) {
            throw new ErrorException("Invalid email address", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        userRepository.save(user);
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ErrorException("User Not Found", HttpStatus.UNAUTHORIZED));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ErrorException("Incorrect password", HttpStatus.UNAUTHORIZED);
        }
        return generateToken(user);
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, "yourverylongsecretkeythatisatleast64byteslong")
                .compact();
    }
}
