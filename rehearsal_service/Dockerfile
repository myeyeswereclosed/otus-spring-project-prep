FROM maven:3.6.0-jdk-11-slim AS build
MAINTAINER myeyeswereclosed
COPY src /home/rehearsal_service/src
COPY pom.xml /home/rehearsal_service
RUN mvn -f /home/rehearsal_service/pom.xml clean package

FROM openjdk:11-jdk-slim
COPY --from=build /home/rehearsal_service/target/RehearsalService.jar /rehearsal_service/RehearsalService.jar
EXPOSE 8888

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait

CMD /wait && java -jar /rehearsal_service/RehearsalService.jar
