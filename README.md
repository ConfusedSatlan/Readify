# Bookstore API

The Bookstore API is a RESTful web service designed to facilitate the purchase of books online. It provides a range of endpoints for browsing books and categories, managing a shopping cart, and placing orders. This README guide will help you get started with setting up and using the API.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
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

Before you begin, ensure you have the following prerequisites:

- A working development environment with your preferred programming language and tools.
- Docker installed on your system for running the Bookstore API.

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
**BookStore API using case**
- [Video Description on Vimeo](https://vimeo.com/870006994/6d32681cac?share=copy)
