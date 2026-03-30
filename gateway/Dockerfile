# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /build

# ✅ Declare ARG here
ARG SERVICE_NAME=gateway

# Copy full project
COPY . .

# Build only module
#RUN mvn clean install -pl /home/farooq/IdeaProjects/ecommerceplatform/${SERVICE_NAME} -am -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# ✅ REDECLARE ARG (VERY IMPORTANT)
ARG SERVICE_NAME=user-service
ARG VERSION=1.0.0

ENV APP_VERSION=${VERSION}

# ✅ Correct path
COPY --from=build /build/${SERVICE_NAME}/target/app.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]