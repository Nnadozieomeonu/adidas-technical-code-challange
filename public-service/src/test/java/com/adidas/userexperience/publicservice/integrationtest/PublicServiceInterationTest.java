package com.adidas.userexperience.publicservice.integrationtest;

import com.adidas.userexperience.publicservice.PublicServiceApplication;
import com.adidas.userexperience.publicservice.dto.EmailSubscriptionDto;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.sql.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PublicServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PublicServiceInterationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Local server port
     */
    @LocalServerPort
    private int port;

    /**
     *
     * @return
     */
    private String getRootUrl() {
        return "http://localhost:" + port+"/api/v1/public/service";
    }

    @Test
    @Order(1)
    public void contextLoads() {

    }

    /**
     * Public Email Subscriber test
     */
    @Test
    @Order(2)
    public void testCreateSubscription() {
        EmailSubscriptionDto subscription = new EmailSubscriptionDto();
        subscription.setEmail("test@gmail.com");
        subscription.setFirstName("testName");
        subscription.setGender("Male");
        subscription.setDob(Date.valueOf("2000-04-28"));
        subscription.setConsent(true);
        subscription.setToken(UUID.randomUUID().toString());
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/", subscription, String.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertThat(postResponse.getBody()).isEqualTo("You've successfully registered to get updates about adidas. " +
                "Check your email for news about upcoming releases.");
    }

}
