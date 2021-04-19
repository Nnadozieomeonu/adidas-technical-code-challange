package com.adidas.userexperience.subscriptionservice.controller;


import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import com.adidas.userexperience.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/subscription/")
@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public EmailSubscription createSubscripton(@RequestBody EmailSubscriptionDto emailSubscription){
        return subscriptionService.save(emailSubscription);
    }

    @GetMapping
    public List<EmailSubscription> allSubscription(){
        return subscriptionService.findAll();
    }

    @GetMapping("{id}")
    public EmailSubscription getSubscriptionById(@PathVariable("id") int id){
        return subscriptionService.getSubscription(id);
    }

    @GetMapping("cancel/{id}")
    public EmailSubscription cancelASubscription(@PathVariable("id") int id){
        return subscriptionService.cancelSubscription(id);
    }


}
