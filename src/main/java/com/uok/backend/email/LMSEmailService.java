package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;
import com.uok.backend.announcement.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LMSEmailService implements EmailService{

    //fixme lmsemaildataretriever should be loosely coupled
    private final LMSEmailDataRetriever retriever;
    private final EmailAuthenticator authenticator;
    private final EmailConfigurator configurator;
    private final EmailSender sender;



    @Autowired
    public LMSEmailService(
            LMSEmailDataRetriever retriever,
            EmailAuthenticator authenticator,
            EmailConfigurator configurator,
            EmailSender sender
    ) {
        this.retriever = retriever;
        this.authenticator = authenticator;
        this.configurator = configurator;
        this.sender = sender;
    }

    public void sendAnnouncemetEmail(Announcement announcement) {
        Email emailData = retriever.getEmailData(announcement);
        HttpRequestWithBody authenticatedEmailRequest = authenticator.authenticateEmail();
        HttpRequestWithBody configuredEmailRequest = configurator.configureEmail(authenticatedEmailRequest, emailData);
        sender.sendEmail(configuredEmailRequest);
    }
}
