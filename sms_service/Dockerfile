FROM maven:3.6.0-jdk-11-slim AS build
MAINTAINER myeyeswereclosed
COPY src /home/sms_service/src
COPY pom.xml /home/sms_service
RUN mvn -f /home/sms_service/pom.xml clean package

FROM openjdk:11-jdk-slim
COPY --from=build /home/sms_service/target/SmsService.jar /sms_service/SmsService.jar
EXPOSE 8085

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.7.3/wait /wait
RUN chmod +x /wait

CMD /wait && java -jar /sms_service/SmsService.jar
