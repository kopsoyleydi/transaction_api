#!/bin/bash

# Остановить текущий запущенный сервис (если запущен)
sudo systemctl stop transaction_service

# Копировать новый JAR-файл в директорию сервиса
cp target/transaction_service.jar /transaction_service/build/generated

# Запустить сервис с использованием нового JAR-файла
sudo systemctl start transaction_service

# Опционально: проверить статус сервиса
sudo systemctl status transaction_service
