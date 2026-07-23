FROM amazoncorretto:21

WORKDIR /app

# Download OpenTelemetry Java agent
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.9.0/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

COPY target/gateway-server-0.0.1-SNAPSHOT.jar gateway-server-0.0.1-SNAPSHOT.jar

EXPOSE 8072

ENTRYPOINT ["java", "-jar", "gateway-server-0.0.1-SNAPSHOT.jar"]
