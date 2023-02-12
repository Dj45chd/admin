# Spring Boot API to get user information with their related posts to Blog Admin

Spring boot API to fetch user information with their related posts to Blog Admin. 
This Blog application already has some APIs created that we are reusing.

## Technology used 

1. Java 

2. Maven

3. Spring-boot

4. RestTemplate

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/Dj45chd/admin.git
```

**2. Build and run the app using maven**

```bash
mvn package
java -jar target/admin-1.0.0.jar
```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following APIs.
 
    GET /users/{userId}

You can test them using postman or using curl command.

```
curl http://localhost:8080/users/1
```