FROM eclipse-temurin:17-jdk-focal
RUN mkdir /repl-app
WORKDIR /repl-app
COPY target/repl-0.0.1-SNAPSHOT.jar repl.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "repl.jar"]