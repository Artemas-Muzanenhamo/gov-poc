# Gov-POC

CircleCi: [![CircleCI](https://circleci.com/gh/Artemas-Muzanenhamo/gov-poc/tree/develop.svg?style=svg)](https://circleci.com/gh/Artemas-Muzanenhamo/gov-poc/tree/develop)
SonarQube: ![SonarQube](https://sonarcloud.io/api/project_badges/measure?project=com.gov-poc%3Agov-poc&metric=coverage)

# About
This is a proof of concept to demo government services using the Microservice Architecture with spring boot and spring cloud. We will not really dive into the Authentication aspect at first but we will look at how data should flow within a Microservice Architecture.

# Pre-Requisites
 * Java 11
 * Gradle
 * Docker

# Starting the application

* build the project via gradle `./gradlew -x test build`
* run `docker-compose -f ./docker-compose.yml up -d`
* you can see the status for each container by executing `docker ps`
* or you can use a docker manager like [portainer.io](https://www.portainer.io/) and manage containers from there.
* If you need the UI for this application please look at [gov-poc-ui](https://github.com/Artemas-Muzanenhamo/gov-poc-ui).
* to stop all containers you can run `docker-compose -f ./docker-compose.yml down`


# Architecture
<p>
  <img src="https://user-images.githubusercontent.com/29547780/61170379-379d2600-a560-11e9-8e7e-e48a55221488.jpg">
</p>

# Services

The idea here is that we can have as many services as we want behind our Registry Server(Discover-Server) which will allow our Services to register and de-register themselves to the Registry Server. Also Services can still communicate between themselves behind the Registry Server without any issues. 

## Identity-Service

This Service will hold all National ID details for every citizen of the
country.

### Identity-Service API
http://localhost:9999/identity-service/swagger-ui.html#/ :point_left: If you are running all services at once on Docker.
http://localhost:8080/identity-service/swagger-ui.html#/ :point_left: If you are running this service on its own.

## License-Service

This Service will hold all License details. However, each person
that has a license MUST hold an ID. So there are some scenarios 
when one needs to apply for a license but won't be able to if they
fail to present an ID. :thinking:

So when a user without an ID attempts to apply for a License, the 
system itself should not allow the user to achieve this as that 
would be illegal :smirk: .

### License-Service API
http://localhost:9999/license-service/swagger-ui.html#/ :point_left: If you are running all services at once on Docker.
http://localhost:8080/license-service/swagger-ui.html#/ :point_left: If you are running this service on its own.

# Infrastructure

## Discovery-Server (Eureka)

This is a [Service Registry(Eureka)](https://github.com/spring-cloud/spring-cloud-netflix) which will allow our Services to be 
able to register and de-register themselves to the Service Registry. Instead of all traffic making requests to the 
Services directly by calling an explicit IP address e.g. http://192.0.33.1:8080, we will let our Service Registry worry
about that and take care of that for you. Suppose, in the cloud, I had scaled up my services so it won't be just one 
Service running, then, how will you know the IP of the other Services I have scaled?? In this case, when a Service
registers itself to the Service Registry, you can make calls to any service my referring to the service name within the 
URL. For example, if you wanted to access the License-Service via the Service Registry, your URL would look like, 
`http://license-service:9999/licenses`. The Service Registry will take care of translating the `license-server` to match 
the relevant IP address/addresses if we had scaled up our Services. So it kinda handles some load balancing for us behind 
the scenes. :smirk:

## API Gateway (Zuul)

The API Gateway is a fully managed service that makes it easy for developers to create, publish, maintain, monitor, and 
secure APIs at any scale. We will be using the Zuul proxy as our API Gateway.
[Zuul](https://github.com/Netflix/zuul) is the front door for all requests from devices and web sites to the backend of the Netflix streaming application. 
As an edge service application, Zuul is built to enable dynamic routing, monitoring, resiliency and security. It also 
has the ability to route requests to multiple Amazon Auto Scaling Groups as appropriate.
You can access the Gateway can be accessed on: `http://localhost:9999`. Should you wish to 
access any of the services via the gateway you can just access them as below:
`http://localhost:9999/license-service/identies` for the `license-service` or
`http://localhost:9999/identity-service/identities` for the `identity-service`.

## Configuration Server

The Configuration Server will use `Spring-Cloud-Config` to support externalised (externalized :smirk: American..) configuration in a distributed system. With the Configuration Server we will have a central place to manage external properties for applications across all environments. Why all this Whyyyyy??!!!! Well if you think about scalability of your services. Suppose we now have 10 more `License-Service` services scaled up and we want to change some properties within those services. How can we make one change and have that update all the other services??? :thinking: .... The Configuration Server can handle this for us simply by us externalizing our configuration.

# Testing
## CDC testing

<p align="center">
  <img src="https://user-images.githubusercontent.com/29547780/35231146-eb6550d6-ff8f-11e7-8546-25646cd138d9.png">
</p>

`Pact` is what I'm using to test and verify API between consumers and providers.
See [here](https://github.com/pact-foundation/pact-ruby/wiki/FAQ#how-does-pact-differ-from-vcr) to find out more about how Pact works.

## Unit testing
Each of the classes in these services are writter with TDD and so there is unit testing for each functionality introduced to all these
services.