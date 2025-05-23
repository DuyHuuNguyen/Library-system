package com.g15.library_system.service.impl;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.service.MailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

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

//    @Override
//    @SneakyThrows
//    public void sendNotificationNewBooks(EmailNotificationNewBooksDTO notificationNewBooksDTO) {
//        log.info("send email notification new books");
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setTo(notificationNewBooksDTO.getEmails());
//        helper.setSubject("📚 Fit Library, New book");
//        StringBuilder htmlContent = new StringBuilder();
//
//        htmlContent.append("""
//                😊 Friendly & inviting:\n
//                Have You Seen Our New Books Yet? 📚\n
//                New Books Are Waiting for You!\n
//                Fresh Reads Just Arrived!\n
//                Check Out What’s New at the Library!\n
//                """);
//
//            var items = notificationNewBooksDTO.getTitleAndFirstImageDTOS();
//            for (int i = 0; i < items.size(); i++) {
//
//            htmlContent.append("<h2>");
//            htmlContent.append(i+1 + "."+items.get(i).getTitle()+"\n");
//            htmlContent.append("</h2>");
//            var isHadImage = !items.get(i).isNoImage();
//            if (isHadImage) {
//                log.info("{} image : {} ",i,items.get(i).getFirstImage());
//                ClassPathResource image = new ClassPathResource(items.get(i).getFirstImage());
//                helper.addAttachment((i+1)+ items.get(i).getTitle()+".jpg", image);
//            }
//        }
//
//        helper.setText(htmlContent.toString(), true);
//        javaMailSender.send(message);
//    }

    @Override
    @SneakyThrows
    public void sendTransactionContent(TransactionContentDTO transactionContentDTO) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(transactionContentDTO.getReaderEmail());
        helper.setSubject("Successfully lending books!!");

        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>");
        builder.append("<div style='border: 1px solid #ddd; padding: 20px; max-width: 600px; margin: 0 auto;'>");

        // Title
        builder.append("<h1 style='text-align: center; color: #2a5885; margin-bottom: 20px;'>LIBRARY TRANSACTION RECEIPT</h1>");
        builder.append("<hr style='border: 1px solid #eee;'>");

        String readerName = transactionContentDTO.getReaderName();
        builder.append("<p>Dear <strong>").append(readerName != null ? readerName : "Valued Reader").append("</strong>,</p>");
        builder.append("<p>Your book transaction has been processed successfully.</p>");

        builder.append("<h3 style='color: #2a5885;'>Transaction Details:</h3>");
        builder.append("<div style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>");
        Long id = transactionContentDTO.getId();
        builder.append("<p>Transaction ID: <strong>").append(id != null ? id : "N/A").append("</strong></p>");
        TransactionType transactionType = transactionContentDTO.getTransactionType();
        builder.append("<p>Transaction Type: <strong>").append(transactionType != null ? transactionType : "N/A").append("</strong></p>");
        String librarianName = transactionContentDTO.getLibrarianName();
        builder.append("<p>Librarian: <strong>").append(librarianName != null ? librarianName : "N/A").append("</strong></p>");
        builder.append("</div>");

        builder.append("<h3 style='color: #2a5885;'>Items:</h3>");
        builder.append("<ul style='list-style-type: square;'>");

        Map<String, Integer> books = transactionContentDTO.getBooks();
        if (books != null && !books.isEmpty()) {
            for (Map.Entry<String, Integer> entry : books.entrySet()) {
                builder.append("<li><strong>").append(entry.getKey())
                        .append("</strong> × ").append(entry.getValue()).append("</li>");
            }
        } else {
            builder.append("<li>No items in this transaction.</li>");
        }
        builder.append("</ul>");

        builder.append("<p><strong>Due Date: </strong>");
        Long expectedReturnAt = transactionContentDTO.getExpectedReturnAt();
        if (expectedReturnAt != null) {
            java.time.LocalDate dueDate = java.time.Instant
                    .ofEpochMilli(expectedReturnAt)
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
            builder.append(dueDate.format(java.time.format.DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
        } else {
            builder.append("Not specified");
        }
        builder.append("</p>");

        String description = transactionContentDTO.getDescription();
        if (description != null && !description.trim().isEmpty()) {
            builder.append("<p><strong>Notes: </strong>").append(description).append("</p>");
        }

        // Footer sections
        builder.append("<hr style='border: 1px solid #eee; margin-top: 20px;'>");
        builder.append("<div style='text-align: center; color: #666; font-size: 12px;'>");
        builder.append("<p><strong>FIT Library</strong></p>");
        builder.append("<p>Khu phố 6, phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí Minh</p>");
        builder.append("<p>Email: library@fit.hcmus.edu.vn | Phone: (028) 3725 2002</p>");
        builder.append("<p>© 2025 FIT Library. All rights reserved.</p>");
        builder.append("</div>");

        builder.append("</div>");
        builder.append("</body></html>");

        helper.setText(builder.toString(), true);
        javaMailSender.send(message);
    }


    @Override
    @SneakyThrows
    public void sendNotificationNewBooks(EmailNotificationNewBooksDTO notificationNewBooksDTO) {
        log.info("send email notification new books");
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(notificationNewBooksDTO.getEmails());
        helper.setSubject("📚 Fit Library, New Book");

        StringBuilder htmlContent = new StringBuilder();
        // Add a bordered section for the introductory content
        htmlContent.append("""
            <div style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                <div style="border: 2px solid #4CAF50; padding: 20px; border-radius: 10px; background-color: #f9f9f9; margin-bottom: 20px;">
                    <h1 style="color: #4CAF50; text-align: center;">📚 Friendly & Inviting:</h1>
                    <p style="font-size: 16px; text-align: center; color: #333;">Have You Seen Our New Books Yet?</p>
                    <p style="font-size: 16px; text-align: center; color: #333;">New Books Are Waiting for You!</p>
                    <p style="font-size: 16px; text-align: center; color: #333;">Fresh Reads Just Arrived!</p>
                    <p style="font-size: 16px; text-align: center; color: #333;">Check Out What’s New at the Library!</p>
                </div>
            </div>
            <br />
            <table style="width: 100%; border-collapse: collapse; font-family: Arial, sans-serif;">
                <thead>
                    <tr style="background-color: #f2f2f2;">
                        <th style="border: 1px solid #ddd; padding: 8px; text-align: center;">No.</th>
                        <th style="border: 1px solid #ddd; padding: 8px; text-align: left;">Book Title</th>
                    </tr>
                </thead>
                <tbody>
            """);

        var items = notificationNewBooksDTO.getTitleAndFirstImageDTOS();
        for (int i = 0; i < items.size(); i++) {
            htmlContent.append("<tr style=\"border: 1px solid #ddd;\">");
            htmlContent.append("<td style=\"border: 1px solid #ddd; padding: 8px; text-align: center;\">" + (i + 1) + "</td>");
            htmlContent.append("<td style=\"border: 1px solid #ddd; padding: 8px;\">" + items.get(i).getTitle() + "</td>");
            htmlContent.append("</tr>");



            var isHadImage = !items.get(i).isNoImage();
            if (isHadImage) {
                log.info("{} image : {} ", i, items.get(i).getFirstImage());
                ClassPathResource image = new ClassPathResource(items.get(i).getFirstImage());
                helper.addAttachment((i + 1) + items.get(i).getTitle() + ".jpg", image);
            }
        }

        // Footer sections
        htmlContent.append("<hr style='border: 1px solid #eee; margin-top: 20px;'>");
        htmlContent.append("<div style='text-align: center; color: #666; font-size: 12px;'>");
        htmlContent.append("<p><strong>FIT Library</strong></p>");
        htmlContent.append("<p>Khu phố 6, phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí Minh</p>");
        htmlContent.append("<p>Email: library@fit.hcmus.edu.vn | Phone: (028) 3725 2002</p>");
        htmlContent.append("<p>© 2025 FIT Library. All rights reserved.</p>");
        htmlContent.append("</div>");

        htmlContent.append("</div>");
        htmlContent.append("</body></html>");

        helper.setText(htmlContent.toString(), true);
        javaMailSender.send(message);
    }
}

