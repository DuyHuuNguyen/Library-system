package com.g15.library_system.service.impl;

import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.service.EmailProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailProducerServiceImpl implements EmailProducerService {
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.topicExchangeEmail}")
  private String topicExchangeEmail;

  @Value("${rabbitmq.sendEmailRouter}")
  private String sendEmailRouter;

  @Override
  public void send(EmailNotificationNewBooksDTO emailNotificationNewBooksDTO) {
    log.info(
        "😍😍😍 send email from {} -> routing {}", this.topicExchangeEmail, this.sendEmailRouter);
    this.rabbitTemplate.convertAndSend(
        this.topicExchangeEmail, this.sendEmailRouter, emailNotificationNewBooksDTO);
  }
}
