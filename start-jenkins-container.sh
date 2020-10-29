#!/usr/bin/env bash

docker container stop jgit_jenkins_1
docker-compose -f docker-compose.yaml up -d
sleep 5
docker ps