package com.adidas.userexperience.subscriptionservice.service.impl;

import com.adidas.userexperience.subscriptionservice.client.EmailClient;
import com.adidas.userexperience.subscriptionservice.dto.EmailMessage;
import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import com.adidas.userexperience.subscriptionservice.exceptionhandlers.ResourceNotFoundException;
import com.adidas.userexperience.subscriptionservice.repository.SubscriptionRepository;
import com.adidas.userexperience.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailClient emailClient;

    /**
     *
     * @param subscription
     * @return
     */
    public EmailSubscription save(EmailSubscriptionDto subscription){
        EmailSubscription emailSubscription = new EmailSubscription();
        EmailMessage emailMessage = new EmailMessage();
        emailSubscription.setEmail(subscription.getEmail());
        emailSubscription.setConsent(subscription.isConsent());
        emailSubscription.setDob(subscription.getDob());
        emailSubscription.setGender(subscription.getGender());
        emailSubscription.setFirstName(subscription.getFirstName());
        emailSubscription = subscriptionRepository.save(emailSubscription);
        try {
            this.sendEmail(emailMessage,emailSubscription);
            System.out.println("Sending to "+emailMessage.toString()+" Successful");
        } catch (Exception e) {
            System.out.println("Sending to "+emailMessage.toString()+" failed");
            e.printStackTrace();
        }
        return emailSubscription;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public EmailSubscription cancelSubscription(int id) {
        EmailSubscription emailSubscription = this.getSubscription(id);
        emailSubscription.setConsent(false);
        return emailSubscription;
    }

    /**
     *
     * @return
     */
    @Override
    public List<EmailSubscription> findAll() {
        return subscriptionRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public EmailSubscription getSubscription(int id) {
        return subscriptionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Email subscription not found"));
    }

    /**
     *
     * @param emailMessage
     * @param emailSubscription
     * @return
     * @throws Exception
     */
    @Override
    public String sendEmail(EmailMessage emailMessage, EmailSubscription emailSubscription) throws Exception {
        emailMessage.setSubject("You're in. Welcome to adidas.");
        emailMessage.setName(emailSubscription.getFirstName());
        emailMessage.setEmail(emailSubscription.getEmail());
        emailMessage.setMessage("Dear " +(emailSubscription.getFirstName() == "" ? "Customer" : emailSubscription.getFirstName().toUpperCase())+" \n\n"+
                "Thank you for signing up, start your journey today. \n\n" +
                "Welcome to the wonderful world of adidas. We are pioneers in sport, fashion and performance, which you are soon to witness. \n\n" +
                "Thanks for joining, we will be happy to show you around.");
        try{
            emailClient.sendEmail(emailMessage);
            return "Email sent successfully to "+emailMessage.toString();
        } catch (Exception e){
            //Email will not be sent out form the public url
            e.printStackTrace();
        }
        return "Email failed to send "+emailMessage.toString();
    }

}
