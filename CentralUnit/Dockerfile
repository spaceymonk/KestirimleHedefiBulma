FROM openjdk:12-alpine
ARG JAR_FILE=target/CentralUnit-0.0.1-SNAPSHOT.jar
WORKDIR /opt/central-unit
COPY src/main/resources ./resources
COPY ${JAR_FILE} app.jar