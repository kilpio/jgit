FROM jenkins/jenkins:latest
USER root
RUN apt-get -y update && \
    apt-get -y upgrade && \
 apt-get -y install apt-transport-https ca-certificates curl gnupg-agent software-properties-common && \
 curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add - && \
 add-apt-repository \
 "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") \
 $(lsb_release -cs) \
 stable" && \
 apt-get update && \
 apt-get -y install docker-ce docker-ce-cli containerd.io mc net-tools
RUN curl -L "https://github.com/docker/compose/releases/download/1.27.4/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose && \
 chmod +x /usr/local/bin/docker-compose && \
 ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
COPY ./ssl/make_pks.sh /usr/local/bin/
COPY ./ssl/openssl.cnf /tmp
# TODO: rename group if exists
RUN groupadd -g 1000 $JENKINS_USER
RUN useradd  -m -u 1000 -g 1000 -s /bin/bash $JENKINS_USER
RUN usermod -aG docker $JENKINS_USER

USER jenkins
 
