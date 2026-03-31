#!/bin/bash

set -e

# 🔧 CONFIG
VERSION=${1:-1.0.1}

PROJECT_ROOT="/home/farooq/IdeaProjects/ecommerceplatform"

SERVICES=(
"user-service"
"gateway"
"config-server"
"discovery-server"
)

echo "🚀 Using version: $VERSION"

# 🧠 Ensure minikube is running
echo "🔍 Checking Minikube..."
minikube status >/dev/null 2>&1 || {
  echo "❌ Minikube not running. Start it first."
  exit 1
}

# 🔁 Loop services
for SERVICE in "${SERVICES[@]}"
do
  echo "---------------------------------------"
  echo "🔨 Building $SERVICE..."

  docker build --no-cache \
    -f $PROJECT_ROOT/$SERVICE/Dockerfile \
    -t $SERVICE:$VERSION \
    --build-arg SERVICE_NAME=$SERVICE \
    --build-arg VERSION=$VERSION \
    $PROJECT_ROOT   # ✅ CRITICAL FIX (context)

  echo "📦 Loading into Minikube..."
  minikube image load $SERVICE:$VERSION

  echo "✅ Done: $SERVICE"
done

echo "---------------------------------------"
echo "🎉 All images built and loaded successfully!"