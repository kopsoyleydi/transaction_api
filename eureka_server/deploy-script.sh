#!/bin/bash

# Остановить текущий запущенный сервис (если запущен)
sudo systemctl stop eureka_server

# Копировать новый JAR-файл в директорию сервиса
cp target/eureka_server.jar /transaction_service/build/generated

# Запустить сервис с использованием нового JAR-файла
sudo systemctl start eureka_server

# Опционально: проверить статус сервиса
sudo systemctl status eureka_server
