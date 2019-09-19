FROM openjdk:11
ADD target/CauseCRUD-0.0.1-SNAPSHOT.jar cause-registration.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","cause-registration.jar"]
