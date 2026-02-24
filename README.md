LightLife

LightLife is a backend application developed to manage users’ diet and healthy lifestyle processes.
The system provides secure user authentication, diet plan management, activity tracking, and role-based operations for users and dietitians.

This project was developed using Spring Boot to practice scalable backend architecture and modern authentication mechanisms.

Project Scope

The application allows:

User registration and authentication

Diet plan management

Activity tracking

Role-based authorization

Notification and request management

Technologies

Java 17

Spring Boot

Spring Security

JWT Authentication

Spring Data JPA

RESTful API

Maven

Architecture

The project follows a layered architecture structure:

src
 ├── controller
 ├── service
 ├── repository
 ├── entity
 ├── dto
 └── security

Business logic, data access, and API layers are separated to ensure maintainability and scalability.

Security

Authentication and authorization processes are implemented using Spring Security and JWT-based authentication.

Role-based access control is applied across protected endpoints.

Setup

Clone the repository:

git clone https://github.com/USERNAME/lightlife.git

Navigate into the project directory:

cd lightlife

Run the application:

mvn spring-boot:run

The application runs on:

http://localhost:8080

Learning Outcomes

During development, the following concepts were practiced:

REST API development

Backend architecture design

Authentication and authorization

Layered system design

Spring ecosystem usage

Author

Nisa Yakar

Mehmet Akif Güneş
