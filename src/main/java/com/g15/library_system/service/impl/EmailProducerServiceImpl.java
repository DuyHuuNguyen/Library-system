package com.g15.library_system.service.impl;

import com.g15.library_system.dto.EmailContent;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.TransactionContentDTO;
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

  @Value("${rabbitmq.exchangeEmailText}")
  private String exchangeEmailText;

  @Value("${rabbitmq.sendEmailRouter}")
  private String sendEmailRouter;

  @Value("${rabbitmq.sendEmailTextRouter}")
  private String sendOTPRouter;


  @Value("${rabbitmq.sendEmailLendBookRouter}")
  private String sendEmailLendBookRouter;

  @Override
  public void send(EmailNotificationNewBooksDTO emailNotificationNewBooksDTO) {
    log.info(
        "😍😍😍 send email from {} -> routing {}", this.topicExchangeEmail, this.sendEmailRouter);
    this.rabbitTemplate.convertAndSend(
        this.topicExchangeEmail, this.sendEmailRouter, emailNotificationNewBooksDTO);
  }

  @Override
  public void send(TransactionContentDTO transaction) {
    log.info(
            "😍😍😍 send email from {} -> routing {}", this.topicExchangeEmail, this.sendEmailLendBookRouter);
    this.rabbitTemplate.convertAndSend(this.topicExchangeEmail, this.sendEmailLendBookRouter, transaction);
  }
  
  @Override
  public <T extends EmailContent> void send(EmailMessageDTO<T> emailMessageDTO) {
    log.info("😍😍😍 send email from {} -> routing {}", this.exchangeEmailText, this.sendOTPRouter);
    this.rabbitTemplate.convertAndSend(this.exchangeEmailText, this.sendOTPRouter, emailMessageDTO);
  }
}
