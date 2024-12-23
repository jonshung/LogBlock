FROM openjdk:17-oracle AS base

WORKDIR /logblock

# Building
FROM base AS build
COPY . /logblock

RUN ./mvnw package -Dmaven.test.skip=true

# Production
FROM base AS run
RUN mkdir -p /logblock
WORKDIR /logblock

RUN adduser --system logblock-be
RUN chown logblock-be /logblock

COPY --from=build --chown=logblock-be /logblock/target/*.jar /logblock/app.jar

ENTRYPOINT [ "java", "-jar", "./app.jar" ]