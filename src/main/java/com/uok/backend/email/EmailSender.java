package com.uok.backend.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.stereotype.Service;

@Component
public class EmailSender {

    private final NotificationEmailConfigurator configurator;

    @Autowired
    public EmailSender(NotificationEmailConfigurator configurator) {
        this.configurator = configurator;
    }

    public void sendEmail() {

        HttpRequestWithBody request = configurator.configureEmail();
        try {
            HttpResponse<JsonNode> response = request.asJson();
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }

    }


}
