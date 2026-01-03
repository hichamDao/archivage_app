# Image de base avec Java 17
FROM eclipse-temurin:21-jdk-jammy

# Crée le dossier app dans le conteneur
WORKDIR /app

# Copie le fichier JAR
<<<<<<< HEAD
COPY d-0.0.1-SNAPSHOT.jar app.jar
=======
COPY d2-0.0.1-SNAPSHOT.jar app.jar
>>>>>>> 2cf105a00452077c105e357234ddeef86ed58a39

# Expose le port (Railway définit PORT automatiquement)
ENV PORT=8080
EXPOSE $PORT

# Commande de lancement
ENTRYPOINT ["java","-jar","app.jar"]
