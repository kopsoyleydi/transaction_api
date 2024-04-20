# Transaction Microservices

## Project: Проект для МФО, для отслежования транзаций

***Данное руководство подразумевается для описания сервиса, и для дальнейшего запуска.***

- [Описание сервиса](#about-service)
* [На чем я реализовал, миграцию данных](#database-migration)
* [Технологий](#technologies)
+ [Установка и запуск проекта на вашей стороне](#installation)


## About service

Проект для МФО, для отслежования транзаций - с разных сервисов МФО приходят транзаций, далее эти транзакций должны высчитоваться по актуальному курсу волют,
базовая валюта KZT, все расчеты конвиртируются в тенге, и уже далее высчитывается лимит, лимит можно устоновить на любой валюте

### EndPoints

#### Client Service, this service connect to MySql

Main service endpoint - "api/user"

| Method | Path         | Description                                                           |
|--------|----------------------|-----------------------------------------------------------------------|
| POST   | /insert              | Для добавления пользователя                                           |
| GET    | /{id}                | Для получения пользователя                                            |
| PUT    | /setLimit/{id}       | Для устонавления лимита транзакций                                    |
| GET    | /limit/list/bad/{id} | Запрос для списка транзакций которые превысили лимит                  |
| GET    | /contact/all         | Запрос для списка транзакций которые в предалах лимита                |

USER-DTO - для отправки запроса на сервер, для создания нового контакта:

`

    Long:id -- при созданий нового пользователя отправлять без id 
    String:name
    Double:limit_sum
    LocalDateTime: limit_datetime
    String: surname
    String clientIin
    String birthDate
    String address
    Double balance
    @ManyToOne
    CityDto city
    @ManyToMany
    List<PermissionDto> permissions;
    -- Дату и время создания сервис сам вазьмет и отправит в BD
`
Использовал классы обертки(wrapper classes), так как внутри классов оберток есть свои методы которые может помогут во время разработки, и Garbage Callector - очищает их.


#### Transaction service, this service connect to MongoDB
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






