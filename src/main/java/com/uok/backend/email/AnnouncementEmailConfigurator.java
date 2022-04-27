package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementEmailConfigurator implements EmailConfigurator{

    private AnnouncementEmailAuthenticator authenticator;

    @Autowired
    public AnnouncementEmailConfigurator(AnnouncementEmailAuthenticator authenticator){
        this.authenticator = authenticator;
    }

    public HttpRequestWithBody configureEmail(HttpRequestWithBody authenticatedEmailRequest, Email emailData) {
        HttpRequestWithBody request = authenticatedEmailRequest
                .queryString("from", emailData.getFrom())
                .queryString("to", emailData.getTo())
                .queryString("subject", emailData.getSubject())
                .queryString("text", emailData.getBody());
        return request;
    }
}
