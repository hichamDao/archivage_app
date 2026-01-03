# base image
FROM eclipse-temurin:21-jdk-jammy

# WORKDIR
WORKDIR /app

# copy jar
COPY demo-0.0.1-SNAPSHOT.jar app.jar

# optional: cache bust
ARG CACHEBUST=1
RUN echo "Cache bust $CACHEBUST"

# expose port
EXPOSE 8080

# run jar

CMD ["java", "-Dserver.port=${PORT}", "-jar", "appv3.jar"]
