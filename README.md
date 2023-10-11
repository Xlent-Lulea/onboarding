# Onboarding #

## Project Descriptiom
Onboarding is the backend of an onboarding application for new employees at XLU. 
A checklist of onboarding activities is central for the application. 
The purpose of the project is to collect onboarding activities to one place and to streamline the planning of it. 
It is also to modernize the process and to make it more enjoyable.

## Dependencies ##
* Java Development Kit (JDK) version 17

## Development Server ##

### Set up H2 database locally ###

1. Remove scope = test from artifactId = h2 in pom.xml and 
make sure the version number matches your locally installed version of h2
2. Replace contents of application.yaml with the contents of h2settings.yaml
3. Start server to create tables of the database and then close server
4. [Download](https://www.h2database.com) and connect to H2 database. 
Make sure JDBC URL points to test at the root of this project
5. Run the queries of src/main/resources/dbscript.sql
6. Disconnect from the database before starting the server

### Set up postgres database locally ### 

TBD

### Connect to production database ### 

TBD

### Start server ### 

Run `./mvnw spring-boot:run` to start the server


## Build ##

Run `./mvnw -B package` to build the project.

## Lint ##

Run `./mvnw validate` to execute the linter.

## Unit test ##

Run `./mvnw test` to execute the unit tests.

## Swagger ##

Start server locally and then navigate to:

[Link to swagger UI](http://localhost:8081/swagger-ui/index.html)

