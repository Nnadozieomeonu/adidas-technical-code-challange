package com.adidas.userexperience.publicservice.controller;


import com.adidas.userexperience.publicservice.dto.EmailSubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/public/service")
@RequiredArgsConstructor
@Validated
public class PublicController {

    private final StreamBridge streamBridge;


    @PostMapping
    public ResponseEntity<String> index(@Valid @RequestBody EmailSubscriptionDto emailSubscriptionDto){
        final boolean isPublished = streamBridge.send("notificationEventSupplier-out-0", emailSubscriptionDto);
        if (!isPublished) {
            // TODO: Need to implement a retry, fallback & notification
            System.out.println("Failed to publish message: "+ emailSubscriptionDto.toString());
        }
        return ResponseEntity.ok("You've successfully registered to get updates about adidas. " +
                "Check your email for news about upcoming releases.");
    }
}
