# Usa una imagen base con Java 17
FROM openjdk:17-jdk-slim

# Copia el JAR construido al contenedor
COPY target/mutant.jar /app/mutant-application.jar

# Establece el directorio de trabajo
WORKDIR /app

# Exponer el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "mutant-application.jar"]
