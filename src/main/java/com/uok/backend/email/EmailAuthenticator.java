package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;

public interface EmailAuthenticator {
    HttpRequestWithBody authenticateEmail();
}
