package com.adidas.userexperience.apigatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/emailFallBack")
    public Mono<String> emailServiceFallBack(){
        return Mono.just("Email Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    @RequestMapping("/subscriptionFallBack")
    public Mono<String> subscriptionServiceFallBack(){
        return Mono.just("Subscription Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    @RequestMapping("/publicFallBack")
    public Mono<String> publicServiceFallBack(){
        return Mono.just("Public Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    @RequestMapping("/authorizationFallBack")
    public Mono<String> authorizationServiceFallBack(){
        return Mono.just("Authorization Service is taking too long to respond" +
                " or is down, Please try again later");
    }



}
