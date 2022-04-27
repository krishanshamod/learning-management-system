package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;

public interface EmailConfigurator {
    HttpRequestWithBody configureEmail(HttpRequestWithBody authenticatedEmailRequest, Email emailData);
}
