# Bookstore API

The Bookstore API is a RESTful web service designed to facilitate the purchase of books online. It provides a range of endpoints for browsing books and categories, managing a shopping cart, and placing orders. This README guide will help you get started with setting up and using the API.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Tech stack](#tech-stack)
- [API Endpoints](#api-endpoints)
  - [Viewing Books](#viewing-books)
  - [Viewing Categories](#viewing-categories)
  - [Managing Shopping Cart](#managing-shopping-cart)
  - [Placing an Order](#placing-an-order)
- [Docker Setup](#docker-setup)
- [.env Configuration](#env-configuration)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

### Prerequisites

Before you begin, make sure you have the following prerequisites in place:

- **Development Environment:** Ensure that you have a working development environment set up for the Java programming language.

- **Integrated Development Environment (IDE):** You should have a preferred IDE, such as IntelliJ IDEA, for coding, debugging, and project management.

- **Docker:** Proficiency in using Docker for containerization to package and run your application.

- **Spring Boot:** Familiarity with Spring Boot to create web services and APIs efficiently.

- **Swagger:** Knowledge of using Swagger to document your API endpoints and functionality.

- **Security:** Understanding and implementing necessary security considerations for web application development.

This project focuses on building a web API in Java using Spring Boot and containerizing it with Docker. The API's endpoints and functionality are documented using Swagger, and security measures are essential components of this web application.

**Local Development Environment:** You can run your application on your local development machine without Docker. In this case, you might need to set up a local database, provide the necessary environment variables from the `.env` file, and run the application using your preferred development tools.

### Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/ConfusedSatlan/online-book-store.git
Navigate to the project directory:

bash
Copy code
cd bookstore-api
Build and run the Docker containers:

bash
Copy code
docker-compose up --build
The API should now be accessible at http://localhost:your-port (replace your-port with the actual port number).

## Tech stack

Here's a brief high-level overview of the tech stack the **BookStore API** uses:

- [Spring Boot](https://spring.io/projects/spring-boot): provides a set of pre-built templates and conventions for creating stand-alone, production-grade Spring-based applications.
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html): provides features like authentication, authorization, and protection against common security threats.
- [Spring Web](https://spring.io/projects/spring-ws#overview): includes tools for handling HTTP requests, managing sessions, and processing web-related tasks.
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/): provides a higher-level abstraction for working with databases and includes support for JPA (Java Persistence API).
- [Hibernate](https://hibernate.org/): simplifies the interaction between Java applications and databases by mapping Java objects to database tables and vice versa.
- [Lombok](https://projectlombok.org/): helps reduce boilerplate code by automatically generating common code constructs (like getters, setters, constructors, etc.) during compile time.
- [Mapstruct](https://mapstruct.org/): generates mapping code based on annotations, reducing the need for manual, error-prone mapping code.
- [Liquibase](https://www.liquibase.org/): helps manage database schema changes over time, making it easier to track and deploy database updates.
- [Swagger](https://swagger.io/): provides a framework for generating interactive API documentation, allowing developers to understand, test, and use APIs more easily.
- [Docker](https://www.docker.com/): provides a consistent and reproducible way to deploy applications across different environments.

## API Endpoints

The Bookstore API exposes the following endpoints:

- **GET /api/books**: Retrieve a list of available books.
- **GET /api/categories**: Browse books by category.
- **GET /api/cart**: Retrieve the contents of the shopping cart.
- **POST /api/cart**: Add books to the shopping cart.
- **DELETE /api/cart**: Remove books from the shopping cart.
- **POST /api/orders**: Place an order for the selected books.

For detailed usage examples and request/response formats, refer to the API documentation.

## Docker Setup

The Bookstore API is containerized using Docker for ease of deployment. You can use the provided `docker-compose.yml` file to run the API and its dependencies, such as the PostgreSQL database.

To start the Docker containers, run:

```bash
docker-compose up --build
```
## .env Configuration

Configuration options for the Bookstore API are stored in the `.env` file. You can customize settings such as production and debugging ports by editing this file.

### Example .env file:

```env
# Info for DB
POSTGRES_USER=postgres
POSTGRES_PASSWORD=password
POSTGRES_DATABASE=book-store
POSTGRES_LOCAL_PORT=5434
POSTGRES_DOCKER_PORT=5432

# Docker port to connect production port
SPRING_DOCKER_PORT:8080

# Production port for the API
SPRING_LOCAL_PORT:8088

# Debugging port for the API
DEBUG_PORT=5005
```
## 🧑Contribution

We welcome contributions from the community to enhance the Bookstore project. Whether you want to fix a bug, improve an existing feature, or propose a new one, your contributions are valuable to us.

**BookStore API using case**
- [Video Description on Vimeo](https://vimeo.com/870006994/6d32681cac?share=copy)
