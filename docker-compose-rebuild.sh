#!/bin/sh

bash ./src/back-end/mvnw -f src/back-end/pom.xml package
docker compose up --build --force-recreate --no-deps