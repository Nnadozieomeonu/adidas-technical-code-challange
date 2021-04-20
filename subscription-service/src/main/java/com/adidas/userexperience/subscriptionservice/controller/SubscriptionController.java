package com.adidas.userexperience.subscriptionservice.controller;


import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import com.adidas.userexperience.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/subscription/")
@RestController
@RequiredArgsConstructor
@Validated
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     *
     * @param emailSubscription
     * @return
     */
    @PostMapping
    public ResponseEntity<EmailSubscription> createSubscripton(@Valid @RequestBody EmailSubscriptionDto emailSubscription)
    {
        return ResponseEntity.ok(subscriptionService.save(emailSubscription));
    }

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<EmailSubscription>> allSubscription(){
        return ResponseEntity.ok(subscriptionService.findAll());
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public ResponseEntity<EmailSubscription> getSubscriptionById(@PathVariable("id") int id){
        return ResponseEntity.ok(subscriptionService.getSubscription(id));
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("cancel/{id}")
    public ResponseEntity<EmailSubscription> cancelASubscription(@PathVariable("id") int id){
        return ResponseEntity.ok(subscriptionService.cancelSubscription(id));
    }


}
