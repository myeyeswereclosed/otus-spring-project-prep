FROM maven:3.6.0-jdk-11-slim AS build
MAINTAINER myeyeswereclosed
COPY src /home/service_discovery/src
COPY pom.xml /home/service_discovery
RUN mvn -f /home/service_discovery/pom.xml clean package

FROM openjdk:11-jdk-slim
COPY --from=build /home/service_discovery/target/ServiceDiscovery.jar /service_discovery/ServiceDiscovery.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/service_discovery/ServiceDiscovery.jar"]
