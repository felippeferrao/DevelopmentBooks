# DevelopmentBooks - Backend Docs
Development Books - Full Solution API


# **Getting Started**

The following project is deployed with the following features:

- Java 11
- Spring Boot 2.7
- Spring Security with Bearer token
- Spring Data to persistence the data with H2 as database.
- Spring Fox with Swagger to generate the API documentation
- Docker as a container

# **Reference Documentation**

For reference, please follow the next steps:

1. Clone the project from GIT: [https://github.com/felippeferrao/DevelopmentBooks.git](https://github.com/felippeferrao/DevelopmentBooks.git)
2. In Eclipse IDE, import the project as Maven project.
3. Build the applucation

## Two scripts were created to build the Docker image and create the container.

1. Access the project directory.

3. Run shell-script 'build.sh':

       sh build.sh

3. Run the shell-script 'run.sh':

       sh run.sh

# H2 Database

This REST API is currently using an H2 database called **testdb** so you can run it quickly and out-of-the-box without much configuration.

The script is located in the resources directory: /main/resources/data.sql

## H2 Console

1. Make sure the project is running
2. Go to URL: http://localhost:8080/h2-console
3. Login:

- Driver Class: org.h2.driver
- JDBC URL: jdbc:h2:mem:testdb
- User Name: sa
- Password: library

# Generated Swagger Documentation

The application was configured as a Spring Boot to integrate swagger2. In Spring Boot, a REST API was exposed. The documentation of REST services is very important and should help consumers of the service to know which services are available, such as authentication. In addition, it is a simple way to test whether the service is active.

1. Make sure the project is running
2. Go to URL: http://localhost:8090/swagger-ui.html

# Postman API Client

To send requests and view responses, test the endpoints using the collection located in the project: / main / resources / postman_colection

1. Make sure the project is running
2. Open Postman API Client an import the file **LIBRARY.postman_collection.json**

# Spring Security with Bearer token

1. Make sure the project is running
2. Make a POST request to `http://localhost:8080/auth` with the default admin user we programatically created to get a valid JWT token:

       Request Body:

       {
           "email": "sa@email.com",
           "password": "123456"
       }

       Response body:

       {
           "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYSIsInJvbGVzIjpbXSwiZXhwIjoxNzA4Njc4NzM2LCJpYXQiOjE2MjIyNzg3MzZ9.TFVjHPzH10Frn3k3QZmJs56EbRAega55oPI-sH7pMHA"
       }

3. Add the JWT token as a Bearer token parameter and make the initial request to rest of endpoints.

