## Task API

REST API для управления задачами с поддержкой аутентификации (JWT), фильтрацией и документацией Swagger.

---

### Технологии

* Java 21
* Spring Boot 3.5.0
* Spring Security + JWT
* Spring Data JPA
* PostgreSQL
* Swagger для документации
* Maven
* Lombok

---

### Как запустить проект локально

#### 1. Склонировать репозиторий

```bash
git clone https://github.com/MaxLutsenko205/TaskApi
cd TaskApi
```

#### 2. Создать .env файл в корне проекта:

Укажите в нём необходимые переменные:

```bash
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
JWT_SECRET=your_jwt_secret_key
```

#### 3. Настроить базу данных PostgreSQL

Создай БД вручную или выполнить:

```sql
CREATE DATABASE task_service;
```

#### 4. Запустить проект

В IntelliJ IDEA или через команду:

```bash
./mvnw spring-boot:run
```

---


### Swagger-документация

Для удобного просмотра и тестирования эндпоинтов была добавлена документация Swagger

Доступна по адресу: http://localhost:8080/swagger-ui/index.html

