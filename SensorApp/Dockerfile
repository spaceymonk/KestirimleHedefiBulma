FROM openjdk:12-alpine
ARG JAR_FILE=target/SensorApp-0.0.1-SNAPSHOT.jar
WORKDIR /opt/sensor-app
COPY src/main/resources ./resources
COPY ${JAR_FILE} app.jar