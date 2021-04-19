package com.adidas.userexperience.emailservice.controller;


import com.adidas.userexperience.emailservice.config.EmailConfiguration;
import com.adidas.userexperience.emailservice.dto.EmailMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/v1/email/")
@RequiredArgsConstructor
public class EmailController {

    @Autowired
    private EmailConfiguration emailConfiguration;

    @Value("${spring.mail.from}")
    private String from;

    @PostMapping
    public void sendEmail(@RequestBody EmailMessage emailMessage,
                          BindingResult bindingResult) throws Exception{
        if(bindingResult.hasErrors()){
            throw new ValidationException("Email message object is not valid");
        }

        /**
         *   Create a mail sender
         */
        JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
        emailSender.setHost(emailConfiguration.getHost());
        emailSender.setPort(emailConfiguration.getPort());
        emailSender.setUsername(emailConfiguration.getUsername());
        emailSender.setPassword(emailConfiguration.getPassword());

        /**
         * Create an email instance
         */
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(emailMessage.getEmail());
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getMessage());

        try{
            /**
             * Send mail
             */
            emailSender.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
