#!/usr/bin/env bash

JENKINS_DATA=$(cat .env | grep HOST_JENKINS_DATA)
eval "${JENKINS_DATA}"

echo "Creating new keystore..."
docker-compose --remove-orphans -f docker-compose-switch-to-ssl.yaml up  2>/dev/null

echo "Saving password in the .env file..."

KEYSTORE_PASSWORD=$(cat ${HOST_JENKINS_DATA}/keystorepass)
sed -i "s/^KEYSTORE_PASS.*/KEYSTORE_PASS=${KEYSTORE_PASSWORD}/g" .env