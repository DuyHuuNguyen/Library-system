package com.g15.library_system.service.impl;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.service.EmailConsumerService;
import com.g15.library_system.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailConsumerServiceImpl implements EmailConsumerService {

  private final MailService mailService;

  @Override
  @RabbitHandler
  @RabbitListener(queues = {"${rabbitmq.sendEmailQueue}"})
  public void receive(EmailNotificationNewBooksDTO emailNotificationNewBooksDTO) {
    log.info("🙌🙌🙌 I received EmailNotificationNewBooksDTO");
    this.mailService.sendNotificationNewBooks(emailNotificationNewBooksDTO);
  }

  @Override
  @RabbitHandler
  @RabbitListener(queues = {"${rabbitmq.sendMailLendBookQueue}"})
  public void receive(TransactionContentDTO transaction) {
    this.mailService.sendTransactionContent(transaction);
  }

  @Override
  @RabbitHandler
  @RabbitListener(queues = {"${rabbitmq.sendEmailTextQueue}"})
  public <T extends EmailContent> void receive(EmailMessageDTO<T> emailMessageDTO) {
    log.info("send only text queue .... received message");
    this.mailService.send(emailMessageDTO);
  }
}
