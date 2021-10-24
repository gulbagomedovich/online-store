FROM openjdk:8-jdk-alpine
EXPOSE 8082
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java ${JAVA_OPTS} -jar /app.jar