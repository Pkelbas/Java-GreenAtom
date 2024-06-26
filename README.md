# Java-GreenAtom

Тестовое задание на Java-стажировку в GreenAtom. Весна 2024

1. Реализован базовый функционал требуемый в задании

2. Реализована аутенфикация с помощью JWT-токена.

3. Реализован доступ к эндпоинтам по ролям, в частности по permisson-ам:

    1. Регистрация\аутенфикация доступна без ограничений по ролям
    2. Нет разделения на контроллер админа и контроллер юзера, но часть функционала контроллеров
       доступна только админу(досутп указываетя в SecurityConfig)
    3. Юзер может редактировать\удалять только свои сообщения(происходит проверка на совпадение
       username в SecurityContextHolder и username в message)
    4. Админ может редактировать\удалять любые сообщения(происохдит проверка на нужную роль)

4. Используемое хранилище: H2-Database. Также используется h2-console.
    1. url : jdbc:h2:mem:testdb
    2. username : test
    3. password : test

В репозитории приложен postman_collection со всеми эндпоинтами.

# Описание эндпоинтов


## POST http://localhost:8080/v1/auth/reg

Регистрация.

Требует username, password и роль в RequestBody.

Возвращает JWT-токен, который далее надо использовать при обращение к защищенным эндпоинтам.


## POST http://localhost:8080/v1/auth/auth

Аутенфикация.

Требует username и password в RequestBody.

Возвращает JWT-токен, который далее надо использовать при обращение к защищенным эндпоинтам.


## GET http://localhost:8080/api/v1/topic/all

Возвращает все топики. Доступен всем авторизованным пользователям.


## POST http://localhost:8080/api/v1/topic/create

Создает топик вместе с первым сообщением в нем. Доступен всем авторизованным пользователям.


## DELETE http://localhost:8080/api/v1/topic/delete

Удаляет топик и все сообщения в нем. Доступен только администратору.


## GET http://localhost:8080/api/v1/topic/find

Возвращает топик по ID. Доступен всем авторизованным пользователям.

## PUT http://localhost:8080/api/v1/topic/update

Обновляет топик(его название). Доступен только администратору.

## GET http://localhost:8080/api/v1/message/all

Возвращает все сообщения в топике по ID. Доступен всем авторизованным пользователям.

## POST http://localhost:8080/api/v1/message/create

Создает сообщение в топике по ID. Доступен всем авторизованным пользователям.

## DELETE http://localhost:8080/api/v1/message/delete

Удаляет сообщение по ID. User-ы могут удалять только свои сообщения, Admin-ы любые.

При удалении последнего сообщения в топике, топик тоже удаляется.(т.к. топик должен иметь хотя бы одно сообщение)

## GET http://localhost:8080/api/v1/message/find

Возвращает сообщение по ID. Доступен всем авторизованным пользователям.

## PUT http://localhost:8080/api/v1/message/update

Редактирует сообщение по ID. User-ы могут редактировать только свои сообщения, Admin-ы любые. После
редактирования сообщения меняется editTime.