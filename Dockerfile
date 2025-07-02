# ----- BUILD PHASE -----
FROM maven:3.8.4-amazoncorretto-17 as build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# ----- RUNTIME PHASE -----
FROM amazoncorretto:17

WORKDIR /app

COPY --from=build /app/target/fastfood-app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
