#!/bin/bash

TAG=$1

if [ -z "$TAG" ]; then
  echo "❌ Usage: ./cleanup.sh <tag>"
  exit 1
fi

echo "🧹 Removing images with tag: $TAG"

docker images --format "{{.Repository}}:{{.Tag}}" | grep ":$TAG" | while read IMAGE
do
  echo "Deleting $IMAGE"
  docker rmi -f $IMAGE
done

echo "✅ Cleanup complete"