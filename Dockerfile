FROM arm64v8/eclipse-temurin:17-jdk-ubi9-minimal
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]