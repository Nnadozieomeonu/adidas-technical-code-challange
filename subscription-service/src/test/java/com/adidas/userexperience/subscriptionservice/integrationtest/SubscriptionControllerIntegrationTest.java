package com.adidas.userexperience.subscriptionservice.integrationtest;

import com.adidas.userexperience.subscriptionservice.SubscriptionServiceApplication;
import com.adidas.userexperience.subscriptionservice.entity.EmailSubscription;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import java.sql.Date;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SubscriptionServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SubscriptionControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port+"/api/v1/";
    }

    @Test
    @Order(1)
    public void contextLoads() {

    }

    @Test
    @Order(2)
    /**
     * Get All subscription test
     */
    public void testGetAllSubscriptions() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "subscription/",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    @Order(3)
    /**
     * FInd Subscription by Id test
     */
    public void testGetSubscriptionById() {
        EmailSubscription subscription = restTemplate.getForObject(getRootUrl() + "/subscription/1", EmailSubscription.class);
        System.out.println(subscription.getEmail());
        assertNotNull(subscription);
    }

    @Test
    @Order(4)
    /**
     * Subscribe for news letter endpoint test
     */
    public void testCreateSubscription() {
        EmailSubscription subscription = new EmailSubscription();
        subscription.setEmail("test@gmail.com");
        subscription.setFirstName("testName");
        subscription.setGender("Male");
        subscription.setDob(Date.valueOf("2000-04-28"));
        subscription.setConsent(true);
        ResponseEntity<EmailSubscription> postResponse = restTemplate.postForEntity(getRootUrl() + "/subscription/", subscription, EmailSubscription.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    @Order(5)
    /**
     * Stop Scubscription Test
     */
    public void testCancelSubscription() {
        int id = 1;
        EmailSubscription subscription = restTemplate.getForObject(getRootUrl() + "/subscription/" + id, EmailSubscription.class);
        subscription.setConsent(false);
        restTemplate.put(getRootUrl() + "/subscription/cancel/" + id, subscription);
        EmailSubscription updatedEmailSubscription = restTemplate.getForObject(getRootUrl() + "/subscription/" + id, EmailSubscription.class);
        assertNotNull(updatedEmailSubscription);
    }


}
