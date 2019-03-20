Go thru this section to get the bigger picture

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
