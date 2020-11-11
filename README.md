## Empty server

As 'root':

```
apt-get -y update && apt-get -y upgrade && apt-get install git
git clone https://github.com/kilpio/jgit
cd jgit
./deploy/new_jenkins_server.sh
su - jenkins
```
Now as 'jenkins':
Test if docker is ok
```
docker run hello-world
docker image rm -f hello-world
```

^d

as root
./build-image-kilpio-jenkins.sh