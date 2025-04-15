FROM maven:3.9.8-eclipse-temurin-21 AS build
RUN mkdir /opt/app
COPY . /opt/app
WORKDIR /opt/app
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
RUN mkdir /opt/app
COPY --from=build /opt/app/target/alarme-0.0.1-SNAPSHOT.jar /opt/app/app.jar
WORKDIR /opt/app

# Define o profile padrão (pode ser sobrescrito com `-e PROFILE=algumacoisa`)
ENV PROFILE=prd

EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "app.jar"]
