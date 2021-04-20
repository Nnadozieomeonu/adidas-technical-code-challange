package com.adidas.userexperience.subscriptionservice.service;


import com.adidas.userexperience.subscriptionservice.dto.EmailMessage;
import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;

import java.util.List;

public interface SubscriptionService {

    /**
     *
     * @param subscription
     * @return
     */
    EmailSubscription save(EmailSubscriptionDto subscription);

    /**
     *
     * @param id
     * @return
     */
    EmailSubscription cancelSubscription(int id);

    /**
     *
     * @return
     */
    List<EmailSubscription> findAll();

    /**
     *
     * @param id
     * @return
     */
    EmailSubscription getSubscription(int id);

    /**
     *
     * @param emailMessage
     * @param emailSubscription
     * @return
     * @throws Exception
     */
    String sendEmail(EmailMessage emailMessage, EmailSubscription emailSubscription) throws Exception;


}
