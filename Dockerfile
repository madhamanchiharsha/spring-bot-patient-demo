FROM openjdk:17-jdk-alpine
MAINTAINER harsha madhamanchi
COPY /build/libs/demo-0.0.1-SNAPSHOT.jar /demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]
