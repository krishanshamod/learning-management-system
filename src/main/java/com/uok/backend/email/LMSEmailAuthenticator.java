package com.uok.backend.email;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LMSEmailAuthenticator implements EmailAuthenticator{

    //fixme fix this
    @Value("${mailgun.api.key}")
    private String apiKey;
    @Value("${mailgun.domain}")
    private String domainName;

    public HttpRequestWithBody authenticateEmail() {
        return Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", apiKey);
    }
}
