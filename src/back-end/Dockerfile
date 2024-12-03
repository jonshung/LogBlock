FROM openjdk:17-oracle
ARG RUNTIME_JAR=target/*.jar

RUN mkdir -p /usr/local/app/
WORKDIR /usr/local/app/

COPY $RUNTIME_JAR ./app.jar

RUN adduser logblock-be
USER logblock-be
ENTRYPOINT [ "java", "-jar", "./app.jar" ]