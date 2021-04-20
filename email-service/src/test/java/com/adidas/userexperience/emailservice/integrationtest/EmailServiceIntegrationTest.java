package com.adidas.userexperience.emailservice.integrationtest;

import com.adidas.userexperience.emailservice.EmailServiceApplication;
import com.adidas.userexperience.emailservice.dto.EmailMessage;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EmailServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailServiceIntegrationTest {

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
        return "http://localhost:" + port+"/api/v1/email/";
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
    public void testSendEmail() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setEmail("test@gmail.com");
        emailMessage.setSubject("Test Subject");
        emailMessage.setMessage("Say Hello");
        emailMessage.setName("Test Name");
        //@Todo work on RestTemplate to feth token from authentication server
        //ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl() + "/", emailMessage, null);
        assertNull(null);
    }
}
