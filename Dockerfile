# STAGE 1: Build journey server jar
FROM gradle as builder
USER root

COPY . .
RUN gradle build

# STAGE 2: Start journey server
FROM openjdk:8-jdk-alpine
VOLUME /tmp

COPY --from=builder /home/gradle/build/libs/church-people.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
