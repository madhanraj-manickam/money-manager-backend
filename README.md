# Money Manager API (Pro)

A Spring Boot REST API for managing personal and office finances.

###  Key Features
- **Account Transfers:Logic to move funds between Office and Personal divisions.
- Security:12-hour edit restriction logic.
- Dockerized: Ready for deployment on Render.
- Database: PostgreSQL integration.

### Tech Stack
- Java 17 / Spring Boot
- PostgreSQL
- Docker

### Local Setup
1. Update `src/main/resources/application.properties` with your DB credentials.
2. Run `./mvnw spring-boot:run`