package com.g15.library_system.service.impl;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.service.MailService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Override
    @SneakyThrows
    public <T extends EmailContent> void send(EmailMessageDTO<T> emailMessageDTO) {
        log.info("send email");
        var mail = new SimpleMailMessage();
        mail.setTo(emailMessageDTO.getTo());
        mail.setSubject(emailMessageDTO.getSubject());
        mail.setText(emailMessageDTO.getContent().toString());
        javaMailSender.send(mail);
    }

    @Override
    @SneakyThrows
    public void sendNotificationNewBooks(EmailNotificationNewBooksDTO notificationNewBooksDTO) {
        log.info("send email notification new books");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(notificationNewBooksDTO.getEmails());
        helper.setSubject("📚 Fit Library, New book");
        StringBuilder htmlContent = new StringBuilder();

        htmlContent.append("""
                😊 Friendly & inviting:\n
                Have You Seen Our New Books Yet? 📚\n
                New Books Are Waiting for You!\n
                Fresh Reads Just Arrived!\n
                Check Out What’s New at the Library!\n
                """);

            var items = notificationNewBooksDTO.getTitleAndFirstImageDTOS();
            for (int i = 0; i < items.size(); i++) {

            htmlContent.append("<h2>");
            htmlContent.append(i+1 + "."+items.get(i).getTitle()+"\n");
            htmlContent.append("</h2>");
            var isHadImage = !items.get(i).isNoImage();
            if (isHadImage) {
                log.info("{} image : {} ",i,items.get(i).getFirstImage());
                ClassPathResource image = new ClassPathResource(items.get(i).getFirstImage());
                helper.addAttachment((i+1)+ items.get(i).getTitle()+".jpg", image);
            }
        }

        helper.setText(htmlContent.toString(), true);
        javaMailSender.send(message);
    }
}

