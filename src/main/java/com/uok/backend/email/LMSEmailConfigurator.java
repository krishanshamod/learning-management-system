package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LMSEmailConfigurator implements EmailConfigurator {

    private final EmailAuthenticator authenticator;

    @Autowired
    public LMSEmailConfigurator(EmailAuthenticator authenticator) {
        this.authenticator = authenticator;
    }

    public HttpRequestWithBody configureEmail(HttpRequestWithBody authenticatedEmailRequest, Email emailData) {
        return authenticatedEmailRequest
                .queryString("from", emailData.getFromAddress())
                .queryString("to", emailData.getToAddress())
                .queryString("subject", emailData.getEmailSubject())
                .queryString("text", emailData.getEmailBody());
    }
}
