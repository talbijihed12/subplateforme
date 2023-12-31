package com.communication.plateforme.services.impl;

import com.communication.plateforme.utils.exceptions.SpringPlateformeException;
import com.communication.plateforme.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springplateforme@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject((notificationEmail.getSubject()));
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation email sent!");

        } catch (MailException e) {
            throw new SpringPlateformeException("Exception occurred when sending mail to " + notificationEmail.getRecipient());
        }
    }
}
