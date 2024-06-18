FROM maven:3.9.7-eclipse-temurin-22 AS build
WORKDIR /app
COPY pom.xml .
COPY core/pom.xml ./core/
COPY api/pom.xml ./api/
COPY core/src ./core/src/
COPY api/src ./api/src/
RUN mvn clean package -DskipTests


FROM openjdk:22-jdk
WORKDIR /app
COPY --from=build /app/core/target/original-core-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
