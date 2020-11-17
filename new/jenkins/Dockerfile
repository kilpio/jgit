FROM jenkins/jenkins:latest

USER root

ARG USER_GROUP_ID
ARG USER_ID

RUN groupmod -g ${USER_GROUP_ID} jenkins
RUN usermod -u ${USER_ID} -g ${USER_GROUP_ID} jenkins

USER jenkins

WORKDIR $JENKINS_HOME
