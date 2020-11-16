#!/usr/bin/env bash
JENKINS_USER=${JENKINS_USER:-jenkins}


apt-get -y update && \
apt-get -y upgrade && \
apt-get -y install apt-transport-https ca-certificates curl gnupg-agent software-properties-common && \

curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - && \

add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") $(lsb_release -cs) stable" && \

apt-get update && apt-get -y install docker-ce docker-ce-cli containerd.io

curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && \
chmod +x /usr/local/bin/docker-compose && \
ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose

apt-get -y install mc net-tools
#TODO: check if $JENKINS_USER user/group exist
groupadd -g 1000 $JENKINS_USER
useradd  -m -u 1000 -g 1000 -s /bin/bash $JENKINS_USER
usermod -aG docker $JENKINS_USER

mkdir /home/jenkins/jenkins_home
chown jenkins:jenkins /home/jenkins/jenkins_home
