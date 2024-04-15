# Configuration Server

- Application to hold all configuration for all microservices.

## To the app locally

`./gradlew bootR --args=--spring.profiles.active=native`

## Docker build

`docker build . -t gov-poc-configuration-server <DOCKER_HUB_USER_ACCOUTN>/gov-poc-configuration-server`

## Docker pull image

`docker pull <DOCKER_HUB_USER_ACCOUTN>/gov-poc-configuration-server`
