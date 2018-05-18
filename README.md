# Gov-POC

CircleCi: [![CircleCI](https://circleci.com/gh/Artemas-Muzanenhamo/gov-poc/tree/develop.svg?style=svg)](https://circleci.com/gh/Artemas-Muzanenhamo/gov-poc/tree/develop)

# About
This is a proof of concept to demo government services using the Microservice Architecture with spring boot and spring cloud. We will not really dive into the Authentication aspect at first but we will look at how data should flow within a Microservice Architecture.


# Architecture
<p align="center">
  <img src="https://user-images.githubusercontent.com/29547780/33390496-ef763280-d52d-11e7-80c3-8aaf41c3d5b0.png">
</p>

# Services

The idea here is that we can have as many services as we want behind our Registry Server(Discover-Server) which will allow our Services to register and de-register themselves to the Registry Server. Also Services can still communicate between themselves behind the Registry Server without any issues. 

## Identity-Service

This Service will hold all National ID details for every citizen of the
country.

### Identity-Service API
URL|Request Method|Request Body| Description
:---:|:---:|:---:|:---:
`identity-service:8080/identities`|**POST**|**Identity**|Adds an Identity to the Identity-Service.
`identity-service:8080/identities/name`|**POST**|**{"name": ${name}}**|Retrieves a List of Identities given a name.
`identity-service:8080/identities`|**GET**||Retrieves all Identities from the Identity-Service.
`identity-service:8080/identities`|**PUT**|**Identity**|Updates an Identity in the Identity-Service.
`identity-service:8080/identities`|**DELETE**|**Identity**|Removes an Identity from the Identity-Service.

## License-Service

This Service will hold all License details. However, each person
that has a license MUST hold an ID. So there are some scenarios 
when one needs to apply for a license but won't be able to if they
fail to present an ID. :thinking:

So when a user without an ID attempts to apply for a License, the 
system itself should not allow the user to achieve this as that 
would be illegal :smirk: .

### License-Service API
URL|Request Method|Request Body| Description
:---:|:---:|:---:|:---:
`license-service:8081/licenses`|**POST**|**License**|Adds a License to the License-Service.
`license-service:8081/licenses`|**GET**||Retrieves all Licenses from the License-Service.
`license-service:8081/licenses`|**PUT**|**License**|Updates License data in the License-Service.
`license-service:8081/licenses`|**DELETE**|**License**|Removes License data from the License-Service.

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

## Pact

<p align="center">
  <img src="https://user-images.githubusercontent.com/29547780/35231146-eb6550d6-ff8f-11e7-8546-25646cd138d9.png">
</p>

The project uses [Pact](https://docs.pact.io/) in order to test communication between microservices. 

### Scenario

Suppose a person wants to get a new drivers' license. They will be required to present their identification to verify that they are who they say they are and also that they are legally allowed to 
acquire a drivers' license (By checking their age of course :smirk: ). Instead of the staff at the `License` company calling the `Identity` company every time someone wants to get a new license, we thought we could have our microservices 
doing that for us. In this case, we wanted the `License` company staff to enter the license applicant's unique ID number in the `License-Service` and then let the `License-Service` talk to the `Identity-Service` to retrieve the applicant's ID details.

#### Implementing this scenario using TDD

We are all TDD advocates right??... :smirk: Well.... to test this is not a walk in the park type of test. Traditionally, we would have integration tests in this scenario or even end-to-end tests in some cases where we would need both our `License-Service` microservice and our `Identity-Service` microservice up and running and maybe a few other things our microservices would depend on in order to function properly and give us the 
expected outcomes we require in our tests. 

**TRUSTWORTHY** - The good thing is that when all the resources( including infrastructure dependencies) are up and running, these tests are trustworthy and work as expected.

~~**CHEAP**~~ - These tests are really expensive to write, as you not only code but you'll need to agree with other teams working on resources that your service depend on (meaning more meetings !!) and that could take a long time.

~~**FAST**~~ - Another point is that these tests are not as fast as you might expect. In fact, they will, in most cases, execute in order and suppose you had 50 resources( other dependencies or services) your service depended on to be able to pass your integration test... You wouldn't want to get that test wrong !!

~~**RELIABLE**~~ - Because there are so many moving parts/resources, these tests aren't really relaiable as when one resource doesn't work properly, almost everything will break and give you a lot of painful red tests with
no explanation as to why they would be breaking or perhaps you will receive stack traces of problems that have nothing to do with the actual code you are testing. 

### Solutions

**Wiremock**

...

**Spring-Cloud-Contracts**

[Link is Here](https://cloud.spring.io/spring-cloud-contract/)

**Pact**

#### What is Pact?
The Pact family of frameworks provide support for Consumer Driven Contracts testing.

#### Consumer Driven Contracts
A `Contract` is a collection of agreements between a client _(Consumer)_ and an API _(Provider)_ that describes the interactions that can take place between them.

Consumer Driven Contracts is a pattern that drives the development of the Provider from its Consumer's point of view. It is TDD for services.

`Pact` is a testing tool that helps you write Contracts, and guarantees those Contracts are satisfied.

**Pros for using Pact**
