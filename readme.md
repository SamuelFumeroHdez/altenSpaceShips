# API de Spaceship

Esta API proporciona funcionalidades para administrar naves espaciales.

## Funcionalidades

- **Obtener todas las naves espaciales**: Permite obtener una lista de todas las naves espaciales disponibles. Se aplica paginación
- **Obtener una nave espacial por su ID**: Permite obtener detalles de una nave espacial específica mediante su ID.
- **Buscar naves espaciales por nombre**: Permite buscar naves espaciales por su nombre.
- **Crear una nueva nave espacial**: Permite crear una nueva nave espacial en la base de datos.
- **Actualizar una nave espacial existente**: Permite actualizar los detalles de una nave espacial existente.
- **Eliminar una nave espacial**: Permite eliminar una nave espacial existente de la base de datos.

## Puntos extra

Se han abordado los siguientes puntos extra:

- **Tests de Integración**: Se han creado tests de integración para garantizar el correcto funcionamiento de la API en diferentes escenarios.
- **Dockerización de la Aplicación**: La aplicación ha sido dockerizada para facilitar su despliegue y ejecución en cualquier entorno compatible con Docker.
- **Documentación de la API**: La API ha sido documentada utilizando OpenAPI y Swagger UI para una fácil comprensión y utilización por parte de los desarrolladores.

## Ejecución

Para ejecutar la aplicación, asegúrese de tener Docker instalado en su máquina y siga estos pasos:

1. Clonar el repositorio: `git clone https://github.com/SamuelFumeroHdez/altenSpaceShips`
2. Navegar al directorio del proyecto
3. Ejecutar el comando `mvn install`
4. Construir la imagen Docker: `docker build -t spaceships-app .`
5. Ejecutar el contenedor Docker: `docker run -p 8081:8081 spaceship-app`

La aplicación estará disponible en [http://localhost:8081/apispaceships](http://localhost:8081/api/spaceships).

## Documentación de la API

La documentación de la API está disponible en [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html), donde se pueden visualizar y probar los diferentes endpoints.
