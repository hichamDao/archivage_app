# Image de base avec Java 17
FROM eclipse-temurin:21-jdk-jammy

# Crée le dossier app dans le conteneur
WORKDIR /app

RUN echo "Building new version $(date)" > dummy.txt

#Copie le fichier JAR

COPY d4-0.0.1-SNAPSHOT.jar app.jar0

# Expose le port (Railway définit PORT automatiquement)
ENV PORT=8080
EXPOSE $PORT

# Commande de lancement
ENTRYPOINT ["java","-jar","app.jar"]
