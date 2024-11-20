FROM openjdk:17-jdk-slim

# Establece un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de tu aplicación en el contenedor
COPY target/pokedex-*.jar app.jar

# Define el puerto en el que la aplicación va a escuchar (opcional)
EXPOSE 8080

# Comando para iniciar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
