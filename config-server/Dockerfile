FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the jar into the docker container
COPY target/config-server-0.0.7-SNAPSHOT.jar config-server-0.0.7-SNAPSHOT.jar

# Expose the application port
EXPOSE 8100

# Execute the application
ENTRYPOINT ["java", "-jar", "config-server-0.0.7-SNAPSHOT.jar"]