FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /build

COPY app/pom.xml app/pom.xml
RUN mvn -f app/pom.xml -DskipTests dependency:go-offline

COPY app/src app/src

RUN mvn -f app/pom.xml -DskipTests package


FROM eclipse-temurin:17-jre

WORKDIR /app

EXPOSE 8080

COPY --from=build /build/app/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
