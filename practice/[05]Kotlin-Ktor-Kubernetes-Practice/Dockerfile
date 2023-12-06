# базовый образ
FROM ubuntu:20.04

# установка желаемых пакетов и зависимостей
RUN apt-get update
RUN apt-get install -y wget && echo "We installed wget!"

# создание каталога /mytest
WORKDIR /mytest

# создание файла testfile
RUN touch testfile

# ls и echo будут выполняться при запуске контейнера
CMD ls && echo "Show me mytest"

