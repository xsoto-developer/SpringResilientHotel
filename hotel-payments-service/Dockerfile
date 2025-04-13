### `Dockerfile` Multi-Stage con Imagen Alpine
#### Archivo: `hotel-payments-service/Dockerfile`

# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory
WORKDIR /app/hotel-payments-service

# Copy parent POM and module POM
COPY ./pom.xml /app
COPY ./hotel-payments-service/.mvn ./.mvn
COPY ./hotel-payments-service/mvnw .
COPY ./hotel-payments-service/pom.xml .

# Download parent dependencies
RUN ./mvnw dependency:go-offline

# Copy source code
COPY ./hotel-payments-service/src ./src

# Build the module
RUN ./mvnw clean package -DskipTests

# Stage 2: Create the runtime image
#FROM openjdk:17-jdk-alpine
FROM eclipse-temurin:17.0.10_7-jre-alpine

# Set working directory
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/hotel-payments-service/target/hotel-payments-service-0.0.1-SNAPSHOT.jar .

# Expose the port
EXPOSE 8083

# Set environment variables
ENV SPRING_DATA_MONGODB_URI=mongodb://mongo:27017/payments_db
ENV JAVA_OPTS=""

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]