package com.uok.backend.email;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LMSEmailAuthenticator implements EmailAuthenticator {

    private final Environment env;
    private final String apiKey;
    private final String domainName;

    @Autowired
    public LMSEmailAuthenticator(Environment env) {
        this.env = env;
        this.apiKey = env.getProperty("mailgun.api.key");
        this.domainName = env.getProperty("mailgun.domain");
    }

    public HttpRequestWithBody authenticateEmail() {
        return Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", apiKey);
    }
}
