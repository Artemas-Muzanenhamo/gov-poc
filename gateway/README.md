# Gateway

- This application serves as an API Gateway which is a proxy that will 
allow traffic on a particular port to be served by the other microservices.

- For example, mobile devices could be redirected by this gateway to be served
by different microservices whereas desktop traffic could be served as seen
in the architecture.

## Docker build

`docker build . -t gov-poc-gateway <DOCKER_HUB_USER_ACCOUTN>/gov-poc-gateway`

## Docker pull image

`docker pull <DOCKER_HUB_USER_ACCOUTN>/gov-poc-gateway`