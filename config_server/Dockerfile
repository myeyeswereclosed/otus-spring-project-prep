FROM maven:3.6.0-jdk-11-slim AS build
MAINTAINER myeyeswereclosed
COPY src /home/config_server/src
COPY pom.xml /home/config_server
RUN mvn -f /home/config_server/pom.xml clean package

FROM openjdk:11-jdk-slim
COPY --from=build /home/config_server/target/ConfigServer.jar /config_server/ConfigServer.jar
EXPOSE 4000
ENTRYPOINT ["java", "-jar", "/config_server/ConfigServer.jar"]
