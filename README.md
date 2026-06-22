# Trace - Secure Notes Management API

A production-oriented backend application built with Spring Boot that enables users to securely create, organize, tag, and manage notes using JWT-based authentication and PostgreSQL persistence.

This project was built independently to gain practical experience in backend development, security, database design, REST API development, containerization, and production-ready application architecture.

---

# Features

## Authentication & Authorization

- User Registration
- User Login
- JWT Token Generation
- JWT Token Validation
- Protected Routes
- Stateless Authentication
- Password Hashing using BCrypt
- Spring Security Integration
- User-specific Data Access

## Notes Management

- Create Notes
- Retrieve Notes
- Update Notes
- Delete Notes
- User Ownership Validation
- Secure CRUD Operations

## Tag Management

- Create Tags
- Attach Multiple Tags to Notes
- Reuse Existing Tags
- Many-to-Many Relationship Handling

## Database Features

- PostgreSQL Integration
- Persistent Data Storage
- Automatic Schema Management
- Entity Relationship Mapping
- JPA Repository Layer

## Dockerization

- Dockerized Spring Boot Backend
- Dockerized PostgreSQL Database
- Multi-Container Setup using Docker Compose
- Environment Variable Configuration
- Container Networking
- Persistent Database Volumes

---

# Tech Stack

## Backend

- Java 21
- Spring Boot 4
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate ORM

## Database

- PostgreSQL

## Authentication

- JWT (JSON Web Tokens)
- BCrypt Password Encoder

## DevOps

- Docker
- Docker Compose

## Build Tool

- Maven

---

# System Architecture

```text
Client
   │
   ▼
REST API
   │
   ▼
Controllers
   │
   ▼
Services
   │
   ▼
Repositories
   │
   ▼
PostgreSQL
```

---

# Authentication Flow

```text
User Login
     │
     ▼
Credential Validation
     │
     ▼
JWT Token Generation
     │
     ▼
Token Sent To Client
     │
     ▼
Authorization Header
     │
     ▼
JWT Filter Validation
     │
     ▼
Protected Endpoint Access
```

---

# Project Structure

```text
src
│
├── controller
├── service
├── repository
├── security
├── entity
├── dto
├── exception
├── config
└── TraceBackendApplication
```

---

# Concepts Applied

## Core Java

- Object-Oriented Programming
- Encapsulation
- Abstraction
- Collections Framework
- Exception Handling
- Generics
- Streams API
- Dependency Injection Principles

## Spring Boot

- REST API Development
- Bean Lifecycle Management
- Configuration Management
- Layered Architecture
- Environment Variables
- Application Properties

## Spring Security

- Authentication
- Authorization
- Security Filter Chain
- JWT Authentication Filter
- Custom UserDetailsService
- Password Encryption
- Stateless Security

## Database Design

- Relational Database Design
- Primary Keys
- Foreign Keys
- Database Normalization
- Data Integrity
- User Data Isolation

## Hibernate & JPA

- ORM Concepts
- Entity Mapping
- Repository Pattern
- Relationship Mapping
- Persistence Context
- Query Generation
- Lazy/Eager Loading

## SQL & PostgreSQL

- CRUD Operations
- Database Schema Design
- Table Relationships
- Query Optimization Basics
- Persistent Storage

## Docker

- Dockerfile Creation
- Image Building
- Container Lifecycle
- Docker Networking
- Docker Volumes
- Multi-Container Applications
- Docker Compose

## Software Engineering

- Layered Architecture
- Service Layer Pattern
- Repository Pattern
- Separation of Concerns
- Clean Code Practices
- API Design Principles
- Secure Credential Management

---

# Challenges Solved

During development, several real-world backend and infrastructure problems were encountered and resolved:

- JWT authentication implementation
- Spring Security configuration issues
- PostgreSQL connection failures
- Hibernate entity relationship mapping
- Docker networking problems
- Environment variable management
- Docker Compose configuration errors
- PostgreSQL container setup issues
- Persistent volume management
- Container restart loops
- Database initialization debugging
- Protected endpoint access control
- User ownership validation

---

# API Endpoints

## Authentication

| Method | Endpoint | Description |
|----------|----------|-------------|
| POST | `/api/auth/register` | Register User |
| POST | `/api/auth/login` | Login User |

## Notes

| Method | Endpoint | Description |
|----------|----------|-------------|
| POST | `/api/notes` | Create Note |
| GET | `/api/notes` | Get All Notes |
| GET | `/api/notes/{id}` | Get Note By ID |
| PUT | `/api/notes/{id}` | Update Note |
| DELETE | `/api/notes/{id}` | Delete Note |

## Tags

| Method | Endpoint | Description |
|----------|----------|-------------|
| POST | `/api/tags` | Create Tag |
| GET | `/api/tags` | Get Tags |

---

# Running Locally

## Clone Repository

```bash
git clone <repository-url>
cd trace-backend
```

## Configure Environment Variables

```env
DB_URL=jdbc:postgresql://localhost:5432/tracedb
DB_USERNAME=postgres
DB_PASSWORD=your_password

JWT_SECRET=your_secret_key
JWT_EXPIRATION=86400000
```

## Run Application

```bash
mvn spring-boot:run
```

---

# Running With Docker

## Build and Start Containers

```bash
docker compose up --build
```

## Run In Detached Mode

```bash
docker compose up -d
```

## View Running Containers

```bash
docker ps
```

## View Logs

```bash
docker compose logs -f
```

## Stop Containers

```bash
docker compose down
```

---

# What I Learned

Through this project I gained practical experience in:

- Building secure REST APIs
- Implementing JWT authentication from scratch
- Configuring Spring Security
- Designing relational databases
- Working with PostgreSQL
- Managing entity relationships using Hibernate
- Building layered backend architectures
- Containerizing applications with Docker
- Managing multiple containers using Docker Compose
- Using environment variables securely
- Debugging production-like backend issues
- Solving database connectivity problems
- Working with persistent storage volumes
- Understanding backend deployment workflows

---

# Future Improvements

- Pagination & Sorting
- Search Functionality
- Swagger/OpenAPI Documentation
- Request Validation Enhancements
- Unit Testing
- Integration Testing
- Role-Based Access Control (RBAC)
- Audit Logging
- Redis Caching
- Email Verification
- Refresh Tokens
- CI/CD Pipeline
- Cloud Deployment

---

# Why This Project Matters

This project goes beyond a basic CRUD application by combining:

- Authentication & Authorization
- Database Design
- Security
- REST APIs
- ORM Frameworks
- Docker Containerization
- Multi-Container Architecture

It reflects the workflow used in real backend applications and demonstrates the ability to design, build, secure, containerize, and troubleshoot a complete backend system independently.

---

# Author

**Nischal Kothari**

Built independently to learn and apply modern backend development practices using Spring Boot, Spring Security, PostgreSQL, JWT Authentication, Docker, and Docker Compose.