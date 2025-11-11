🚀 API REST de Gestión de Productos
Alumna:Pérez García, Carolina Andrea
Legajo: 50127
Curso:3K9


Este es un proyecto de caso práctico (com.utn.productos_api) que implementa una API REST completa para la gestión de productos de un e-commerce.
La API permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre los productos, filtrarlos y gestionar su stock.El proyecto está construido con Spring Boot, sigue las mejores prácticas de arquitectura de software, incluyendo la separación de capas (Controlador, Servicio, Repositorio), el uso de DTOs para la transferencia de datos y un manejo global de excepciones.

📚 Características Principales
Gestión CRUD: Operaciones completas para crear, leer, actualizar y eliminar productos.

Filtrado: Endpoint específico para obtener productos filtrados por su categoría.

Validación: Validación de datos de entrada en los DTOs usando jakarta.validation (ej. @NotBlank, @Min, etc.).

Manejo de Errores: Implementación de un GlobalExceptionHandler (@ControllerAdvice) para gestionar errores de forma centralizada (400 Bad Request, 404 Not Found, 500 Internal Server Error).

Mapeo de DTOs: Uso de la librería ModelMapper para un mapeo limpio y eficiente entre Entidades y DTOs.

Documentación Interactiva: API documentada con Springdoc (Swagger), permitiendo probar todos los endpoints desde el navegador.

🛠️ Tecnologías Utilizadas
Java 17Spring Boot 3.3.1
Spring Web
Spring Data JPA
Spring Boot DevToolsMaven (Gestor de dependencias)
H2 Database (Base de datos en memoria)
Lombok (Para reducir código boilerplate)
Springdoc OpenAPI (Swagger) (Para documentación de API)
ModelMapper (Para mapeo de DTOs)

⚙️ Instalación y Ejecución
Para levantar el proyecto localmente:Clonar el repositorio:Bashgit clone \https://github.com/carolinaa13/productos-api
cd productos-api

Compilar el proyecto con Maven:Bash mvn clean install
Ejecutar la aplicación:Bash mvn spring-boot:run
(Alternativamente, puedes ejecutar la clase ProductosApiApplication.java desde tu IDE).
La aplicación estará disponible en: http://localhost:8080

📋 Uso de la API
Una vez que la aplicación esté en ejecución:
Se pueden usar las siguientes URLs para interactuar con el sistema:

1. Documentación Swagger:URL: http://localhost:8080/swagger-ui/index.html Desde aquí podrás ver todos los endpoints,
   sus descripciones, los modelos de datos (DTOs) y ejecutar pruebas en vivo.
2. Consola de Base de Datos H2:URL: http://localhost:8080/h2-console
   Asegúrate de usar la siguiente configuración al conectar:
   Driver Class: org.h2. Driver
   JDBC URL: jdbc:h2:mem:productdb
   User Name: sa
   Password: (dejar en blanco)

Endpoints Principales
Verbo           Ruta                        Descripción
HTTP
POST        /api/productos                  Crea un nuevo producto.
GET         /api/productos                  Obtiene la lista de todos los productos.
GET         /api/productos/{id}             Obtiene un producto específico por su ID.
GET         /api/productos/categoria        Filtra productos por categoría (ej. ?categoria=ROPA).
PUT         /api/productos/{id}             Actualiza un producto completo por su ID.
PATCH       /api/productos/{id}/stock       Actualiza únicamente el stock de un producto.
DELETE      /api/productos/{id}             Elimina un producto por su ID.

🏛️ Arquitectura del Proyecto
El proyecto sigue una arquitectura por capas para separar responsabilidades:

com.utn.productos\_api.controller:(Capa Web)
Contiene los RestController que exponen los endpoints y manejan las peticiones HTTP.
Solo interactúa con DTOs.

com.utn.productos\_api.service: (Capa de Negocio)
Contiene la lógica de negocio principal. Es llamado por el controlador.

com.utn.productos\_api.repository: (Capa de Datos)
Interfaces que extienden JpaRepository para la interacción con la base de datos.

com.utn.productos\_api.model: (Entidades)
Clases (@Entity) que representan las tablas de la base de datos (ej. Producto, Categoria).

com.utn.productos\_api.dto: (Data Transfer Objects)
Clases para transferir datos entre el cliente y el controlador, y para las validaciones.

com.utn.productos\_api.exception: (Excepciones)
Clases de excepción personalizadas y el manejador global (@ControllerAdvice).

com.utn.productos\_api.config: (Configuración)
Clases de configuración de Spring, como la creación del Bean de ModelMapper.

Conclusiones Personales:
Fue un excelente desafío aprender SpringBoot y utilizar estos métodos que eran desconocidos para mí. Sin embargo, valió la pena ya que es una manera mucho más limpia para elaborar código y más clara para entender qué hace cada clase o método en el código.


Como Anexo, se incluye un PDF con las capturas de pantalla de las consultas realizadas a través de Swagger, y una captura de la Base de datos con la tabla de Productos creados



