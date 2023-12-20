# Onboarding #

## Project Descriptiom
Onboarding is the backend of an onboarding application for new employees at XLU. 
A checklist of onboarding activities is central for the application. 
The purpose of the project is to collect onboarding activities to one place and to streamline the planning of it. 
It is also to modernize the process and to make it more enjoyable.

## Database initialization
The initialization of the database tabels are not made with JPA and the update flag. Its not an proper solution, but
for our own needs it does what we need with minimum complexity. If we need something later we will add Flyway with
database migration to the application.

## Dependencies ##
* Java Development Kit (JDK) version 17

### Set up postgres database locally ###
TBD.

### Connect to production database ###
If you want to start the application locally and connect to the production database you first have to update the
**application-prod.yaml** with the URL, username and password to the database.

When that is updated you will start the app with the following command:
```./mvnw spring-boot:run -Dspring-boot.run.profiles=prod ```

### Start server with H2 locally ### 
Run `./mvnw spring-boot:run` to start the server. Spring Boot will automatically start up an H2 database, connect to it
and fill it with some test data from the **data.sql**.

## Build ##

Run `./mvnw -B package` to build the project.

## Lint ##

Run `./mvnw validate` to execute the linter.

## Unit test ##

Run `./mvnw test` to execute the unit tests.

## Swagger ##

Start server locally and then navigate to:

[Link to swagger UI](http://localhost:8081/swagger-ui/index.html)

