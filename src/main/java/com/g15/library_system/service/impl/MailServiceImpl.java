package com.g15.library_system.service.impl;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.NotificationNewBooksDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.service.MailService;
import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    @SneakyThrows
    public void sendNotificationNewBooks(EmailNotificationNewBooksDTO notificationNewBooksDTO) {
        log.info("send email notification new books");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
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
            htmlContent.append(i+1 + "."+items.get(i)+"\n");
            htmlContent.append("</h2>");

            if (items.get(i).isNoImage()) {
                var file = new FileSystemResource(new File(items.get(i).getFirstImage()));
                helper.addAttachment((i+1)+ items.get(i).getTitle()+".png", file);
            }
        }

        helper.setText(htmlContent.toString(), true);
        javaMailSender.send(message);
    }
}

