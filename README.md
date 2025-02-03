

# Spring Boot API

API for CRUD functions in Database with Author and Book class in Java.

## Description

This project is a Spring Boot application that provides CRUD operations for managing `Author` and `Book` entities in a database. It uses Spring Data JPA for data access and PostgreSQL as the database.

## Installation

To install and run this project, follow these steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Sundhar22/spring-boot-API.git
   cd spring-boot-API
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

## Usage

After running the application, you can access the API at `http://localhost:8080`.

### Endpoints

- `GET /api/authors` - Retrieve all authors
- `POST /api/authors` - Create a new author
- `GET /api/books` - Retrieve all books
- `POST /api/books` - Create a new book

## Dependencies

The project uses the following dependencies:

- `spring-boot-starter-data-jpa` - Spring Data JPA
- `spring-boot-starter-web` - Spring Web
- `modelmapper` - Object mapping
- `h2` - In-memory database for testing
- `postgresql` - PostgreSQL driver
- `lombok` - Java library to reduce boilerplate code
- `spring-boot-starter-test` - Test starter

## License

This project is licensed under the MIT License.
