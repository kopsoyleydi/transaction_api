# Transaction Microservices

## Project: Проект по МФО, для отслежования транзаций

***Данное руководство подразумевается для описания сервиса, и для дальнейшего запуска.***

- [Описание сервиса](#about-service)
* [На чем я реализовал, миграцию данных](#database-migration)
* [Технологий](#technologies)
+ [Установка и запуск проекта на вашей стороне](#installation)


## About service

Сервис CRUD - телефония, с использованием программы можно добавлять
контакты друзей или же коллег в свою базу потом для обращения и для
дальнейшего использования

### EndPoints

#### Contact service, this service connect to Postgres

| Method | Path         | Description                                                           |
|--------|--------------|-----------------------------------------------------------------------|
| GET    | /contact/id  | Get a contact by id                                                   |
| POST   | /contact/    | Create a new contact, send request without id                         |
| PUT    | /contact/    | Update contact with all parameters                                    |
| DELETE | /contact/id  | Delete a contact                                                      |
| GET    | /contact/all | Запрос для списка контактов с помощью фильтра Filter - описание снизу |

CONTACT-DTO - для отправки запроса на сервер, для создания нового контакта:

`

    int:id -- при созданий нового пользователя отправлять без id 
    String:name
    int:yearOfBirth
    String: firstPhoneNumber
    String: secondPhoneNumber
    -- Дату и время создания сервис сам вазьмет и отправит в BD
`

#### Person service, this service connect to MongoDB
| Method | Path         | Description                                                               |
|--------|--------------|---------------------------------------------------------------------------|
| GET    | /person/id   | Get a person by id                                                        |
| POST   | /person/     | Create a new person, send request without id                              |
| PUT    | /person/     | Update a person, with all parameters                                      |
| GET    | /person/all  | Запрос для списка пользователей с помощью фильтра Filter - описание снизу |
| DELETE | /person/id   | Delete a person by id                                                     |

PERSON-DTO - для отправки запроса на сервер, для создания нового пользователя:

`

    String:id -- при созданий нового пользователя отправлять без id 
    String:name
    int:yearOfBirth
    String: firstPhoneNumber
    String: secondPhoneNumber
    -- Дату и время создания сервис сам вазьмет и отправит в BD
`

#### Filter класс упомянутый в заданий состоит из ваших параметров, это 
 `

    int:limit,
    int:offset

    Отправляете это, в теле запроса 
` 

## Database migration

### Миграция данных было сделано с помощью Liquibase, и с внутренней аннотацией Spring
#### У каждого сервиса по 2 данных


## Technologies

#### Java version: 17

#### Но у меня было jdk-21 версий

###### id 'java'

###### id 'org.springframework.boot' version '3.2.0'

###### id 'io.spring.dependency-management' version '1.1.4'

###### Тесты: TestNg

###### Архитектура: MVC

###### DB: Postgres, MongoDB

###### Docker - чтобы все не конфигурировать и устанавливать
###### Думаю у разработчиков по большей вероятности есть Docker

###### Lombok, Spring Data, Spring WEB, Spring, Liquibase

###### Mapper: Можно было использовать MapStruct, предпочел написать все руками.

###### Старался придерживаться принципов проектирования и ООП, но применять это было очень мало вариантов в связи с размером проекта

##### Gradle: 8.5



## Installation

### Перейдем к установке, самого сервиса

#### №1. Создать директорию для теста сервиса
#### №2. Скопировать мой репозиторий с GitHub по ссылке по команде `git clone ` + ссылка репозиторий, в терминале, в папке который вы создали
#### №3. После всего копирования кода, открыть его в IDEA приназдначеных для Java и Spring, для запуска сервиса
#### №4. Внутри проекта есть папка `docker`, и внутри две папки `mongodb`  и `postgres`.
#### №5. Далее зайти в каждый файл и именно где файлы открыть `power shell`.
#### №6. Далее внутри терминала в каждом из папок исполнить команду `docker compose up -d`.
#### №7. После установки образов `docker`, и запуска контейнеров, убедиться что контейнеры запущены.
#### №7.1. Если контейнеры не запущены, то перезапустить контейнеры если не сработало, обратно перейти к 4 ому шагу :)
#### №8. Далее в любом из браузеров открыть окно по ссылке `http://localhost:8081/`, login:admin - password:pass
#### №9. Далее внутри IDEA запустить сам проект :)
#### №10. Поздравляю вы запустили проект на своей стороне:)

### Папка postman: внутри файл со всеми запросами на сервер
#### Сервис по порту 9000, ссылка: `http://localhost:9000`

##### Примечание: nobeksultan в git ветке это я точнее GitHub аккаунт. И прошу учесть что mongodb сам генерирует уникальный id, и в связи с этим, прошу зайти localhost:8081 и оттуда взять id, спасибо.






