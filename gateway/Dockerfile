# ---------- BUILD ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build

ARG SERVICE_NAME=gateway

# Copy entire project
COPY . .

# ✅ FIXED (no services/)
RUN mvn clean install -pl ${SERVICE_NAME} -am -DskipTests

# ---------- RUN ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

ARG SERVICE_NAME=gateway

# ✅ FIXED PATH
COPY --from=build /build/${SERVICE_NAME}/target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]