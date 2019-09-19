FROM openjdk:11
ADD target/NotifyService-0.0.1-SNAPSHOT.jar notify.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","notify.jar"]
