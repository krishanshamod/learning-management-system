package com.uok.backend.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.stereotype.Component;

@Component
public class LMSEmailSender implements EmailSender {
    //fixme handle exceptions properly
    public void sendEmail(HttpRequestWithBody configuredEmailRequest) {
        try {
            //fixme response is not used
            HttpResponse<JsonNode> response = configuredEmailRequest.asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
