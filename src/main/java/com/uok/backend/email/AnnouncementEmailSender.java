package com.uok.backend.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementEmailSender implements EmailSender{
    public void sendEmail(HttpRequestWithBody configuredEmailRequest) {
        try {
            HttpResponse<JsonNode> response = configuredEmailRequest.asJson();
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
