package com.uok.backend.email;

public class Email {

    private final String fromAddress;
    private final String toAddress;
    private final String emailSubject;
    private final String emailBody;

    public Email(String fromAddress, String toAddress, String emailSubject, String emailBody) {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }
}
