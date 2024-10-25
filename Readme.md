# Business Domain: Real Estate Listing Management System

The Real Estate Listing Management System is a Spring Boot-based web application designed to manage agents, properties and potential buyers.
## Table of Contents
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Error Handling](#error-handling)
- [Contributing](#contributing)

## Tech Stack
- **Java 17**
- **Spring Boot** 3.x
- **Maven** (for dependency management)
- **PostgreSQL** (for production database)
- **H2 Database** (for in-memory testing and development)
- **Spring Data JPA** (for repository pattern and data access)
- **Hibernate** (ORM)
- **MockMVC** (for integration tests)
- **JUnit 5** (for unit tests)
- **Mockito** (for mocking dependencies in tests)
- **Postman** (for API testing)

## Features
CRUD Operations:
- Manage agents games.
- Manage buyers.
- Manage properties.
- Buyers can add properties to favorites.

Dynamic Search:
- Dynamic search for properties.

Input Validation:
- Validates incoming data.

Error Handling:
- Centralized exception handling with custom error messages and proper HTTP status codes.

## Project Structure
```bash
src/
├── main/
│   ├── java/com/github/alina/repl
│   │   ├── controllers/        # REST Controllers
│   │   ├── exceptions/         # Exception handling
│   │   ├── models/             # DTOs and Entity classes
│   │   ├── repositories/       # Data Repositories
│   │   └── services/           # Service implementations
│   └── resources/
│       ├── application.properties  # Configuration
└── test/                           # Unit and integration tests
```

## Running the Application
Run the application using Maven:
```bash
mvn spring-boot:run
```

Alternatively, run the JAR file:
```bash
java -jar target/Board-Game-Cafe-Reservation-System.jar
```
The app will be accessible at: http://localhost:8080

## API Endpoints
The system offers the following functionalities:
### Agents Endpoints
- GET /api/agents/{id} - Get agent by id.
- POST /api/agents - Create a new agent.
- PUT /api/agents/{id} - Update an agent.
- POST /api/agents/{id}/properties - Create a property.
- 
### Buyer Endpoints
- GET /api/buyer/{id} - Get buyer.
- POST /api/buyer - Create a new buyer.
- PUT /api/buyer/{id} - Update a buyer.
- PATCH /api/buyer/{id} - Add property to favorites.

### Property Endpoints
- GET /api/properties - Search properties with optional filters 

## Testing
Run unit and integration tests using:
```bash
mvn test
```
- MockMVC for REST API tests.
- JUnit 5 for unit tests.
- Mockito for mocking services.

## Error Handling
All exceptions are centrally handled using @ControllerAdvice.

- 404 Not Found for missing resources (agents, buyers, etc.).
- 400 Bad Request for invalid data input.

## Contributing
Fork the repository and submit a pull request. For major changes, open an issue to discuss what you'd like to change.



