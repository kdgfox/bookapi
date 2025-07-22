# Dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY . /app

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/SpringBootTest-0.0.1-SNAPSHOT.jar"]