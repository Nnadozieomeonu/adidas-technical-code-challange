## Adidas Technical Challenge [Documentation](https://app.swaggerhub.com/apis/Nnadozieomeonu/Adidas-Technical-Challenge/1.0.0)

![alt adidas](https://github.com/Nnadozieomeonu/lacecart/blob/master/image001.jpg?raw=true)

This are my notes on  how to set up the under listed service, technology for testing, my microservice architecture comprises of the below microservice services.

[Spring-boot Framework](http://start.spring.io).
This was the ORM Used.

[Eureka Discovery Service](http://start.spring.io).

I used Eureka discovery service to register all the microservices on in my cluster

[API Gateway Service](http://start.spring.io).

I used API gateway to route traffic to the appriopriate service based on the api route

[Service Authorization service](http://start.spring.io).

I used this service to secure every non public micorservice

[Mysql Database Server](http://start.spring.io).

Mysql used to as the RDMS for the authentication service

[Rabbitmq Queuing Service](http://start.spring.io).

This was an event-driven microservice, so the public service here was the producer with recieves
request from the client application, provide feedback in almost realtime and sends the request via a cloud bus to the consumer which is subscription service
to persist and send a confirmation email to the customer.

[FeignClient](http://start.spring.io).

This was used in the subscription service to send RestTemplate request to the email service to dispatch email.

[H2 DB](http://start.spring.io).
The subscription service used the H2 database as an in memory database

[JUnit](http://start.spring.io).
Used to write unit test for the public, subscribtion and email service

[Hibernate](http://start.spring.io).
This was the ORM Used.

[Public Service](http://start.spring.io).
This was a non authenticated service that recievces request from the client and sends to the secured services via event bus (rabbitmq)

[Subscription Service](http://start.spring.io).
This is the main service that handles business logic and database persistence

[Email Service](http://start.spring.io).
This service is responsible for sending email to the customer

[Docker](http://docker.com).
Containerization Application 

[GIT](http://git.com).
Source code repository

[InteliJ](http://jetbrains.com).
Code editor

**Other artifacts would have implemented if time permits**
[ELK](http://start.spring.io).
*Elastic Search*
*Logstash*
*Kibana*
To implement Distributed logging.
[Cloud Configuration server with GIT](http://start.spring.io).
[Hystrix Dashboard](http://start.spring.io).





## Manual Set-up Guide

1. Clone The master branch  using the below command  `` git clone https://github.com/Nnadozieomeonu/adidas-technical-code-challange.git ``
2. make sure you have docker run on your machine [Docker website](https://www.docker.com/)
3. Start up your rabbitmq by running this docker command on your terminal  `` docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management ``  i used rabbitmq insted of kafaka cos of this was  quick prototype implementation.
4. Start up your mysql by running this docker command on your terminal `` docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag ``
5. import the directory into your inteliij IDE.
6. import maven dependency for the project
7. configure the spring start up configuration
8. Start up the discovery server first
9. next the boot up your API gateway service
10. Start up the authorization service
11. Start up the public, email, subscription service

Application Configuration file for individual service

**Public Service**

*resources/application.yml*

 ```  
spring:  
  cloud:  
    stream:  
      source: notificationEventSupplier  
      bindings:  
        notificationEventSupplier-out-0:  
          destination: notification-events  
    loadbalancer:  
      ribbon:  
        enabled: false  
 rabbit:  
      bindings:  
        processTxnRequest-out-0:  
          producer:  
            batchingEnabled: true  
            batchSize: 500  
            batchBufferLimit: 100000  
            batchTimeout: 5000  
  rabbitmq:  
    host: localhost  
    username: guest  
    password: guest  
    port: 5672  
 application:  
    name: public-service  
  
logging:  
  level:  
    org:  
      springframework:  
        amqp: DEBUG  
  
server:  
  port: 0  
  
  
eureka:  
  instance:  
    instance-id: ${spring.application.name}-${random.uuid}  
  client:  
    service-url:  
      default-zone: http://localhost:8761/eureka/
    fetch-registry: true  
 register-with-eureka: true
 
``` 

**Subscrbtion Service**

*resources/application.yml*

```
eureka:  
  client:  
    service-url:  
      defaultZone: http://localhost:8761/eureka/ 
  instance:  
    instance-id: ${spring.application.name}-${random.uuid}  
  
server:  
  port: 0  
  
spring:  
  cloud:  
    stream:  
      function:  
        definition: notificationEventSupplier  
      bindings:  
        notificationEventSupplier-in-0:  
          destination: notification-events  
          group: notification-events  
          consumer:  
            batch-mode: true  
  application:  
    name: subscription-service  
  datasource:  
    url: jdbc:h2:mem:testdb  
    username: sa  
    password:  
    driver-class-name: org.h2.Driver  
 initialization-mode: always  
 jpa:  
    hibernate:  
      naming:  
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl  
 ddl-auto: create-drop  
 properties:  
      hibernate:  
        dialect: org.hibernate.dialect.H2Dialect  
        show_sql: true  
        format_sql: true  
  
  
security:  
  oauth2:  
    resource:  
      token-info-uri: http://localhost:9090/oauth/check_token  
    client:  
      client-id: mobile  
      client-secret: pin  
  
  
feign:  
  client:  
    config:  
      email-service:  
          logger-level: full
```


**Email Notification Service**

In the notification service i use mailtrap.io, you can your mailtrap.io configuration to test this service

![alt adidas](https://github.com/Nnadozieomeonu/lacecart/blob/master/Screen%20Shot%202021-04-19%20at%207.27.02%20PM.png?raw=true)

*resources/application.yml*

```

spring:  
  mail:  
    host: smtp.mailtrap.io  
    port: 2525  
 username: 94e26234716d84  
    password: 2d2245724f1aed  
    from: "Adidas Store <no-reply@adidas.com>"  
 cloud:  
    loadbalancer:  
      ribbon:  
        enabled: false  
 rabbitmq:  
    host: localhost  
    username: guest  
    password: guest  
    port: 5672  
 application:  
    name: email-service  
  
  
security:  
  oauth2:  
    resource:  
      token-info-uri: http://localhost:9090/oauth/check_token  
    client:  
      client-id: mobile  
      client-secret: pin  
  
server:  
  port: 0  
  
  
eureka:  
  instance:  
    instance-id: ${spring.application.name}-${random.uuid}  
  client:  
    service-url:  
      default-zone: http://localhost:8761/eureka/
    fetch-registry: true  
 register-with-eureka: true
```

**Authorization Service**

In the notification service i use mailtrap.io, you can your mailtrap.io configuration to test this service

*resources/application.properties*

```
server.port=0  
spring.application.name=auth-service  
spring.datasource.url=jdbc:mysql://localhost:3306/database?autoReconnect=true&useSSL=false  
spring.datasource.username=root  
spring.datasource.password=password  
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver  
spring.datasource.initialization-mode=always  
  
management.endpoints.web.exposure.include=*  
  
spring.jpa.hibernate.ddl-auto=create-drop  
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl  
spring.jpa.properties.hibernate.show_sql=true  
spring.jpa.properties.hibernate.show_sql.format_sql=true  
  
eureka.instance.instance-id=${spring.application.name}-${random.uuid}  
  
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/  
eureka.client.fetch-registry=true  
eureka.client.register-with-eureka=true
```


## Docker Set-up Guide
I implemented docker set up for the microservice, how due to time constriants i was not able to completely set it up, during docker compose set up i encounter the below error, which got me stuck in my implementation. see below error message gotten on the Authorization service, all other service boots up with docker-compose;

**Docker Error Encountered**

```

[output clipped, log limit 1MB reached]
_______
ERROR: Service 'authorization-service' failed to build

```


1. Clone The master branch  using the below command  `` git clone https://github.com/Nnadozieomeonu/adidas-technical-code-challange.git ``
2. make sure you have docker run on your machine [Docker website](https://www.docker.com/)
3. Navigate to the clone directory  `` cd ~/{path}/adidas-technical-code-challange ``
4. Type the below command to build your docker images `` docker-compose build ``
5. Upon successful build process you can run the service  `` docker-compose up` ``
6. To bring down the service use the below command `` docker-compose down ``


[API On Swagger.io Documentation](https://app.swaggerhub.com/apis/Nnadozieomeonu/Adidas-Technical-Challenge/1.0.0)

I setup the a local spring boot application api documentation using springfox swagger Docket dependecy, however for the authenticated services it was not made public. so i dropped the service and documented a rough documentation online [API On Swagger.io Documentation](https://app.swaggerhub.com/apis/Nnadozieomeonu/Adidas-Technical-Challenge/1.0.0)

## Microservice architecture diagram

## Jenkins Pipeline Sketch

## Jenkins Pipeline Sketch

## Application Request Screenshot







![alt adidas](https://github.com/Nnadozieomeonu/lacecart/blob/master/image002.png?raw=true)
