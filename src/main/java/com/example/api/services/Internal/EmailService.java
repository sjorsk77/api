package com.example.api.services.Internal;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.io.ClassPathResource;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${server}")
    private String server;

    public void sendVerificationEmail(String to, Long userId) {
        String htmlTemplate = loadHtmlTemplate("templates/VerificationEmail.html");
        htmlTemplate = htmlTemplate.replace("{{verification_link}}", server + "/account/verify/" + userId);

        try {
            sendHtmlEmail(to, "Verify your email", htmlTemplate);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String loadHtmlTemplate(String htmlpath) {
        try {
            ClassPathResource resource = new ClassPathResource(htmlpath);
            Path path = resource.getFile().toPath();
            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        javaMailSender.send(message);
    }
}