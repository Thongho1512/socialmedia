1. prerequisite.
install jdk 21, maven 3.9.9, mongodb 8.0, intellij IDEA community edition 2024.2.0.2, docker.

2. technical stack.
- Java 21.
- Maven 3.9+
- Spring boot 3.4.1.
- Actuator.
- MongoDB.
- Lombok.
- Spring data validation.
- Devtool.
- Docker.
- Docker compose.
- Webmvc-ui 2.6.0.
- Spring web.
- websocket reference https://www.unlogged.io/post/getting-started-with-websockets-in-springboot

3. Build & Run application.
- Run application by mvnw ar folder socialmedia.
mvnw spring-boot: run

- Run application by docker.
mvn install -P dev
docker build -t socialmedia:lastest
docker run -it -p 8080:8080 --name socialmedia socialmedia:lastest


