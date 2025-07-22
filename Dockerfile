FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM openjdk:17-jdk-slim
COPY --from=build /home/gradle/src/build/libs/*.jar /app/xphr.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/xphr.jar"]