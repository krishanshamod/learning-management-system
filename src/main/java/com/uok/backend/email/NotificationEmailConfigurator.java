package com.uok.backend.email;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationEmailConfigurator {

    private NotificationEmailAuthenticator authenticator;

    private Email emailData;

    @Autowired
    public NotificationEmailConfigurator(NotificationEmailAuthenticator authenticator){
        this.authenticator = authenticator;
        this.emailData = new Email(
                "Excited User <USER@YOURDOMAIN.COM>",
                "pj799571@gmail.com",
                "Hello",
                "This is a test email"
        );
    }

    public HttpRequestWithBody configureEmail() {
        HttpRequestWithBody request = authenticator.authenticateEmail();
        request
                .queryString("from", emailData.getFrom())
                .queryString("to", emailData.getTo())
                .queryString("subject", emailData.getSubject())
                .queryString("text", emailData.getBody());
        return request;
    }
}
