package com.g15.library_system.service;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.TransactionContentDTO;

public interface MailService {
  <T extends EmailContent> void send(EmailMessageDTO<T> emailMessageDTO);

  void sendNotificationNewBooks(EmailNotificationNewBooksDTO notificationNewBooksDTO);

  void sendTransactionContent(TransactionContentDTO transactionContentDTO);
}
