package com.adidas.userexperience.subscriptionservice;

import com.adidas.userexperience.subscriptionservice.dto.EmailSubscriptionDto;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import com.adidas.userexperience.subscriptionservice.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.function.Consumer;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
@EnableFeignClients
@EnableResourceServer
public class SubscriptionServiceApplication {

	@Autowired
	private SubscriptionService subscriptionService;

	@Bean
	public Consumer<EmailSubscriptionDto> notificationEventSupplier(){
		return subscription -> {
			System.out.println("Subscribed");
			EmailSubscription emailSubscription = subscriptionService.save(subscription);
			System.out.println("Saved Subscription and Send email \n-----------"+emailSubscription+"\n -------------");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionServiceApplication.class, args);
	}

}
