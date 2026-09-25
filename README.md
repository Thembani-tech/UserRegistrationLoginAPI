# verification code

* WTC-AWCK9NGK

# User Registration & Login API

A simple REST API built with Java and Javalin that provides user registration and login functionality.

This project was created as a learning project to practice **Java, REST APIs, HTTP methods, API testing, and Postman**.

## Technologies Used

* Java
* Javalin
* Maven
* REST API
* JSON
* Postman
* Git & GitHub

## API Endpoints

| Method | Endpoint    | Description                  |
| ------ | ----------- | ---------------------------- |
| GET    | `/`         | Checks if the API is running |
| POST   | `/register` | Registers a new user         |
| POST   | `/login`    | Logs a user in               |

## Running the Application

Clone the repository and open the project in IntelliJ IDEA or another Java IDE.

Run the `Main.java` class.

The API starts on:

```text
http://localhost:7000
```

## API Testing with Postman

The API was manually tested using Postman.

### Health Check

**GET**

```text
http://localhost:7000/
```

Expected response:

```text
200 OK
User Registration & Login API is running!
```

### Register User

**POST**

```text
http://localhost:7000/register
```

Request body:

```json
{
  "username": "testuser",
  "password": "Password123"
}
```

Expected response:

```text
201 Created
```

### Invalid Registration

A registration request without a username or password should return:

```text
400 Bad Request
```

Response:

```text
Username and password are required
```

### Duplicate Username

Trying to register an existing username should return:

```text
409 Conflict
```

Response:

```text
Username already exists
```

### Successful Login

**POST**

```text
http://localhost:7000/login
```

Request body:

```json
{
  "username": "testuser",
  "password": "Password123"
}
```

Expected response:

```text
200 OK
Login successful
```

### Invalid Login

Using an incorrect password or a username that does not exist should return:

```text
401 Unauthorized
```

Response:

```text
Invalid username or password
```

## API Test Scenarios

The following scenarios were tested manually in Postman:

* API health check
* Successful user registration
* Registration with missing password
* Duplicate username registration
* Successful login
* Login with incorrect password
* Login with a username that does not exist

## Project Structure

```text
UserRegistrationLoginAPI
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── them bani
│   │               └── api
│   │                   └── Main.java
│   └── test
├── pom.xml
└── README.md
```

## Future Improvements

Possible future improvements include:

* Add automated API tests
* Add more input validation
* Store users in a database
* Hash passwords instead of storing them directly
* Add authentication tokens
* Add more API endpoints
* Add a Postman test collection
* Add CI/CD testing with GitHub Actions

## Learning Goals

This project demonstrates practical experience with:

* REST API development
* HTTP methods and status codes
* JSON request and response bodies
* API validation
* Positive and negative testing
* Postman
* Maven
* Git and GitHub

## Author

Thembani-tech
