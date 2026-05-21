# Portfolio API – Spring Boot Showcase for Junior Java Engineer Roles

A production-style Java backend project designed to demonstrate practical skills expected in Junior Java Software Engineer positions. This repository showcases clean architecture, SOLID-aligned design, OOP modeling, validation, exception handling, logging, and automated testing using modern Java and Spring Boot.

## Tech Stack

- **Language:** Java 21
- **Framework:** Spring Boot 3 (Web + Validation)
- **Build Tool:** Maven
- **Testing:** JUnit 5, Mockito, Spring Boot Test, MockMvc
- **API QA Artifact:** Postman Collection
- **Logging:** SLF4J (via Spring Boot logging setup)

## Architecture Overview

This project follows a layered architecture with clear responsibility boundaries:

- **Controller Layer** (`controller`): Handles HTTP requests/responses and delegates business logic.
- **Service Layer** (`service`): Contains business logic and use-case orchestration.
- **Repository Layer** (`repository`): Abstracts persistence concerns (currently in-memory implementation).
- **Model/DTO Layer** (`model`, `dto`): Uses immutable records to represent domain and request payloads.
- **Exception Layer** (`exception`): Centralized global exception handling for robust API behavior.

### Design Choices & Principles

- **Single Responsibility Principle:** each class has a narrowly defined role.
- **Dependency Inversion:** service depends on repository abstraction, not concrete implementation.
- **Immutability:** Java `record` types used for safer data handling.
- **Validation-first APIs:** request payloads validated with Jakarta Bean Validation.
- **Observability:** logging added for service operations.

## Project Structure

```text
.
├── pom.xml
├── postman/
│   └── portfolio-api.postman_collection.json
└── src/
    ├── main/
    │   ├── java/com/portfolio/api/
    │   │   ├── controller/
    │   │   ├── dto/
    │   │   ├── exception/
    │   │   ├── model/
    │   │   ├── repository/
    │   │   ├── service/
    │   │   └── PortfolioApplication.java
    │   └── resources/
    │       ├── application.yml
    │       └── static/
    │           ├── index.html
    │           └── Photo.jpeg
    └── test/
        └── java/com/portfolio/api/
            ├── controller/
            └── service/
```

## Setup & Installation

### Prerequisites

- Java 21+
- Maven 3.9+

### Steps

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd My-Portfolio-Website
   ```

2. Build and run tests:
   ```bash
   mvn clean test
   ```

3. Start the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access endpoints at:
   ```text
   http://localhost:8080
   ```

## API Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/api/projects` | Retrieve all projects | None |
| GET | `/api/projects/{id}` | Retrieve one project by ID | None |
| POST | `/api/projects` | Create a new project | JSON payload with name, description, repositoryUrl, skills |

### Example `POST /api/projects` payload

```json
{
  "name": "My Portfolio API",
  "description": "Spring Boot backend showcasing OOP and testing",
  "repositoryUrl": "https://github.com/your-user/your-repo",
  "skills": ["Java", "Spring Boot", "JUnit 5"]
}
```

## Testing & QA Demonstration

- **Unit Test:** `DefaultProjectServiceTest` (Mockito-backed business logic testing)
- **Integration Test:** `ProjectControllerIntegrationTest` (MockMvc endpoint contract testing)
- **Manual/API QA:** Import `postman/portfolio-api.postman_collection.json` into Postman

## Next Improvement Ideas

- Replace in-memory repository with Spring Data JPA + PostgreSQL.
- Add pagination/filtering for projects.
- Add CI pipeline (GitHub Actions) with build + test gates.
