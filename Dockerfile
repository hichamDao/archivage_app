# Image de base avec Java 17
FROM eclipse-temurin:21-jdk-jammy

# Crée le dossier app dans le conteneur
WORKDIR /app

# Copie le fichier JAR
COPY archivage-SNAPSHOT.jar app.jar

# Expose le port (Railway définit PORT automatiquement)
ENV PORT=8080
EXPOSE $PORT

# Commande de lancement
ENTRYPOINT ["java","-jar","app.jar"]
