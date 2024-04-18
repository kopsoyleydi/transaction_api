#!/bin/bash

# Остановить текущий запущенный сервис (если запущен)
sudo systemctl stop client_service

# Копировать новый JAR-файл в директорию сервиса
cp target/client_service.jar /transaction_service/build/generated

# Запустить сервис с использованием нового JAR-файла
sudo systemctl start client_service

# Опционально: проверить статус сервиса
sudo systemctl status client_service
