# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml /app
COPY src /app/src

# Build the application
RUN ./mvnw clean install

# Expose the port the application runs on
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]