# Chatop — Backend API (P3)

Backend API for an apartment rental application (Chatop), built with **Spring Boot 4.0.8**, **Java 17**, **Spring Security + JWT**, **Spring Data JPA (Hibernate)** and **MySQL**.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Profiles & Configuration](#profiles--configuration)
- [Database](#database)
- [Running the Application](#running-the-application)
- [Swagger Documentation](#swagger-documentation)
- [Endpoints](#endpoints)
- [Authentication](#authentication)
- [Project Structure](#project-structure)
- [Useful Commands](#useful-commands)

## Prerequisites
- JDK 17+
- Maven (or use the provided wrapper `./mvnw`)
- MySQL 8+

## Installation

1. Create the database:
   ```bash
   mysql -u root -p -e "CREATE DATABASE chatop_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
   ```

2. Create the schema (tables `users`, `rentals`, `messages`):
   ```bash
   mysql -u root -p chatop_db < sql/script.sql
   ```

3. Copy the local configuration template:
   ```bash
   cp src/main/resources/application-dev.properties.example \
      src/main/resources/application-dev.properties
   ```

4. Fill in the MySQL credentials and a JWT secret (base64, at least 32 bytes) in `application-dev.properties`.

## Profiles & Configuration

| Profile | File | Usage |
|---|---|---|
| `dev` (default) | `application-dev.properties` | Local DB + JWT secret (git-ignored) |
| `prod` | `application-prod.properties` | Config via env vars `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`; `ddl-auto=none` |

## Database
Tables: `users`, `rentals`, `messages` (schema in `sql/script.sql`). Uploaded pictures are stored in `src/main/resources/static/images/` (git-ignored).

## Running the Application
```bash
./mvnw spring-boot:run
```
The API listens on `http://localhost:8080`.

## Swagger Documentation
- Swagger UI: **http://localhost:8080/swagger-ui.html**
- OpenAPI spec: **http://localhost:8080/v3/api-docs**

## Endpoints

| Method | URL | Description | Auth |
|---|---|---|---|
| POST | `/api/auth/register` | Create an account and obtain a JWT | No |
| POST | `/api/auth/login` | Log in and obtain a JWT | No |
| GET | `/api/auth/me` | Profile of the authenticated user | Yes |
| GET | `/api/user/{id}` | Get a user by id | Yes |
| GET | `/api/rentals` | List all rentals | Yes |
| GET | `/api/rentals/{id}` | Get a rental by id | Yes |
| POST | `/api/rentals` | Create a rental (multipart) | Yes |
| PUT | `/api/rentals/{id}` | Update a rental (owner only) | Yes |
| POST | `/api/messages` | Send a message to a rental owner | Yes |

## Authentication
JWT (Bearer). After `register`/`login`, send the header `Authorization: Bearer <token>`. The sender of a message is always the authenticated user (`user_id` is not part of the message body).

## Project Structure
- `controller/` — HTTP entry points
- `service/` — business logic
- `dto/` — API exchange objects
- `security/` — JWT, filters, 401/403 handlers
- `config/` — security, OpenAPI, static resources
- `handler/` — centralized error handling
- `sql/script.sql` — database schema

## Useful Commands
| Command | Purpose |
|---|---|
| `./mvnw compile` | Compile the project |
| `./mvnw package` | Build the jar |