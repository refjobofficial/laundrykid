# --------------------------------------------------
# Stage 1: Build the Spring Boot application (Maven)
# --------------------------------------------------
FROM maven:3.9-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copy Maven configuration first (for dependency caching)
COPY pom.xml .

# Download dependencies (cached layer)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src src

# Build the Spring Boot JAR (skip tests for faster build)
RUN mvn clean package -DskipTests

# --------------------------------------------------
# Stage 2: Runtime (lighter image)
# --------------------------------------------------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/laundry-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
