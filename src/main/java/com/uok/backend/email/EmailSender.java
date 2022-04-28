package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;

public interface EmailSender {
    void sendEmail(HttpRequestWithBody configuredEmailRequest);
}
