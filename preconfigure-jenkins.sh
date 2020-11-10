#!/usr/bin/env bash
docker container stop kilpio_jenkins

HOST_JENKINS_DATA=$(cat .env | grep HOST_JENKINS_DATA)
eval "${HOST_JENKINS_DATA}"

mkdir -p "${HOST_JENKINS_DATA}"

cp -r ./init.groovy.d/ "${HOST_JENKINS_DATA}"
chown -R jenkins:jenkins "${HOST_JENKINS_DATA}"

docker-compose -f docker-compose-preconfigure-jenkins.yaml up -d

echo "run docker logs kilpio_jenkins -f to see if setup procedure is over"
echo "run jenkins-switch-to-ssl.sh to swith Jenkins container to https"
echo "than run start-jenkins-containter to start working with your jenkins"
echo "the default admin password is <changeme>, so change it"

sleep 10