# We use the amazon coretto 21 base image
FROM amazoncorretto:21

# Set the working directory
WORKDIR /app

# Copy the jar into the docker container
COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 8080

# Execute the application
ENTRYPOINT ["java", "-jar", "loans-0.0.1-SNAPSHOT.jar"]