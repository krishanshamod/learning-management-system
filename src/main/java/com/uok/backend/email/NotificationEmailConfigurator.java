package com.uok.backend.email;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationEmailConfigurator {
    @Value("${mailgun.api.key}")
    private String apiKey;
    @Value("${mailgun.domain}")
    private String domainName;

    private Email emailData;

    public NotificationEmailConfigurator(){
        this.emailData = new Email(
                "Excited User <USER@YOURDOMAIN.COM>",
                "pj799571@gmail.com",
                "Hello",
                "This is a test email"
        );
    }

    public HttpRequestWithBody configureEmail() {
        HttpRequestWithBody request = Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", apiKey)
                .queryString("from", emailData.getFrom())
                .queryString("to", emailData.getTo())
                .queryString("subject", emailData.getSubject())
                .queryString("text", emailData.getBody());
        return request;
    }
}
