# Discovery Server

- This is a discovery server which is a Eureka Server in charge of 
dynamically identifying microservice instances available for any given
given requests.
These microservices are registered to Eureka(Discovery server).

## How to run the app locally

 - `./gradlew bootR`
 - Access `http://localhost:8761/`

## Docker build

`docker build . -t gov-poc-discovery-server <DOCKER_HUB_USER_ACCOUTN>/gov-poc-discovery-server`

## Docker pull image

`docker pull <DOCKER_HUB_USER_ACCOUTN>/gov-poc-discovery-server`
