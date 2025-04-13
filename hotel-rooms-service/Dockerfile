### `Dockerfile` Multi-Stage con Imagen Alpine
#### Archivo: `hotel-reservations-service/Dockerfile`

# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app/hotel-rooms-service

# Copy parent POM and module POM
COPY ./pom.xml /app
COPY ./hotel-rooms-service/.mvn ./.mvn
COPY ./hotel-rooms-service/mvnw .
COPY ./hotel-rooms-service/pom.xml .

# Download parent dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY ./hotel-rooms-service/src ./src

# Build the module
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
#FROM openjdk:17-jdk-alpine
FROM eclipse-temurin:17.0.10_7-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/hotel-rooms-service/target/hotel-rooms-service-0.0.1-SNAPSHOT.jar .

# Expose the port
EXPOSE 8081

# Set environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/rooms_db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=password
ENV JAVA_OPTS=""

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]