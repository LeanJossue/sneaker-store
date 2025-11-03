FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia el JAR compilado dentro de /app
COPY target/*.jar app.jar

# Expone el puerto del backend
EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java","-jar","app.jar"]
