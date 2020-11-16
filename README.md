# Deploing jenkins with docker on Ubuntu empty server

## Install docker and docker-compose
Just after getting the Ubuntu machine,
run as 'root' user:

```bash
apt-get -y update && apt-get -y upgrade && apt-get install git
git clone https://github.com/kilpio/jgit
cd jgit
./deploy/new_jenkins_server.sh
su - jenkins
```

Now as 'jenkins':

Test if docker is ok

```bash
docker run hello-world
docker image rm -f hello-world
^d
```

## Building docker image

Run as root:

```bash
./build-image-kilpio-jenkins.sh
```

This will build the jgit/jenkins image based on jenkins/jenkins:latest.

## Automatically configuring Jenkins container

Start the container and configure the Jenkins keystore to enable ssl:

```bash
./preconfigure-jenkins.sh
```

This will start jenkins container and set up plugins and security settings (see ./init.groovy.d folder for initial admin password and plugins list).

Run docker logs to see if Jenkins has been started and running.

Now run

```bash
./jenkins-switch-to-ssl.sh
```

to configure the https access (default port 8080). The keystore password will be automatically saved in the .env file (KEYSTORE_PASS) on the host.

## Startting Jenkins with ssl support

Stop Jenkins container and then start it with

```bash
docker-compose -f docker-compose.yaml up -d
```

Jenkins web interface should now be avaliabe ah <https://<YOUR_HOST_IP>:8080/>
Change the default admin password!
