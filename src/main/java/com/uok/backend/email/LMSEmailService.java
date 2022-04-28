package com.uok.backend.email;

import com.mashape.unirest.request.HttpRequestWithBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LMSEmailService implements EmailService{

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

    public void sendAnnouncemetEmail() {
        Email emailData = retriever.getEmailData();
        HttpRequestWithBody authenticatedEmailRequest = authenticator.authenticateEmail();
        HttpRequestWithBody finalEmailrequest = configurator.configureEmail(authenticatedEmailRequest, emailData);
        sender.sendEmail(finalEmailrequest);
    }
}
