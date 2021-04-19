package com.adidas.userexperience.subscriptionservice;

import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import com.adidas.userexperience.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class SubscriptionServiceApplication {

	@Autowired
	private SubscriptionService subscriptionService;

	@Bean
	public Consumer<EmailSubscriptionDto> subscriptionEventSupplier(){
		return subscription -> {
			System.out.println(subscription);
			EmailSubscription emailSubscription = subscriptionService.save(subscription);
			System.out.println("Saved Subscription \n-----------"+emailSubscription+"\n -------------");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}

}
