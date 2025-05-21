package com.g15.library_system.service;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;

public interface EmailProducerService {
  void send(EmailNotificationNewBooksDTO emailNotificationNewBooksDTO);

  <T extends EmailContent> void send(EmailMessageDTO<T> emailMessageDTO);
}
