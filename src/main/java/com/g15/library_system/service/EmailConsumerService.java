package com.g15.library_system.service;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.TransactionContentDTO;

public interface EmailConsumerService {
  void receive(EmailNotificationNewBooksDTO emailNotificationNewBooksDTO);

  void receive(TransactionContentDTO transaction);

  <T extends EmailContent> void receive(EmailMessageDTO<T> emailMessageDTO);
}
