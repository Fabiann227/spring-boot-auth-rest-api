
# Auth REST API

This is a simple authentication REST API built with Spring Boot, providing user registration and login functionality using JWT (JSON Web Token).

## Features

- User registration with email and password validation.
- User login with JWT generation.
- JWT-based authentication and authorization for protected endpoints.
- Swagger UI For Documentation

## Prerequisites

- Java 17
- Maven
- MySQL (or any other relational database)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Fabiann227/spring-boot-auth-rest-api.git
```

### 2. Navigate to the project directory:
```bash
cd auth
```

### 3. Configure the Application
#### Database Configuration

spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name

spring.datasource.username=your_db_username

spring.datasource.password=your_db_password

#### JWT Configuration

jwt.secret=your-very-long-secret-key   
jwt.expiration=3600000

### 4. Build the project:
```bash
mvn clean install
```

### 5. Running the Application

To run the application locally, use the following command:

```bash
mvn spring-boot:run
```
## API Reference

#### Swagger-ui

```bash
  GET /swagger-ui/index.html
```

#### User Sign Up

```bash
  POST /auth/register
```

| Parameter | Type |
| :-------- | :------- |
| `username` | `string` |
| `email` | `string` |
| `password` | `string` |

#### User Sign In

```bash
  POST /auth/login
```

| Parameter  | Type     | 
| :--------- | :------- | 
| `username` | `string` |
| `password` | `string` |

#### Response:
```
{
  "token": "your-jwt-token"
}
```
#### Access a Protected Resource

```bash
  GET /user
  Authorization: Bearer your-jwt-token
```

