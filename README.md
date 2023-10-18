<h1 align="center">RESTful Service Book&Author</h1>

## Description
REST API project with the following features:
- Get all books (filter to sort by name, serial number, filter by shelf availability)
- Get all books by the same author
- Add a book
- Add Author
- Edit Book
- Edit author data
- Delete book from shelf

At the first launch, the service independently creates the necessary objects in the database using _Liquibase_.

## Technology stack.
- Java 11
- Spring Boot
- PostgreSQL
- Liquibase
- Hibernate

## Project setup.
```bash
# Clone repository
git clone git@github.com:vnj64/springboot-bookshop.git

# Move to the root.
cd springboot-bookshop

# Create docker container with PostgreSQL
docker run --name {container_name} -e POSTGRES_USER={postgres_user} -e POSTGRES_PASSWORD={postgres_password} -p {port}:5432 -d postgres

# Set environment variables on application.properties
spring.datasource.url=jdbc:postgresql://localhost:{port}/{postgres_user}
spring.datasource.username={postgres_user}
spring.datasource.password={postgres_password}

# Run the maven collector.
./mvnw spring-boot:run
or
run BookShopApplication from your IDE.
```

## API routes.
[Postman Collection](https://it204room.postman.co/workspace/Team-Workspace~35b8e074-85e2-4243-84fc-2533e273fe5e/collection/18367205-994bcee2-b92f-4dfa-9697-c34e37df0206?action=share&creator=18367205)
```http request
GET localhost:3030/api/books/all
```
```http request
POST localhost:3030/api/books/create
```
```http request
GET localhost:3030/api/books/byAuthor/{authorId}
```
```http request
DELETE localhost:3030/api/books/delete/{bookId}
```
```http request
POST localhost:3030/api/authors/create
```
```http request
GET localhost:3030/api/authors/all
```
```http request
GET localhost:3030/api/authors/all
```
```http request
PUT localhost:3030/api/books/update/{bookId}
```
```http request
PUT localhost:3030/api/authors/update/{authorId}
```
**Good luck ✅!**