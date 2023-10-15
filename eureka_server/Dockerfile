FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
COPY target/eureka_server-0.0.1-SNAPSHOT.jar eureka_server.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/eureka_server.jar"]