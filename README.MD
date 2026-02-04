![banner](https://awesomescreenshot.s3.amazonaws.com/image/3964926/58607340-8339b7c9a0f1b71d9d48f2d893244d4d.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAJSCJQ2NM3XLFPVKA%2F20260204%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260204T171647Z&X-Amz-Expires=28800&X-Amz-SignedHeaders=host&X-Amz-Signature=36f53397040bacffda3b31b9882fda6843b66b2e95536305df2123ea6a721682)

# Backend del Sistema de Monitoreo de Calidad de Agua (pinv01_267_ws)

Este repositorio contiene el código fuente para el servicio backend del Prototipo de Sistema de Monitoreo y Alerta Temprana de la Calidad del Agua, desarrollado en el marco de un proyecto de investigación de FACITEC.

## Sobre el Proyecto

- **Título del Proyecto:** PINV01-267: Sistema de red de sensores inalámbricos para el monitoreo de la calidad del agua en cauces hídricos del departamento del Canindeyú.
- **Institución:** FACITEC (Facultad de Ciencias y Tecnología - Universidad Nacional de Canindeyú).
- **Financiamiento:** CONACYT (Consejo Nacional de Ciencia y Tecnología).
- **Objetivo:** Desarrollar un sistema informático para el monitoreo de la calidad del agua de cauces hídricos utilizando datos obtenidos a través una red de sensores inalámbricos

## Arquitectura y Características del Backend

Esta aplicación, `pinv01_267_ws`, es el cerebro del sistema. Construida con **Java 21** y el framework **Spring Boot 3**, provee una API RESTful para centralizar y gestionar todos los datos del proyecto.

### Características Principales

*   **Gestión de Dispositivos:** Permite registrar y administrar los dispositivos de monitoreo IoT desplegados.
*   **Recepción de Lecturas:** Ofrece un endpoint robusto para que los dispositivos envíen los datos recolectados (pH, oxígeno disuelto, temperatura, etc.) de forma masiva y eficiente, utilizando procesamiento por lotes (batch processing).
*   **Consulta de Datos:** Expone endpoints públicos para consultar promedios de lecturas, permitiendo filtrar por ubicación y granularidad temporal (hora, día, mes).
*   **Gestión de Usuarios:** Implementa un sistema de administración de usuarios con roles (`ROOT`, `ADMIN`) para el manejo del sistema.
*   **Gestión de Suscripciones:** Permite a los interesados suscribirse para recibir futuras notificaciones o informes.
*   **Seguridad:** La comunicación está securizada mediante autenticación basada en JSON Web Tokens (JWT). Las rutas de administración están debidamente protegidas.
*   **Documentación de API:** La API está autodocumentada y es explorable a través de una interfaz de Swagger UI.

## Tecnologías Utilizadas

*   **Lenguaje:** Java 21
*   **Framework:** Spring Boot 3.5.0
    *   Spring Web
    *   Spring Data JPA
    *   Spring Security
    *   Spring Boot Actuator
*   **Base de Datos:** PostgreSQL
*   **Autenticación:** JSON Web Tokens (JWT)
*   **Mapeo de Objetos:** ModelMapper
*   **Documentación:** Springdoc OpenAPI (Swagger)
*   **Build Tool:** Maven

## Endpoints de la API

La API se divide en dos secciones principales: endpoints públicos y endpoints de administración.

### Documentación Interactiva (Swagger)

La documentación completa y explorable de los endpoints públicos se puede consultar en:
`http://<servidor>:<puerto>/<context-path>/api/docs`

(Nota: El `context-path` por defecto es `pinv01_267_ws` si se despliega la aplicación como un archivo WAR).

### Endpoints Principales

*   `POST /api/auth/login`: Autentica a un usuario y retorna un token JWT.
*   `GET /api/dispositivos/all`: Retorna una lista pública de los dispositivos de monitoreo.
*   `GET /api/lecturas/promedios`: Retorna promedios de lecturas. Requiere parámetros `ubicacion`, `year`, y opcionalmente `month` y `day`.
*   `POST /api/suscripciones/save`: Permite a un usuario crear una nueva suscripción.
*   `POST /api/lecturas/add`: (Uso interno) Endpoint para que los dispositivos IoT envíen sus datos.
*   `/api/admin/**`: Endpoints para la administración de usuarios, dispositivos y suscripciones. Requieren un token JWT con los permisos adecuados.

## Configuración

La configuración de la aplicación se gestiona a través de variables de entorno, definidas en un archivo `.env` en la raíz del proyecto. Se proporciona un archivo `.env.example` como plantilla.

| Variable                 | Descripción                                                                 |
| ------------------------ | --------------------------------------------------------------------------- |
| `SPRING_PROFILES_ACTIVE` | Perfil de Spring a activar (`dev` o `prod`).                                |
| `POSTGRES_HOST`          | Host de la base de datos PostgreSQL.                                        |
| `POSTGRES_PORT`          | Puerto de la base de datos PostgreSQL.                                      |
| `POSTGRES_PASSWORD`      | Contraseña del superusuario de PostgreSQL (usada por docker-compose).       |
| `DB_NAME`                | Nombre de la base de datos de la aplicación.                                |
| `DB_USER`                | Usuario para la conexión a la base de datos.                                |
| `DB_USER_PASSWORD`       | Contraseña para la conexión a la base de datos.                             |
| `JWT_SECRET`             | Secreto de 256 bits (en Base64) para firmar los tokens JWT.                  |
| `JWT_EXP`                | Tiempo de expiración del token JWT en milisegundos.                         |
| `ROOT_USERNAME`          | Nombre de usuario para el superusuario que se crea al iniciar la aplicación.|
| `ROOT_PASS`              | Contraseña para dicho superusuario.                                         |
| `FRAME_OPTIONS`          | Configuración de la cabecera `X-Frame-Options` (`deny`, `sameOrigin`).      |

## Cómo Ejecutar

### Prerrequisitos
*   JDK 21 o superior.
*   Maven 3.x.
*   Una instancia de PostgreSQL en ejecución.
*   Docker y Docker Compose (recomendado para levantar la base de datos fácilmente).

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone <url-del-repositorio>
    cd prototipo_web_backend
    ```

2.  **Configurar el entorno:**
    *   Copie el archivo `.env.example` a uno nuevo llamado `.env`.
    *   Modifique las variables en `.env` con la configuración de su base de datos y un secreto JWT seguro y único.

3.  **Levantar la base de datos con Docker:**
    *   Asegúrese de que las variables en `docker-compose.yml` coincidan con su configuración si es necesario.
    *   Ejecute el siguiente comando en la raíz del proyecto:
        ```bash
        docker-compose up -d
        ```

4.  **Ejecutar la aplicación con Maven:**
    ```bash
    ./mvnw spring-boot:run
    ```

La aplicación estará disponible en `http://localhost:8080`.

## Autores y Contacto

- **Investigador Principal:** Daniel Romero
- **Director del proyecto:** Rodrigo Martínez
- **Equipo de Desarrollo:** David Ruiz Diaz, Nazario Ayala, Angel Heimann, Gloria Ortiz.
- **Contacto:** dir.invext@facitec.edu.py

## Licencia

Este proyecto está licenciado bajo los términos de la Licencia Pública General de GNU v3.0. Consulta [LICENSE.md](LICENSE.md) para más detalles.


