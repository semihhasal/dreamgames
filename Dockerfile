FROM openjdk:17
EXPOSE 8080
ARG JAR_FILE=target/dreamgames-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} dreamgames.jar
ENTRYPOINT ["java","-jar","/dreamgames.jar"]