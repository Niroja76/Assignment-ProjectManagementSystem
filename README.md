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
git clone https://github.com/Niroja76/ProjectManagement.git
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

### Create Project
![Create Project](screenshots/project/createProject.png)

### Get All Projects
![Get All Projects](screenshots/project/getAllProject.png)

### Get Projects
![Get Projects](screenshots/project/getProject.png)

### Delete Projects
![Delete Projects](screenshots/project/deleteProject.png)

### Update Projects
![Update Projects](screenshots/project/updateProject.png)

### Create Task
![Create Task](screenshots/project/createTask.png)

### Get All Task
![Get All Task](screenshots/project/getAllTask.png)

### Get Task
![Get Task](screenshots/project/getTask.png)

### Delete Task
![Delete Task](screenshots/project/deleteTask.png)

### Update Task
![Update Task](screenshots/project/updateTask.png)

### Create Team Member
![Create Team Member](screenshots/team-member/createMember.png)

### Get All Team Member
![Get All Team Member](screenshots/team-member/getAllMember.png)

### Get Team Member
![Get Team Member](screenshots/team-member/getMember.png)

### Delete Team Member
![Delete Team Member](screenshots/team-member/deleteMember.png)

### Update Team Member
![Update Team Member](screenshots/team-member/updateMember.png)
