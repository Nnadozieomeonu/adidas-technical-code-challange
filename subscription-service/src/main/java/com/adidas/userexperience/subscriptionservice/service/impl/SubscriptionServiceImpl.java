package com.adidas.userexperience.subscriptionservice.service.impl;

import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import com.adidas.userexperience.subscriptionservice.repository.SubscriptionRepository;
import com.adidas.userexperience.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public EmailSubscription save(EmailSubscriptionDto subscription){
        EmailSubscription emailSubscription = new EmailSubscription();
        emailSubscription.setEmail(subscription.getEmail());
        emailSubscription.setConsent(subscription.isConsent());
        emailSubscription.setDob(subscription.getDob());
        emailSubscription.setGender(subscription.getGender());
        emailSubscription.setFirstName(subscription.getFirstName());
        return subscriptionRepository.save(emailSubscription);
    }

    @Override
    public EmailSubscription cancelSubscription(int id) {
        EmailSubscription emailSubscription = this.getSubscription(id);
        emailSubscription.setConsent(false);
        return emailSubscription;
    }

    @Override
    public List<EmailSubscription> findAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public EmailSubscription getSubscription(int id) {
        return subscriptionRepository.findById(id);
    }
}
