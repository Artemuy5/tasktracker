package by.shapovalov.tasktracker.listener;

import by.shapovalov.tasktracker.dto.OnRegistrationCompleteEvent;
import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 13.01.2018
 */

@Component
public class  RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private UserService service;
    private MessageSource messages;
    private JavaMailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    @Value("${support.email}")
    String from;

    public RegistrationListener(UserService service, MessageSource messages, JavaMailSender mailSender, SimpleMailMessage simpleMailMessage1) {
        this.service = service;
        this.messages = messages;
        this.mailSender = mailSender;
        this.simpleMailMessage = simpleMailMessage1;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = event.getAppUrl() + "/registrationConfirm?token=" + token;
        String message = messages.getMessage("email.message", null, event.getLocale());

        simpleMailMessage.setTo(recipientAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message + " \r\n" + confirmationUrl);
        simpleMailMessage.setFrom(from);
        mailSender.send(simpleMailMessage);
    }
}
