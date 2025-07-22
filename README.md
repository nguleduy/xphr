# XPHR Assignment

## 1. Database Query Optimization
## 2. JSP-Based Reporting Page

The XPHR API contains following components:

| Component  | URL                                         | Description                | Language                                                                              |
|------------|---------------------------------------------|----------------------------|---------------------------------------------------------------------------------------|
| API        | http://localhost:8080                       | Electronic Store API.      | Java, Spring, JPA, Flyway, Lombok, Log4j2, Swagger, JUnit, Mokito, Checkstyle, Jacoco |
| Database   |                                             | Electronic Store Database. | Postgres                                                                              |
| Swagger UI | http://localhost:8080/swagger-ui/index.html | API documentation.         | HTML                                                                                  |

## 2. Directory structure

```plaintext
   project-root/
   ├── codequality                                      # Checkstyle configuration
   ├── gradle                                           # Gradle wrapper, manage library versions
   ├── libs                                             # external libraries
   └── src/
      ├── main/java/com/example/xphr/                 # Spring Boot application source files
      └── main/resources/
         ├── db/migration/                              # Flyway migration scripts
         ├── postman                                    # Postman collection
         └── application.properties                     # Spring Boot application configuration
         └── log4j2.xml                                 # Log4j2 configuration
      ├── main/webapp/                                  # JSP files
   ├── build.gradle                                     # Gradle build script
   ├── Dockerfile                                       # Dockerfile
   ├── docker-compose.yml                               # Docker Compose configuration
   └── README.md                                        # Project documentation
   ```

## 3. Installation

### 3.1. Prerequisites

- JDK >= 17
- Docker
- Gradle >= 7.5

### 3.2. Build

#### 3.2.1. Start project

```shell
  docker compose up --build -d
```

#### 3.2.2. Stop project

```shell
  docker compose down
```

## 4. Endpoint
Once the service is up, the following endpoint will be available:

| Endpoints         | Method | Type    | Description                        |
|-------------------|--------|---------|------------------------------------|
| /api/v1/report    | GET    | Private | To get system reports by user role |


- `Private` endpoints can only be accessed with a Header `X-USER-ID`.
- Based on Header `X-USER-ID`, the role of the user will be determined.
  - Default Admin role: `admin`
  - Default Employee roles: `ronaldo`, `messi`, `neymar`
- Example CURL request for Admin:
```shell
  curl --location 'http://localhost:8081/api/v1/report?startDate=2025-07-01T00%3A00%3A00&endDate=2025-07-30T23%3A59%3A59' \
--header 'X-USER-ID: admin'
```
- Example CURL request for Employee:
```shell
  curl --location 'http://localhost:8081/api/v1/report?startDate=2025-07-01T00%3A00%3A00&endDate=2025-07-30T23%3A59%3A59' \
--header 'X-USER-ID: ronaldo'
```