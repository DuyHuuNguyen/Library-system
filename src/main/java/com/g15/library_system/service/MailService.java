package com.g15.library_system.service;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;

public interface MailService {
  <T extends EmailContent> void send(EmailMessageDTO<T> emailMessageDTO);
}
