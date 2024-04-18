#!/bin/bash

# Остановить текущий запущенный сервис (если запущен)
sudo systemctl stop api-gateway

# Копировать новый JAR-файл в директорию сервиса
cp target/api-gateway.jar /transaction_service/build/generated

# Запустить сервис с использованием нового JAR-файла
sudo systemctl start api-gateway

# Опционально: проверить статус сервиса
sudo systemctl status api-gateway
