FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
COPY target/cloud-gateway-0.0.1-SNAPSHOT.jar cloud-gateway.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/cloud-gateway.jar"]