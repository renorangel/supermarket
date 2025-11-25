FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/supermarket-0.0.1.jar
COPY ${JAR_FILE} app_supermarket.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_supermarket.jar"]