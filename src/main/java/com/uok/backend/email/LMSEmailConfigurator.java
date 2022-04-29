package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LMSEmailConfigurator implements EmailConfigurator {

    public HttpRequestWithBody configureEmail(HttpRequestWithBody authenticatedEmailRequest, Email emailData) {
        return authenticatedEmailRequest
                .queryString("from", emailData.getFromAddress())
                .queryString("to", emailData.getToAddress())
                .queryString("subject", emailData.getEmailSubject())
                .queryString("text", emailData.getEmailBody());
    }
}
