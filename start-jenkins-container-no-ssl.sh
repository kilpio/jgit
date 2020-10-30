#!/usr/bin/env bash
docker container stop kilpio_jenkins
docker-compose -f docker-compose-no-ssl.yaml -f docker-compose-switch-to-ssl.yaml up

sleep 5
docker ps