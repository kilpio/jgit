#!/usr/bin/env bash
docker container stop kilpio_jenkins
docker-compose -f docker-compose.yaml up -d
