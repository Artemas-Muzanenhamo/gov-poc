# gov-poc

[![CircleCI](https://circleci.com/gh/Artemas-Muzanenhamo/gov-poc/tree/develop.svg?style=svg)](https://circleci.com/gh/Artemas-Muzanenhamo/gov-poc/tree/develop)

# About
This is a proof of concept to demo government services using the microservice architecture with spring boot and spring cloud.


# Architecture
<p align="center">
  <img src="https://user-images.githubusercontent.com/29547780/33339079-e155e812-d46f-11e7-8777-0d2ceb6b5c26.png">
</p>

## Identity-Service

This Service will hold all National ID details for every citizen of the
country.

## License-Service

This Service will hold all License details. However, each person
that has a license MUST hold an ID. So there are some scenarios 
when one needs to apply for a license but won't be able to if they
fail to present an ID. :thinking:

So when a user without an ID attempts to apply for a License, the 
system itself should not allow the user to achieve this as that 
would be illegal :smirk:

## Discovery-Server

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