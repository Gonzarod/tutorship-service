FROM openjdk:8
ADD target/session-service.jar session-service.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar", "session-service.jar"]
