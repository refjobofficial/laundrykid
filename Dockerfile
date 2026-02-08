# Stage 1: Build the Spring Boot application
# Uses a JDK image from Eclipse Temurin for compilation.
FROM eclipse-temurin:21-jdk AS builder

# Set the working directory inside the container for the build stage.
WORKDIR /app
# Copy the Gradle wrapper and its directory.
# This allows you to use the Gradle wrapper (gradlew) inside the container.
COPY gradlew .
COPY gradle gradle
# Copy the build configuration files.
# build.gradle: Main build script.
# settings.gradle: Defines multi-project builds if applicable.
COPY build.gradle settings.gradle .
# Copy the source code.
# The `src` directory contains your Java source files, resources, etc.
COPY src src
# Make the Gradle wrapper script executable.
RUN chmod +x gradlew

RUN ./gradlew bootJar -x test

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=builder /app/build/libs//laundry-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]