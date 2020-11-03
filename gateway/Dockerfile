FROM maven:3.6.0-jdk-11-slim AS build
MAINTAINER myeyeswereclosed
COPY src /home/gateway/src
COPY pom.xml /home/gateway
RUN mvn -f /home/gateway/pom.xml clean package

FROM openjdk:11-jdk-slim
COPY --from=build /home/gateway/target/Gateway.jar /gateway/Gateway.jar
EXPOSE 8080

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait

CMD /wait && java -jar /gateway/Gateway.jar

