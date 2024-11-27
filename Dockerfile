# Use a lightweight base image with JDK
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application's JAR file into the container
COPY target/MistralAi-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application runs on
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
