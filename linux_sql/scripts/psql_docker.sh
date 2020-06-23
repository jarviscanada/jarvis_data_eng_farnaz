#!/bin/bash
# setup arguments
operation=$1 
password=$2 
username= $3
#set password for default user `postgres`
export PGPASSWORD=$password

#checks docker_deamon status, if not running, start docker
sudo launchctl status docker || sudo launchctl start docker

# Creates the docker...It gives an error it is already created 
if [[ $operation = "create" ]]; then
    if [[ $(docker container ls -a -f name=jrvs-psql | wc -l) -ge 2 ]]; then 
        echo "ERROR!...The container is already created"
        exit 1
    fi

    if [[ ! $username ]] || [[ ! $password ]]; then 
        echo "ERROR!...Please enter correct number of arguements"
        exit 1
    fi

    sudo docker volume create pgdata
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=$password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 $username
    exit 0

fi

if [[ ! $(docker ps -a | grep "jrvs-psql") ]]; then 
   echo "ERROR!...The container is not created yet."
   exit 1
fi

if [[ $operation = "start" ]]; then
   docker container start jrvs-psql
   exit 0

elif [[ $operation = "stop" ]]; then
   docker container start jrvs-psql
   docker container stop jrvs-psql
   exit 0
else
#Gives an error if type of the requested operation is not defined
	echo "ERROR!...Command is invalid... Please enter valid command:start,stop or create"
	exit 1
fi