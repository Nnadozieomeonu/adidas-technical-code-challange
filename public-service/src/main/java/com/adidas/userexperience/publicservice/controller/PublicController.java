package com.adidas.userexperience.publicservice.controller;


import com.adidas.userexperience.publicservice.dto.EmailSubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/public/")
@RequiredArgsConstructor
@Validated
public class PublicController {

    private final StreamBridge streamBridge;

    @PostMapping
    public ResponseEntity<String> index(@Valid @RequestBody EmailSubscriptionDto emailSubscriptionDto){
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
        emailSubscriptionDto.setToken(token);
        streamBridge.send("subscriptionEventSupplier-out-0", emailSubscriptionDto);
        return ResponseEntity.ok("You've successfully registered to get updates about adidas. " +
                "Check your email for news about upcoming releases.");
    }
}
