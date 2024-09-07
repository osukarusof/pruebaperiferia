# Usa una imagen base con Temurin JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Copia el JAR construido al contenedor
COPY target/mutant.jar /app/mutant-application.jar

# Establece el directorio de trabajo
WORKDIR /app

# Exponer el puerto en el que la aplicación estará escuchando
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "mutant-application.jar"]
