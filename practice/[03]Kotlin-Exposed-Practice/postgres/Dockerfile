FROM postgres:14-alpine
MAINTAINER MIPT-Kotlin-Backend

ARG USER_NAME
ARG USER_PASSWORD

ENV POSTGRES_PASSWORD=simple_password
ENV POSTGRES_USER=mipt_kotlin_backend
ENV POSTGRES_DB=practice_db
RUN apk add --no-cache tzdata
EXPOSE 9000
ENV TZ=Europe/London