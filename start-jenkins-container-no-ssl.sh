#!/usr/bin/env bash
docker container stop kilpio_jenkins
docker-compose -f docker-compose-no-ssl.yaml up -d

