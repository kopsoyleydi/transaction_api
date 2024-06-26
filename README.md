# Transaction Microservices

## Project: Проект для МФО, для отслеживания транзакций

***Данное руководство подразумевается для описания сервиса, и для дальнейшего запуска.***

- [Описание сервиса](#about-service)
* [На чем я реализовал, миграцию данных](#database-migration)
* [Технологий](#technologies)
+ [Установка и запуск проекта на вашей стороне](#installation)


## About service

Проект для МФО, для отслежования транзаций - с разных сервисов МФО приходят транзаций, далее эти транзакций должны высчитываться по актуальному курсу валют,
базовая валюта KZT, все расчеты конвертируются в тенге, и уже далее рассчитывается лимит, лимит можно установить на любой валюте


### Ports
`
Api-Gateway: 9000 
`
`
Eureka-Server: 8761
`
`
Client-Service: 9001
`
`
Transaction-Service: 9002
`

### EndPoints

#### Client Service, this service connect to MySql

Main service endpoint - "api/user"

| Method | Path                 | Description                                                           |
|--------|----------------------|-----------------------------------------------------------------------|
| POST   | /insert              | Для добавления пользователя                                           |
| GET    | /{id}                | Для получения пользователя                                            |
| PUT    | /setLimit/{id}       | Для устонавления лимита                                               |
| GET    | /limit/list/bad/{id} | Запрос для списка транзакций которые превысили лимит                  |
| GET    | /limit/list/ok/{id}  | Запрос для списка транзакций которые в предалах лимита                |

USER-DTO - тело для отправки запроса на сервер, для создания нового контакта:

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

Использовал классы обертки(wrapper classes), так как внутри классов оберток есть свои методы которые могут помочь во время разработки, и garbage collector - очищает их.


#### Transaction service, this service connect to MongoDB

Transaction Controller main endpoint = "/transaction"

| Method | Path                     | Description                                                               |
|--------|--------------------------|---------------------------------------------------------------------------|
| POST   | /insert/                 | Для добавления транзакций                                                 |
| GET    | /list/limit/{accountFrom}| Запрос для списка транзакций которые превысили лимит                      |
| GET    | /list/{accountFrom}      | Запрос для списка транзакций которые превысили лимит                      |

TransactionInsert - для отправки запроса на сервер, для создания новой транзакций:

`

    Long:account_from
    Long:name
    Long:account_to
    String: currency_shortname
    Double: sum
    String: expense_category
    -- Дату и время создания сервис сам вазьмет и отправит в BD
`

Currency Controller - для обновления данных по валютам, использовал scheduler в Spring, для запуска каждый день 

Currency Controller: main endpoint = "/currency"

| Method | Path                     | Description                                                               |
|--------|--------------------------|---------------------------------------------------------------------------|
| POST   | /loadInfo/               | Для обноавления курса валют                                               |
| GET    | /amount/{code}           | Запрос для получения данных о валюте, в частности курс валюты в тенге     |


Описание всех эндпойтов внтури Swagger.
##### Swagger: портам каждого сервиса `http://localhost:****` прикрепить ссылку `/swagger-ui/index.html` для доступа


## Database migration

### Миграция данных было сделано с помощью Liquibase, и с внутренней аннотацией Spring для миграции mongodb
#### У сервиса клиента одна запись по пользователю, три записи для ролей, два запися для города
#### Данные о транзакциях и об валютах можете посмотреть внутри интерфейса mongodb
##### Username: admin
##### Password: root
##### Link:   `http://localhost:8081/` 

## Technologies

#### Использовал jdk-21 версий

`
id 'java'
    id 'org.springframework.boot' version '3.2.4'
    id 'io.spring.dependency-management' version '1.1.4'
`

###### Тесты: JUnit

###### Архитектура: Microservice, архитектура внутренних сервисов REST.

###### DB: MySql, MongoDB

###### Docker - чтобы все не конфигурировать и устанавливать

###### Lombok, Spring Data, Spring Cloud, Netflix-Eureka, Java SE, Spring WEB, Liquibase, Swagger

###### Mapper: MapStruct

###### Старался придерживаться принципов проектирования и ООП, но применять это было очень мало вариантов в связи с размером проекта

##### Gradle: 8.5



## Installation

### Перейдем к установке, самого сервиса

#### №1. Создать директорию для теста сервиса
#### №2. Скопировать мой репозиторий с GitHub по ссылке по команде `git clone ` + ссылка репозиторий, в терминале, внутри папки который вы создали, или можете внутри intellij idea, во верхних вкладках есть `get from version control` с помощью данной утилиты скопировать мои проект
#### №3. После всего клонировании кода, открыть его в IDEA приназдначеных для Java и Spring, для запуска сервиса
#### №4. Внутри проекта есть папка `dockers`, и внутри папка `database`.
#### №5. Далее открыть `power shell` или терминал внутри IDEA.
#### №6. Далее внутри терминала по пути dockers/mongodb запустить
     `docker compose up -d`
#### №7. После установки образов `docker`, и запуска контейнеров, убедиться что контейнеры запущены, внутри приложения docker desktop или по команде 
     `docker ps -a`
#### №7.1. Если контейнеры не запущены, то перезапустить контейнеры если не сработало, обратно перейти к 4 ому шагу :)
#### №8. Далее в любом из браузеров открыть окно по ссылке `http://localhost:8081/`, login:admin - password:pass, это для интерфейса mongodb.
#### №9. Далее внутри IDEA запустить сам проект
#### №10. Поздравляю вы запустили проект на своей стороне

#### Сервис по порту 9000, ссылка: `http://localhost:9000`
##### Примечание: У macbook, выходит одна проблема с взаймодействием eureka, и на централизованный эндпойнт не получается отправлять запрос, я тестировал все сервисы по отдельности если у вас другая OS, у нас должно работать





