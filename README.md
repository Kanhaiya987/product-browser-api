# Product Browser API

A high-performance REST API built using Spring Boot and PostgreSQL that supports category filtering and cursor-based pagination for efficiently browsing large product datasets.

---

## Features

- 200,000+ Products
- Cursor (Keyset) Pagination
- Category Filtering
- Spring Boot REST API
- PostgreSQL (Neon Database)
- Optimized Database Indexes
- Maven Project

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL (Neon)
- Maven

---

## API Endpoints

### Get Products

```
GET /api/products
```

Example

```
GET /api/products?size=20
```

---

### Filter by Category

```
GET /api/products?category=electronics
```

---

### Cursor Pagination

```
GET /api/products?size=20&cursor=2026-06-25T12:03:32.28452&lastId=198843
```

---

## Project Structure

```
src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── config
```

---

## Database

- PostgreSQL (Neon)
- 200,000 Product Records
- Indexed on:

```
(updated_at DESC, id DESC)

(category, updated_at DESC, id DESC)
```

---

## Run Locally

Clone repository

```
git clone https://github.com/Kanhaiya987/product-browser-api.git
```

Go inside project

```
cd product-browser-api
```

Run application

```
./mvnw spring-boot:run
```

Server

```
http://localhost:8080
```

---

## Author

Kanhaiya Patel