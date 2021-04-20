package com.adidas.userexperience.subscriptionservice.config;

import com.adidas.userexperience.subscriptionservice.service.SubscriptionService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static String TOKEN;

    @Autowired
    private SubscriptionService subscriptionService;

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

        if (getBearerTokenHeader() != null) {
            FeignClientInterceptor.TOKEN = getBearerTokenHeader();
        }
        requestTemplate.header(AUTHORIZATION_HEADER, TOKEN);
    }
}