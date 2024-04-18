package com.hirecars.hire.services;

import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Map;

public interface EmailService {
    void sendEmail(String to, String subject, String htmlBody, Map<String, Object> templateVariables) throws MessagingException, IOException;
}
