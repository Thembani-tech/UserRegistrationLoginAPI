# User Registration & Login API

A simple REST API built with Java and Javalin that provides user registration and login functionality.

This project was created as a learning project to practice **Java, REST APIs, HTTP methods, manual API testing, and automated test automation**.

## Technologies Used

* Java
* Javalin
* Maven
* REST API
* JSON
* Postman
* JUnit 5
* RestAssured
* AssertJ
* Git & GitHub

## API Endpoints

| Method | Endpoint    | Description                  |
| ------ | ----------- | ----------------------------- |
| GET    | `/`         | Checks if the API is running |
| POST   | `/register` | Registers a new user         |
| POST   | `/login`    | Logs a user in                |

## Running the Application

Clone the repository and open the project in IntelliJ IDEA or another Java IDE.

Run the `Main.java` class.

The API starts on:

```text
http://localhost:7000
```

## Automated Tests

The project includes an automated test suite (`UserApiTests.java`) built with **JUnit 5** and **RestAssured**, covering both the registration and login endpoints.

Run all tests with:

```text
mvn test
```

The tests spin up a real instance of the API on a random free port, send real HTTP requests using the JSON files in the project root as request bodies, and assert on the actual HTTP status codes returned — no mocking of the server itself.

### Test coverage

* Registering a new user succeeds (`201`)
* Registering a duplicate username fails (`409`)
* Registering without a username fails (`400`)
* Registering without a password fails (`400`)
* Logging in with correct credentials succeeds (`200`)
* Logging in with the wrong password fails (`401`)
* Logging in as an unknown user fails (`401`)

## Manual API Testing with Postman

Before automating the tests above, the API was first manually tested and verified using Postman to confirm expected behaviour for each scenario.

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

## Project Structure

```text
UserRegistrationLoginAPI
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── thembani
│   │               └── api
│   │                   ├── Main.java
│   │                   └── model
│   │                       └── User.java
│   └── test
│       └── java
│           └── com
│               └── thembani
│                   └── api
│                       └── UserApiTests.java
├── register-request.json
├── register-duplicate.json
├── register-missing-username.json
├── register-missing-password.json
├── login-wrong-password.json
├── login-unknown-user.json
├── login-missing-username.json
├── login-missing-password.json
├── pom.xml
└── README.md
```


## Future Improvements

Possible future improvements include:

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
* Manual testing with Postman
* Automated testing with JUnit 5 and RestAssured
* Maven
* Git and GitHub

## Author

Thembani-tech
