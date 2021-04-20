package com.adidas.userexperience.subscriptionservice.client;

import com.adidas.userexperience.subscriptionservice.config.FeignClientInterceptor;
import com.adidas.userexperience.subscriptionservice.dto.EmailMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "email-service", name = "email-service", configuration = FeignClientInterceptor.class)
public interface EmailClient {

    /**
     *
     * @param emailMessage
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/email/", consumes = "application/json", headers = {"Authorization: Bearer "})
    String sendEmail(EmailMessage emailMessage);


}
