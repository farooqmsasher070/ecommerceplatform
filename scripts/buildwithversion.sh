#!/bin/bash

set -e

# 🔧 CONFIG

VERSION="latest"
BASE_DIR="/home/farooq/Desktop/ecommerceplatform"

SERVICES=(
"user-service"
#"gateway"
#"config-server"
#"discovery-server"
)

echo "🚀 Version: $VERSION"
cd $BASE_DIR

# 🧠 Step 1 — Build all modules

echo "🔨 Building Maven project..."
mvn clean install -DskipTests

# 🧱 Step 2 — Loop services

for SERVICE in "${SERVICES[@]}"
do
echo "----------------------------------------"
echo "📦 Processing $SERVICE..."

JAR_PATH="$BASE_DIR/$SERVICE/target"

# 🧪 Check jar exists

JAR_FILE=$(ls $JAR_PATH/*.jar | head -n 1)

if [ -z "$JAR_FILE" ]; then
echo "❌ No JAR found for $SERVICE"
#exit 1
fi

echo "✅ Found JAR: $JAR_FILE"

# 🐳 Step 3 — Build Docker image
cd $BASE_DIR
echo "🐳 Building Docker image for $SERVICE..."
docker build --no-cache \
-t $SERVICE:$VERSION \
-f $BASE_DIR/$SERVICE/Dockerfile \
--build-arg SERVICE_NAME=$SERVICE \
$BASE_DIR

# 📥 Step 4 — Load into Minikube

echo "📥 Loading into Minikube..."
minikube image load $SERVICE:$VERSION

echo "✅ Done: $SERVICE"
done

echo "----------------------------------------"
echo "🎉 All services built and loaded!"
