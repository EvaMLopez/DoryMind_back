# DoryMind_back

## Descripción del proyecto

Este proyecto se centra en el desarrollo de una API REST para una app móvil. En el backend se ha utilizado Spring Boot, destinada a gestionar tareas entre los miembros del grupo.
La API incluye endpoints específicos para la manipulación de datos relacionados con usuarios y la gestión de tareas asociadas.

En el frontend se ha utilizado VUE para crear la interfaz de usuario el cual interactua con el backend.

## Recursos

El proyecto esta creado con las siguientes tecnologías:

| Plugin               | URL                       |
| -------------------- | ------------------------- |
| Spring Web           | https://start.spring.io/  |
| Spring Data JPA      | https://start.spring.io/  |
| H2 Database          | https://start.spring.io/  |
| Spring BootDev Tools | https://start.spring.io/  |
| MySQL Drive          | https://start.spring.io/  |


## Instalación del proyecto

Clonar el repositorio

```sh
https://github.com/EvaMLopez/DoryMind_back
```

Iniciar backend

```sh
mvn spring-boot:run
```

## Uso en Postman

#### GET (login)

En Authorization: Basic Auth. Introducir Username y Password.

```sh
GET localhost:8080/api/v1/login
```

#### GET (Logout)

```sh
GET localhost:8080/api/v1/logout
```

#### POST (Registro)
```sh
POST http://localhost:8080/api/v1/register 
```

Hay que insertar un JSON para esta petición.

```json
{
    "username": "Eva",
    "password": "evaeva",
    "email": "yosoyeva@gmail.com",
    "groupName": "Cole"
}

```

### Tareas

#### POST (crear Tarea)

```sh
POST http://localhost:8080/api/v1/tasks
```

Hay que insertar un JSON para esta petición.

```json
{
    "title": "Ropa",
    "description": "Ropa tendida, recogerla antes de que llueva",
    "deadline": "2023-03-25",
    "urgent": true,
    "completed": false,
    "assignedUserId": 1 
}
```

#### GET (ver todas las Tareas)

```sh
GET http://localhost:8080/api/v1/tasks
```
#### GET (ver Tareas asignadas al usuario autenticado)

```sh
GET http://localhost:8080/api/v1/tasks/my-tasks
```

#### DELETE (borrar Tarea)

```sh

http://localhost:8080/api/v1/tasks/6
```
#### PUT (editar Tarea)

```sh
http://localhost:8080/api/v1/tasks/3
```
Hay que insertar un JSON para esta petición.

```json
{
    "title": "Ropa",
    "description": "Ropa tendida, ya no dan lluvia, dejarla más tiempo",
    "deadline": "2023-03-25",
    "urgent": false,
    "completed": false,
    "assignedUserId": 1 
}
```

