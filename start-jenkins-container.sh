#!/usr/bin/env bash

docker-compose -f docker-compose.yaml up -d
sleep 5
docker ps