package com.uok.backend.announcement.email;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.uok.backend.announcement.Announcement;
import com.uok.backend.email.*;
import com.uok.backend.mark.GetMarksRequest;
import com.uok.backend.mark.MarkService;
import com.uok.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LMSEmailService implements EmailService {

    private final MarkService markService;
    private final EmailDataRetriever retriever;
    private final EmailAuthenticator authenticator;
    private final EmailConfigurator configurator;

    @Autowired
    public LMSEmailService(
            EmailDataRetriever retriever,
            EmailAuthenticator authenticator,
            EmailConfigurator configurator,
            MarkService markService
    ) {
        this.retriever = retriever;
        this.authenticator = authenticator;
        this.configurator = configurator;
        this.markService = markService;
    }

    public void sendAnnouncementEmail(Announcement announcement) {

        List<User> userList = (List<User>) markService.getEnrolledStudents(new GetMarksRequest(announcement.getCourseId())).getBody();

        Email emailData = retriever.getEmailData(userList, announcement);


        HttpRequestWithBody authenticatedEmailRequest = authenticator.authenticateEmail();
        HttpRequestWithBody configuredEmailRequest = configurator.configureEmail(authenticatedEmailRequest, emailData);

        try {
            HttpResponse<JsonNode> response = configuredEmailRequest.asJson();
            System.out.println("Response: " + response.getStatus());
        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }
    }
}
