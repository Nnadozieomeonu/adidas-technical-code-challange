package com.adidas.userexperience.subscriptionservice.service;


import com.adidas.userexperience.subscriptionservice.dto.EmailMessage;
import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;

import java.util.List;

public interface SubscriptionService {

    public EmailSubscription save(EmailSubscriptionDto subscription);

    public EmailSubscription cancelSubscription(int id);

    public List<EmailSubscription> findAll();

    public EmailSubscription getSubscription(int id);

    public String sendEmail(EmailMessage emailMessage, EmailSubscription emailSubscription) throws Exception;


}
