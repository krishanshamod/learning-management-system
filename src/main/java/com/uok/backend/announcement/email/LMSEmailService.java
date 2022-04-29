package com.uok.backend.announcement.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.uok.backend.announcement.Announcement;
import com.uok.backend.email.*;
import com.uok.backend.exceptions.EmailSendingFailureException;
import com.uok.backend.mark.GetMarksRequest;
import com.uok.backend.mark.MarkService;
import com.uok.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LMSEmailService implements EmailService {

    private final EmailDataRetriever retriever;
    private final EmailAuthenticator authenticator;
    private final EmailConfigurator configurator;

    @Autowired
    public LMSEmailService(
            EmailDataRetriever retriever,
            EmailAuthenticator authenticator,
            EmailConfigurator configurator
    ) {
        this.retriever = retriever;
        this.authenticator = authenticator;
        this.configurator = configurator;
    }

    public void sendAnnouncementEmail(Announcement announcement) {

        // get email data
        Email emailData = retriever.getEmailData(announcement);

        HttpRequestWithBody authenticatedEmailRequest = authenticator.authenticateEmail();
        HttpRequestWithBody configuredEmailRequest = configurator.configureEmail(authenticatedEmailRequest, emailData);

        try {
            // send email
            HttpResponse<JsonNode> response = configuredEmailRequest.asJson();

            // throw exception if email not sent
            if (response.getStatus() != 200) {
                throw new EmailSendingFailureException("Email sending failed");
            }

        } catch (UnirestException | EmailSendingFailureException e) {
            throw new RuntimeException(e);
        }
    }
}
