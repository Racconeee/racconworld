
FROM arm64v8/ubuntu:latest


RUN apt-get update && apt-get install -y openjdk-17-jdk

WORKDIR /app
COPY src/main/resources/bootsecurity.p12 /app/bootsecurity.p12

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /app.jar


ENTRYPOINT ["java", "-jar", "/app.jar"]

