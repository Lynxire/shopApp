FROM openjdk:22-jdk
ARG API=/api/target/api-1.0-SNAPSHOT.jar
ARG CORE=/core/target/core-1.0-SNAPSHOT.jar
WORKDIR /app
COPY ${API} api.jar
COPY ${CORE} core.jar
CMD ["java", "-jar", "core.jar"]
