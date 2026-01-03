# base image
FROM eclipse-temurin:21-jdk-jammy

# WORKDIR
WORKDIR /app

# copy jar
COPY app-0.0.2.jar.jar app.jar

# optional: cache bust
ARG CACHEBUST=1
RUN echo "Cache bust $CACHEBUST"

# expose port
EXPOSE 8080

# run jar
CMD ["java", "-jar", "app.jar"]
