FROM openjdk:11
ADD target/recommendationservice-0.0.1-SNAPSHOT.jar recommendation.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","recommendation.jar"]
