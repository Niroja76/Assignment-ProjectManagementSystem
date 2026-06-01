# Project & Task Management API

A RESTful backend system built with Spring Boot and PostgreSQL for managing projects, tasks, and team members.

## Tech Stack
- Java 21
- Spring Boot 4.0.6
- PostgreSQL
- Lombok
- Spring Data JPA
- SpringDoc OpenAPI (Swagger)

## Prerequisites
- Java 21
- Maven
- PostgreSQL

## Setup Instructions

### 1. Clone the repository
git clone https://github.com/Niroja76/Assignment-ProjectManagementSystem.git

cd ProjectManagement

### 2. Create the database
psql -U postgres -c "CREATE DATABASE project_db;"

### 3. Configure application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/project_db
spring.datasource.username=postgres
spring.datasource.password=your_password

### 4. Run the application
mvn spring-boot:run

## API Documentation
Once running, visit:
http://localhost:8080/swagger-ui.html

## API Endpoints

### Projects
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/projects | Create project |
| GET | /api/projects | Get all projects |
| GET | /api/projects/{id} | Get project by ID |
| PUT | /api/projects/{id} | Update project |
| DELETE | /api/projects/{id} | Delete project |

### Tasks
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/tasks | Create task |
| GET | /api/tasks | Get all tasks |
| GET | /api/tasks/{id} | Get task by ID |
| PUT | /api/tasks/{id} | Update task |
| DELETE | /api/tasks/{id} | Delete task |

### Team Members
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/members | Add member |
| GET | /api/members | Get all members |
| GET | /api/members/{id} | Get member by ID |
| PUT | /api/members/{id} | Update member |
| DELETE | /api/members/{id} | Delete member |

## Response Format
All responses follow this structure:
{
    "success": true,
    "message": "Operation successful",
    "data": { ... }
}

## Database Schema
Tables: projects, tasks, team_members

## Screenshots

Detailed screenshots demonstrating API requests, responses, and Swagger testing are available in the screenshot folders and documentation provided within the project source (src) directory.

## Author

D Niroja Dora

Email: dnirojadora@gmail.com

