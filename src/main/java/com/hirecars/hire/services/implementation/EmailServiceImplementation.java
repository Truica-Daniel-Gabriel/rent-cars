package com.hirecars.hire.services.implementation;

import com.hirecars.hire.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailServiceImplementation implements EmailService {
    private final JavaMailSender javaMailSender;
    private final ResourceLoader resourceLoader;


    @Value("${MAIN_GMAIL}")
    private String gmailFrom;


    public EmailServiceImplementation(JavaMailSender javaMailSender, ResourceLoader resourceLoader) {
        this.javaMailSender = javaMailSender;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void sendEmail(String to, String subject, String htmlTemplate, Map<String,Object> templateVariables) throws MessagingException, IOException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
        helper.setTo(to);
        helper.setSubject(subject);
        String processedHtml = processTemplate(templateVariables, htmlTemplate);

        helper.setText(processedHtml, true);

        javaMailSender.send(message);
    }


    private String processTemplate(Map<String, Object> variables, String htmlTemplate) {
        VelocityEngine velocityEngine = new VelocityEngine();

        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        VelocityContext context = new VelocityContext();
        variables.entrySet().stream().forEach((entry)-> context.put(entry.getKey(), entry.getValue()));

        Template template = velocityEngine.getTemplate(htmlTemplate);

        StringWriter writer = new StringWriter();

        template.merge(context, writer);

       return writer.toString();
    }

}
