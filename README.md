# TV Maze API

API REST desarrollada con Java y Spring Boot para la integracion con la API publica de TV Maze y la gestion de datos en MongoDB. Permite la busqueda de shows de television, implementando un sistema de almacenamiento local y gestion de comentarios.

## Arquitectura y Estructura del Proyecto
El proyecto fue construido priorizando la calidad del software, implementando una arquitectura por capas y guiada estrictamente por los principios SOLID. Se utilizo inyeccion de dependencias basada en interfaces para garantizar el desacoplamiento.

La estructura interna del código está organizada en los siguientes paquetes principales:

* **client:** Centraliza la logica de integracion con el API externa (TV Maze), aislando las peticiones HTTP.
* **controller:** Define los endpoints REST y gestiona las peticiones de los clientes.
* **service:** Contiene la logica de negocio principal, operando sobre interfaces e implementaciones separadas (`ServiceImpl`) para orquestar los datos.
* **repository:** Interfaces de Spring Data MongoDB para la persistencia de datos.
* **document:** Entidades de dominio mapeadas directamente a las colecciones de base de datos en MongoDB.
* **model:** Objetos de Transferencia de Datos (DTOs) para manejar peticiones (`Requests`), respuestas (`Responses`) y mapeos externos sin exponer la base de datos.
* **mapper:** Capa de transformacion dedicada a convertir informacion entre DTOs y Entidades (Documents).
* **exception:** Manejo global y centralizado de errores para estandarizar las respuestas HTTP de fallo.

## Pruebas Unitarias y de Integracion
El repositorio incluye una suite de pruebas diseñadas para validar la logica de negocio, el manejo de errores del cliente HTTP y la correcta integracion de las capas (incluyendo validacion de peticiones y logica de servicios). 

Para ejecutar todas las pruebas automatizadas y verificar la integridad del codigo, utiliza el comando:

```bash
mvn test
```

## Ejecucion Local
Para levantar la aplicacion localmente, utiliza el siguiente comando en la raiz del proyecto:

```Bash
.\mvnw spring-boot:run
```
