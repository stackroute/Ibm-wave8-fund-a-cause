FROM openjdk:11
ADD target/spring-boot-jwt-tr-0.0.1-SNAPSHOT.jar user-authentication.jar
EXPOSE 8070
ENTRYPOINT ["java","-jar","user-authentication.jar"]