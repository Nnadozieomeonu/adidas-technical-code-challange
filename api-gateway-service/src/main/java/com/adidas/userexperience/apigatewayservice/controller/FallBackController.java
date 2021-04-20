package com.adidas.userexperience.apigatewayservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    /**
     * Email Service fallbacck method
     * @return
     */
    @RequestMapping("/emailFallBack")
    public Mono<String> emailServiceFallBack(){
        return Mono.just("Email Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    /**
     * Subscription service  fallbacck method
     * @return
     */
    @RequestMapping("/subscriptionFallBack")
    public Mono<String> subscriptionServiceFallBack(){
        return Mono.just("Subscription Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    /**
     * Public service  fallbacck method
     * @return
     */
    @RequestMapping("/publicFallBack")
    public Mono<String> publicServiceFallBack(){
        return Mono.just("Public Service is taking too long to respond" +
                " or is down, Please try again later");
    }

    /**
     * Authurization service fallbacck method
     * @return
     */
    @RequestMapping("/authorizationFallBack")
    public Mono<String> authorizationServiceFallBack(){
        return Mono.just("Authorization Service is taking too long to respond" +
                " or is down, Please try again later");
    }



}
