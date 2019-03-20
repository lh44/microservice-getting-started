### What will this project helps you with?
This helps you with fundamentals of building a microservice and deploying it in as a docker container

### Why Microservices?
* They're polyglot - Different developers can deplop different part of your app in different langauges/frameworks
* Distributed deployment
* Scaling only the most heavily used bits of your app
* Zero downtime deployments and upgrades(Blue-Green strategy)
* Upgrades won't affect the entire application
* Easier to manage smaller applications, faster to develop and deploy features
* Better seperation of concerns

### Why not?
* Complex to manage when the number of microservices becomes higher, unless proper deployment pipelines are setup
* Need additional infrastructure to deploy supporting services
* Introduces overhead because of routing through multiple services

### How to find the balance?
* Starting with a minimum set of microservices which are clearly independent
* Design your business logic inside a microservice to be loosely coupled with the interfaces. Always call interfaces and inject implementations so that they can be moved to a different microservice when necessary
* Leveraging automation for testing and deployment(Jenkins pipelines, Kubernetes)
* Build frameworks and not apps, so that they can be reused across different microservices

### Why docker?
* Because containerized application deploments are the future and the future is here!
* Build once & run anywhere, so inherently cloud native
* And much more...

## About the project
### Microservice essentials
Fundamentally microservices are your normal apps but functionally unique, they're meant to do just one thing.
Since they're distributed in nature, we'll need a few supporting services, the below are a few
* Service Discovery: As I said, the Microservices are deployed in a distributed manner, across servers, across networks, with redundancy to handle fail-overs(multiple replicas), we'll need a registry to keep track of 
  - How many instances are running
  - Where are they running
  - Is it currently available to serve requests
 So this service maintains a dynamic registry of all the microservices. In this project this is powered by Netflix Eureka
 
* API Gateway: For all the reasons why you need a Service discovery, It only keeps track of which all services are running and where they're running. We'll need a single point of entry to reach individual services, and this is provided by the API Gateway service. This is powered by Netflix Zuul in our example.

### Other services needed
* A central OAuth2 based Authentication service for login. This is ideal for a microservice based system as OAuth2 offers various authentication strategies
* A central logging service, since all the application run all over the place, debugging with logs becomes impossible unless there is a single dashboard we can look at and fetch logs. ELK stack can be integrated with the docker environment to provide this feature
* There could be more

### Setting up the project
## Pre-requisites
* Docker [How to install on windows?](https://docs.docker.com/v17.09/docker-for-windows/install/) - [Enable rest API on docker](https://stackoverflow.com/questions/37854161/how-do-i-enable-the-docker-restapi-on-windows-containers)
* Maven
* Java 8

## Clone this project

> git clone https://github.com/lh44/microservice-getting-started.git

## Update the local IP of the services
For each of the three services, update the src/main/resources/application.yml file

```yaml
server:
  port: 8081
eureka:
  instance:
    hostname: 192.168.43.116 # Change this to your private IP
    non-secure-port: ${server.port}
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://192.168.43.116:8761/eureka} # Change this to your private IP
spring:
  application:
    name: date-service
```

## Do a maven clean install

> cd microservice-getting-started
  mvn clean install
  
## Clean install should create docker images for all three applications

> docker images

```  
  REPOSITORY                                       TAG                 IMAGE ID            CREATED             SIZE
  microservice.getting.started/api-gateway         latest              06ca5658c83f        26 hours ago        146MB
  microservice.getting.started/date-service        latest              bb82fed9fde5        26 hours ago        143MB
  microservice.getting.started/service-discovery   latest              dde3dfd8291f        26 hours ago        148MB
  openjdk                                          8-jdk-alpine        e9ea51023687        12 days ago         105MB
```

## Start your application containers

  > docker run -d -p 8761:8761 microservice.getting.started/service-discovery
  > docker run -d -p 8090:8090 microservice.getting.started/api-gateway
  > docker run -d -p 8081:8081 microservice.getting.started/date-service
  
## Make sure the containers are successfully created
> docker ps

```
  CONTAINER ID        IMAGE                                            COMMAND                  CREATED             STATUS              PORTS                    NAMES
45b52174177a        microservice.getting.started/date-service        "java -Djava.securit…"   26 hours ago        Up 26 hours         0.0.0.0:8081->8081/tcp   nifty_booth
0fe35794d1d3        microservice.getting.started/api-gateway         "java -Djava.securit…"   26 hours ago        Up 26 hours         0.0.0.0:8090->8090/tcp   flamboyant_easley
21d3ae800cea        microservice.getting.started/service-discovery   "java -Djava.securit…"   26 hours ago        Up 26 hours         0.0.0.0:8761->8761/tcp   nostalgic_stallman
```

## Check the Service Discovery dashboard
link - http://localhost:8761

## Access your app via the API Gateway port: 8090
link - http://localhost:8090/date-service/date
