package com.adidas.userexperience.subscriptionservice.client;

import com.adidas.userexperience.subscriptionservice.dto.EmailMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "email-service", name = "email-service")
public interface EmailClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/email", consumes = "application/json")
    String sendEmail(EmailMessage emailMessage);


}
