package com.adidas.userexperience.publicservice.controller;


import com.adidas.userexperience.publicservice.dto.EmailSubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class PublicController {

    private final StreamBridge streamBridge;

    @PostMapping
    @ResponseStatus
    public String index(@RequestBody EmailSubscriptionDto emailSubscriptionDto){
        streamBridge.send("subscriptionEventSupplier-out-0", emailSubscriptionDto);
        return "You've successfully registered to get updates about adidas. " +
                "Check your email for news about upcoming releases.";
    }
}
