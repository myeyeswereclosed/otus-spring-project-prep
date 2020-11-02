FROM maven:3.6.0-jdk-11-slim AS build
MAINTAINER myeyeswereclosed
COPY src /home/authorization_server/src
COPY pom.xml /home/authorization_server
RUN mvn -f /home/authorization_server/pom.xml clean package

FROM openjdk:11-jdk-slim
COPY --from=build /home/authorization_server/target/AuthServer.jar /authorization_server/AuthServer.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/authorization_server/AuthServer.jar"]
