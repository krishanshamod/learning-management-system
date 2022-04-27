package com.uok.backend.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class Email {
    @Value("${mailgun.api.key}")
    public String apiKey;
    @Value("${mailgun.domain}")
    public String domainName;

    public void sendSimpleMessage() {
        try {
            HttpResponse<JsonNode>  request = Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", apiKey)
                .queryString("from", "Excited User <USER@YOURDOMAIN.COM>")
                .queryString("to", "pj799571@gmail.com")
                .queryString("subject", "hello")
                .queryString("text", "testing")
                .asJson();
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }


    }



}
