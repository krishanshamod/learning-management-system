package com.uok.backend.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.stereotype.Component;

@Component
public class LMSAnnouncementEmailSender implements EmailSender{
    //fixme handle exceptions properly
    public void sendEmail(HttpRequestWithBody configuredEmailRequest) {
        try {
            HttpResponse<JsonNode> response = configuredEmailRequest.asJson();
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
