# Trace - Secure Notes Management API

A production-oriented backend application built with Spring Boot that enables users to securely create, organize, and manage notes using JWT-based authentication and PostgreSQL persistence.

This project was developed independently to gain hands-on experience with backend engineering concepts including authentication, authorization, database design, REST API development, security, and containerization.

---

## Features

### Authentication & Security
- User Registration
- User Login
- JWT Token Generation
- JWT Authentication Filter
- Protected API Endpoints
- Password Encryption using BCrypt
- Spring Security Integration
- Stateless Authentication

### Notes Management
- Create Notes
- Update Notes
- Delete Notes
- View Notes
- User-specific Note Ownership
- Secure Access Control

### Tag Management
- Create Tags
- Associate Multiple Tags with Notes
- Retrieve Notes by Tags
- Many-to-Many Relationship Handling

### Database Features
- PostgreSQL Integration
- Entity Relationships
- Automatic Table Creation
- Data Persistence
- Repository Layer using Spring Data JPA

### Containerization
- Dockerized Spring Boot Application
- Dockerized PostgreSQL Database
- Docker Compose Setup
- Environment Variable Configuration
- Container Networking
- Persistent Database Volumes

---

## Tech Stack

### Backend
- Java 21
- Spring Boot 4
- Spring MVC
- Spring Security
- Spring Data JPA
- Hibernate ORM

### Database
- PostgreSQL

### Authentication
- JWT (JSON Web Tokens)
- BCrypt Password Hashing

### DevOps
- Docker
- Docker Compose

### Build Tool
- Maven
