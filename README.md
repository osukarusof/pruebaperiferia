<div align="center">
    <img src="https://download.logo.wine/logo/Spring_Framework/Spring_Framework-Logo.wine.png" width="300" alt="spring boot" />
</div>

El proyecto detectará si una persona es mutante mediante un método que analiza secuencias de ADN en una matriz NxN. Identificará mutantes si encuentra más de una secuencia de cuatro letras iguales en forma horizontal, vertical u oblicua. Se desarrollará en Java

# Tecnologias
- Java
- Maven
- Spring Boot
- Mysql Database
- JPA
- Docker

# Requirimientos
- Java 17
- maven 3.9
- Docker

# Prerrequisitos

* Docker: Asegúrate de tener Docker y Docker Compose instalados en tu máquina. Puedes descargar Docker desde aquí e instalar Docker Compose siguiendo las instrucciones oficiales.


# Guía para Ejecutar la Aplicación

Esta guía explica cómo ejecutar la aplicación Spring Boot junto con una base de datos MySQL utilizando Docker.

### Paso 1: Navegar al Directorio del Proyecto

Abre una terminal y navega al directorio raíz de tu proyecto donde se encuentran los archivos docker-compose.yml y Dockerfile. Usa el comando cd para cambiar al directorio adecuado:

>```bash
> cd /ruta/a/tu/proyecto
>```

Asegúrate de reemplazar /ruta/a/tu/proyecto con la ruta real a tu directorio de proyecto.

### Paso 2: Construir y Ejecutar los Contenedores

1. Detén y elimina los contenedores existentes (si los hay):

>```bash
> docker-compose down
>```

2. Construye la imagen y levanta los contenedores:

>```bash
> docker-compose up --build
>```

### Paso 4: Acceder a la Aplicación

Una vez que los contenedores estén funcionando, puedes acceder a la aplicación Spring Boot

>```http request
>GET http://localhost:8080
>```
