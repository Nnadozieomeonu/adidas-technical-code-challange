package com.adidas.userexperience.subscriptionservice.repository;

import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<EmailSubscription, Integer> {
    public EmailSubscription findById(int orderId);
}
