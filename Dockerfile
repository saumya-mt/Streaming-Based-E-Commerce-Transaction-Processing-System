# Build stage
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
COPY transaction-generator/pom.xml transaction-generator/
COPY transaction-processor/pom.xml transaction-processor/
COPY api-service/pom.xml api-service/
RUN mvn dependency:go-offline

COPY transaction-generator/src transaction-generator/src
COPY transaction-processor/src transaction-processor/src
COPY api-service/src api-service/src
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:11-jre-slim
WORKDIR /app

# Copy built artifacts from build stage
COPY --from=build /app/transaction-generator/target/*.jar /app/transaction-generator.jar
COPY --from=build /app/transaction-processor/target/*.jar /app/transaction-processor.jar
COPY --from=build /app/api-service/target/*.jar /app/api-service.jar

# Environment variables
ENV SPRING_PROFILES_ACTIVE=prod
ENV KAFKA_BOOTSTRAP_SERVERS=kafka:9092
ENV REDIS_HOST=redis
ENV REDIS_PORT=6379
ENV POSTGRES_HOST=postgres
ENV POSTGRES_PORT=5432

# Expose ports
EXPOSE 8080 8081 8082

# Health check
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Entry point
ENTRYPOINT ["java", "-jar"]
CMD ["transaction-processor.jar"] 