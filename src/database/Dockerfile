FROM postgres:latest
WORKDIR /usr/local/db/

RUN mkdir -p /docker-entrypoint-initdb.d

COPY sql-scripts /docker-entrypoint-initdb.d/