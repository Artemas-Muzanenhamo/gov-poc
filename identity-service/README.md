# Identity Service

- This microservice allows user to create an identity account.

## Pre-requisites
You will need mongo running locally

## To run the app locally

Go to the configuration-server and run it locally:
1. `cd ../configuration-server`
2. `./gradlew bootR`

Run the identity-service locally
1. `./gradlew bootR --args=--spring.profiles.active=dev`

The app has a DB seeder and will insert some dummy data. You can access the app locally via the following endpoint:
`GET: localhost:8080/identities`

## API Documentation

To view the application API docs, visit
* `http://localhost:8080/swagger-ui/index.html`

## Docker Build

`docker build . -t gov-poc-identity-service <DOCKER_HUB_USER_ACCOUTN>/gov-poc-identity-service`

## Docker Pull Image

`docker pull <DOCKER_HUB_USER_ACCOUTN>/gov-poc-identity-service`
