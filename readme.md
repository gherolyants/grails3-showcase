## Сущности

### Отпуск
	дата начала : дата
	дата конца : дата
	пункт назначения : строка
	пользователь : Пользователь
	
### Пользователь
	логин : строка
	пароль : строка
	имя : строка
	фамилия : строка
	год рождения : число
	интересы : строка
	отпуска : список Отпуск

## Шаг 1. Инициализация приложения, профили

* Создать приложение командой grails create-app grails3-showcase --profile=anuglar
* Angular профиль включает в себя профиль rest-api и базовый профиль. (рассказать больше об angular профиле)

## Шаг 2. Запуск приложения, gradle

* Запустить приложение командой ./gradlew bootRun
* Потом сделать то же самое командой grails run-app
* Коротко рассказать о том, что grails работает поверх gradle и про соответствие команд grails и gradle

## Шаг 3. Доменный объект как REST controller

* Создать доменный объект командой grails create-domain-resource User
* Добавить несколько полей (например login, name, age), обратить внимание, что все они not null по умолчанию, что id добавляется автоматически
* Добавить сохранение нескольких объектов в BootStrap
* Получить их список в браузере
* Удалить дефолтный урл маппинг, добавить маппинг на ресурс
* Поиграться с запросами в Postman

## Шаг 4. Кастомизация вывода с помощью gson views

* Добавить поле password в объект User
* Показать, что оно тоже отображается
* Сгенерировать view командой generate-views grails3.showcase.User
* Сгенерировать контроллер командой create-restful-controller User 
* (не забыть добавить поле пароля в BootStrap)
* Добавить excludes в шаблон _user.gson
* Продемонстрировать, что поле password исчезло из вывода
* Показать, что можно для списка и для одного ресурса делать разный вывод (почему-то не получилось через tmpl.*)

## Шаг 5. Добавление второй сущности

* Добавить вторую сущность Трип
* Связать сущности
* Создать rest controller для Трипа (почему-то нужно перезагрузить)
* Добавить вложенный ресурс в UrlMappings
* Переопределить методы ресурса, чтобы сделать его вложенным
* Добавить настройку формата даты
* Продемонстрировать создание и получение списка Трипов для конкретного пользователя

## Шаг 6. Юнит тесты для контроллера
* НЕ НУЖНО генерировать тесты командой generate-unit-test, она генерирует нерелевантный для REST контроллера код
* добавить build-test-data-plugin
* показать, как здорово быстро создавать фикстуру и проверять результаты по базе
* показать, как здорово работать с json прямо в коде без использования строк 

## Шаг 7. Функциональные тесты для контроллера
* сгенерировать тест командой generate-functional-test 
* реализовать тест для index и save
* показать, как легко и удобно тестировать REST api на реальном работающем приложении

## Шаг 8. Версионирование json view
* создать новый контроллер для пользователя с таким же именем
* прописать namespace у обоих контроллеров (это важно, если прописать только у одного, работать не будет)
* добавить в UrlMappings конфигурацию для нового контроллера, прописав в url версию и в конфигурации namespace, а также добавить версию к url и namespace к конфигурации второго контроллера
* продемонстрировать работу версионированного API через версию в url
* удалить версию из всех url в UrlMappings и добавить атрибут version: к конфигурации второго контроллера
* продемонстрировать работу версионированного API через Accept-Version с помощью Postman (для вызова конроллера с пустой версией нужно передать любую непустую строку)
* кратко рассказать о преимуществе второго подхода

## Шаг 9.  Кастомный контроллер
* переименовать TripController в UserTripController
* создать TripController
* реализовать метод index для поиска отпусков по локации и датам 
* продемонстрировать различные возможности GORM по поиску (dynamic finders, where) рассказать про использование Hibernate Criteria API и HQL
* выделить метод поиска в сервис
* возвращать ошибку 422 если один из параметров поиска не задан

## Шаг 10. Console
* добавить и продемонстрировать console plugin
* переопределить метод findTrips следующим образом:

	ctx.tripService.metaClass.findTrips = { String a, Date b, Integer c ->
	    []
	}
	
## Заключение
* рассказать об asset-pipeline-plugin
* показать команды grails для работы с angular
* рассказать о karma plugin
