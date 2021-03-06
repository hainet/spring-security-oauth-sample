package com.hainet.bar.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@SpringBootApplication
public class BarClientApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BarClientApplication.class, args);
    }

    @Bean
    public OAuth2RestTemplate oauth2RestTemplate(
            final @Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext,
            final OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }
}
