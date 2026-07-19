FROM amazoncorretto:21

WORKDIR /app

COPY target/gateway-server-0.0.1-SNAPSHOT.jar gateway-server-0.0.1-SNAPSHOT.jar

EXPOSE 8072

ENTRYPOINT ["java", "-jar", "gateway-server-0.0.1-SNAPSHOT.jar"]
