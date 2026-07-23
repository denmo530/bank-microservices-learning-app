# We use the amazon coretto 21 base image
FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Download OpenTelemetry Java agent
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.9.0/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

# Copy the jar into the docker container
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]