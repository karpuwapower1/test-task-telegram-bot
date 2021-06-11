FROM maven:3-jdk-8 as builder

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine

COPY --from=builder /app/target/telegram-bot*.jar /telegram-bot.jar

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/telegram-bot.jar"]
