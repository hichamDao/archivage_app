# Image de base avec Java 17
FROM eclipse-temurin:17-jdk-jammy

# Crée le dossier app dans le conteneur
WORKDIR /app

# Copie le fichier JAR
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose le port (Railway définit PORT automatiquement)
ENV PORT=8080
EXPOSE $PORT

# Commande de lancement
ENTRYPOINT ["java","-jar","app.jar"]
