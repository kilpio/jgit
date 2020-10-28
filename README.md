## Empty server

As 'root':

```
apt-get -y update && apt-get -y upgrade && apt-get install git
git clone https://github.com/kilpio/jgit
cd jgit
./new_jenkins_server.sh
su - jenkins
```
Now as 'jenkins':
Test if docker is ok
```
docker run hello-world
docker image rm -f hello-world
```

```
git clone https://github.com/kilpio/jgit/ && cd jgit
docker-compose build
```
```
mkdir ~/jenkins_home
```


