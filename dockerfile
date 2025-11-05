FROM maven:3.9.6-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN mvn -q -DskipTests clean compile

CMD ["mvn", "test"]