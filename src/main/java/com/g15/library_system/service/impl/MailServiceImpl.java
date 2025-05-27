package com.g15.library_system.service.impl;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.service.MailService;
import jakarta.mail.internet.MimeMessage;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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
  public void sendTransactionContent(TransactionContentDTO transactionContentDTO) {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(transactionContentDTO.getReaderEmail());
    helper.setSubject("Successfully lending books!!");

    StringBuilder builder = new StringBuilder();
    builder.append("<html><body>");
    builder.append(
        "<div style='border: 1px solid #ddd; padding: 20px; max-width: 600px; margin: 0 auto;'>");

    // Title
    builder.append(
        "<h1 style='text-align: center; color: #2a5885; margin-bottom: 20px;'>LIBRARY TRANSACTION RECEIPT</h1>");
    builder.append("<hr style='border: 1px solid #eee;'>");

    String readerName = transactionContentDTO.getReaderName();
    builder
        .append("<p>Dear <strong>")
        .append(readerName != null ? readerName : "Valued Reader")
        .append("</strong>,</p>");
    builder.append("<p>Your book transaction has been processed successfully.</p>");

    builder.append("<h3 style='color: #2a5885;'>Transaction Details:</h3>");
    builder.append("<div style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>");
    Long id = transactionContentDTO.getId();
    builder
        .append("<p>Transaction ID: <strong>")
        .append(id != null ? id : "N/A")
        .append("</strong></p>");
    TransactionType transactionType = transactionContentDTO.getTransactionType();
    builder
        .append("<p>Transaction Type: <strong>")
        .append(transactionType != null ? transactionType : "N/A")
        .append("</strong></p>");
    String librarianName = transactionContentDTO.getLibrarianName();
    builder
        .append("<p>Librarian: <strong>")
        .append(librarianName != null ? librarianName : "N/A")
        .append("</strong></p>");
    builder.append("</div>");

    builder.append("<h3 style='color: #2a5885;'>Items:</h3>");
    builder.append("<ul style='list-style-type: square;'>");

    Map<String, Integer> books = transactionContentDTO.getBooks();
    if (books != null && !books.isEmpty()) {
      for (Map.Entry<String, Integer> entry : books.entrySet()) {
        builder
            .append("<li><strong>")
            .append(entry.getKey())
            .append("</strong> × ")
            .append(entry.getValue())
            .append("</li>");
      }
    } else {
      builder.append("<li>No items in this transaction.</li>");
    }
    builder.append("</ul>");

    builder.append("<p><strong>Due Date: </strong>");
    Long expectedReturnAt = transactionContentDTO.getExpectedReturnAt();
    if (expectedReturnAt != null) {
      java.time.LocalDate dueDate =
          java.time.Instant.ofEpochMilli(expectedReturnAt)
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

    if (notificationNewBooksDTO.getEmails() == null
        || notificationNewBooksDTO.getEmails().length == 0) {
      log.warn("No email recipients provided for new books notification");
      return;
    }

    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo(notificationNewBooksDTO.getEmails());
    helper.setSubject("Library Collection Update - New Acquisitions Available");

    StringBuilder htmlContent = new StringBuilder();
    htmlContent.append(
        "<html><body style='margin: 0; padding: 0; background-color: #f8f9fa; font-family: \"Segoe UI\", Tahoma, Geneva, Verdana, sans-serif;'>");

    htmlContent.append(
        "<div style='max-width: 700px; margin: 30px auto; background-color: #ffffff; box-shadow: 0 2px 10px rgba(0,0,0,0.1);'>");

    // Professional header
    htmlContent.append(
        "<div style='background-color: #1e3a8a; color: white; padding: 30px 40px;'>");
    htmlContent.append("<table style='width: 100%; border-collapse: collapse;'>");
    htmlContent.append("<tr>");
    htmlContent.append("<td>");
    htmlContent.append(
        "<h1 style='margin: 0; font-size: 24px; font-weight: 600;'>FIT LIBRARY</h1>");
    htmlContent.append(
        "<p style='margin: 5px 0 0 0; font-size: 14px; opacity: 0.9;'>Collection Development Services</p>");
    htmlContent.append("</td>");
    htmlContent.append("<td style='text-align: right; vertical-align: top;'>");
    htmlContent.append(
        "<div style='background-color: rgba(255,255,255,0.2); padding: 8px 15px; border-radius: 4px; font-size: 12px; font-weight: 500;'>NEW ACQUISITIONS</div>");
    htmlContent.append("</td>");
    htmlContent.append("</tr>");
    htmlContent.append("</table>");
    htmlContent.append("</div>");

    // Executive summary
    htmlContent.append(
        "<div style='padding: 40px 40px 30px 40px; border-bottom: 1px solid #e5e7eb;'>");
    htmlContent.append(
        "<h2 style='color: #1f2937; font-size: 22px; font-weight: 600; margin: 0 0 20px 0;'>Collection Update Notification</h2>");
    htmlContent.append(
        "<p style='color: #4b5563; font-size: 16px; line-height: 1.6; margin: 0 0 20px 0;'>We are pleased to inform you of the latest additions to the FIT Library collection. These new acquisitions have been carefully selected to support academic research and enhance the educational resources available to our community.</p>");
    htmlContent.append(
        "<div style='background-color: #eff6ff; border-left: 4px solid #1e3a8a; padding: 15px 20px; margin: 20px 0;'>");
    htmlContent.append(
        "<p style='color: #1e40af; margin: 0; font-size: 14px; font-weight: 500;'> Collection Update Summary: "
            + notificationNewBooksDTO.getTitleAndFirstImageDTOS().size()
            + " new titles now available for circulation</p>");
    htmlContent.append("</div>");
    htmlContent.append("</div>");

    // Professional book listing
    htmlContent.append("<div style='padding: 30px 40px;'>");
    htmlContent.append("<h3 style='color: #2a5885;'>New Acquisitions Catalog:</h3>");

    // Table format for professional look
    htmlContent.append(
        "<table style='width: 100%; border-collapse: collapse; border: 1px solid #ddd;'>");
    htmlContent.append("<thead>");
    htmlContent.append("<tr style='background-color: #f8f9fa;'>");
    htmlContent.append(
        "<th style='padding: 10px; text-align: left; border-bottom: 2px solid #ddd; font-size: 14px; font-weight: 600; color: #374151; text-transform: uppercase; letter-spacing: 0.5px;'>Catalog No.</th>");
    htmlContent.append(
        "<th style='padding: 10px; text-align: left; border-bottom: 2px solid #ddd; font-size: 14px; font-weight: 600; color: #374151; text-transform: uppercase; letter-spacing: 0.5px;'>Title</th>");
    htmlContent.append(
        "<th style='padding: 10px; text-align: center; border-bottom: 2px solid #ddd; font-size: 14px; font-weight: 600; color: #374151; text-transform: uppercase; letter-spacing: 0.5px;'>Status</th>");
    htmlContent.append("</tr>");
    htmlContent.append("</thead>");
    htmlContent.append("<tbody>");

    var items = notificationNewBooksDTO.getTitleAndFirstImageDTOS();
    for (int i = 0; i < items.size(); i++) {
      String rowBg = (i % 2 == 0) ? "#ffffff" : "#f8f9fa";
      htmlContent.append("<tr style='background-color: " + rowBg + ";'>");
      htmlContent.append(
          "<td style='padding: 10px; border-bottom: 1px solid #eee; font-size: 14px; color: #6b7280; font-weight: 600;'>LIB-"
              + String.format("%04d", i + 1)
              + "</td>");
      htmlContent.append(
          "<td style='padding: 10px; border-bottom: 1px solid #eee; font-size: 15px; color: #1f2937; font-weight: 500;'>"
              + items.get(i).getTitle()
              + "</td>");
      htmlContent.append(
          "<td style='padding: 10px; border-bottom: 1px solid #eee; text-align: center;'>");
      htmlContent.append(
          "<span style='background-color: #e8f4fc; color: #2a5885; padding: 4px 8px; border-radius: 4px; font-size: 12px; font-weight: bold;'>Available</span>");
      htmlContent.append("</td>");
      htmlContent.append("</tr>");

      var isHadImage = !items.get(i).isNoImage();
      if (isHadImage) {
        log.info("{} image : {} ", i, items.get(i).getFirstImage());
        ClassPathResource image = new ClassPathResource(items.get(i).getFirstImage());
        helper.addAttachment((i + 1) + "-" + items.get(i).getTitle() + ".jpg", image);
      }
    }

    htmlContent.append("</tbody>");
    htmlContent.append("</table>");
    htmlContent.append("</div>");

    // Call to action
    htmlContent.append(
        "<div style='padding: 30px 40px; background-color: #f9fafb; border-top: 1px solid #e5e7eb;'>");
    htmlContent.append("<div style='text-align: center;'>");
    htmlContent.append(
        "<h3 style='color: #1f2937; font-size: 18px; font-weight: 600; margin: 0 0 15px 0;'>Access Information</h3>");
    htmlContent.append(
        "<p style='color: #6b7280; font-size: 14px; margin: 0 0 20px 0;'>These titles are now available for borrowing through our standard circulation procedures.</p>");
    htmlContent.append(
        "<a href='#' style='background-color: #1e3a8a; color: white; padding: 12px 28px; text-decoration: none; border-radius: 4px; font-weight: 600; font-size: 14px; text-transform: uppercase; letter-spacing: 0.5px; display: inline-block;'>Access Digital Catalog</a>");
    htmlContent.append("</div>");
    htmlContent.append("</div>");

    // Professional footer
    htmlContent.append(
        "<div style='background-color: #1f2937; color: #9ca3af; padding: 25px 40px; text-align: center;'>");
    htmlContent.append(
        "<div style='border-bottom: 1px solid #374151; padding-bottom: 20px; margin-bottom: 20px;'>");
    htmlContent.append(
        "<h4 style='color: #ffffff; margin: 0 0 10px 0; font-size: 16px; font-weight: 600;'>FIT Library Services</h4>");
    htmlContent.append(
        "<p style='margin: 0; font-size: 14px;'>Faculty of Information Technology - University of Science</p>");
    htmlContent.append("</div>");
    htmlContent.append(
        "<table style='width: 100%; max-width: 400px; margin: 0 auto; border-collapse: collapse;'>");
    htmlContent.append("<tr>");
    htmlContent.append(
        "<td style='padding: 5px 0; font-size: 13px;'><strong>Address:</strong></td>");
    htmlContent.append(
        "<td style='padding: 5px 0; font-size: 13px;'>Khu phố 6, phường Linh Trung, TP. Thủ Đức, TP. Hồ Chí Minh</td>");
    htmlContent.append("</tr>");
    htmlContent.append("<tr>");
    htmlContent.append("<td style='padding: 5px 0; font-size: 13px;'><strong>Email:</strong></td>");
    htmlContent.append(
        "<td style='padding: 5px 0; font-size: 13px;'>library@fit.hcmus.edu.vn</td>");
    htmlContent.append("</tr>");
    htmlContent.append("<tr>");
    htmlContent.append("<td style='padding: 5px 0; font-size: 13px;'><strong>Phone:</strong></td>");
    htmlContent.append("<td style='padding: 5px 0; font-size: 13px;'>(028) 3725 2002</td>");
    htmlContent.append("</tr>");
    htmlContent.append("</table>");
    htmlContent.append(
        "<p style='margin: 15px 0 0 0; font-size: 11px; color: #6b7280;'>© 2025 FIT Library. All rights reserved. | This is an automated notification.</p>");
    htmlContent.append("</div>");

    htmlContent.append("</div>");
    htmlContent.append("</body></html>");

    helper.setText(htmlContent.toString(), true);
    javaMailSender.send(message);
  }
}
