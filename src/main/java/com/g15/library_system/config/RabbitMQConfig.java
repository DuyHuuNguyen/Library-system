package com.g15.library_system.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
  @Value("${rabbitmq.sendEmailQueue}")
  private String sendEmailQueue;

  @Value("${rabbitmq.sendEmailTextQueue}")
  private String sendEmailTextQueue;

  @Value("${rabbitmq.sendMailLendBookQueue}")
  private String sendEmailLendBookQueue;

  @Value("${rabbitmq.topicExchangeEmail}")
  private String topicExchangeEmail;

  @Value("${rabbitmq.exchangeEmailText}")
  private String exchangeEmailText;

  @Value("${rabbitmq.sendEmailRouter}")
  private String sendEmailRouter;

  @Value("${rabbitmq.sendEmailTextRouter}")
  private String sendEmailTextRouter;

  @Value("${rabbitmq.sendEmailLendBookRouter}")
  private String sendEmailLendBookRouter;

  @Value("${rabbitmq.sendExportExcelQueue}")
  private String exportExcelQueue;

  @Value("${rabbitmq.topicExchangeExportExcel}")
  private String topicExchangeExportExcel;

  @Value("${rabbitmq.exportExcelRouter}")
  private String exportExcelRouter;

  @Value("${rabbitmq.importQueue}")
  private String importQueue;

  @Value("${rabbitmq.importRouter}")
  private String importExcelRouter;

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(topicExchangeEmail);
  }

  @Bean
  public TopicExchange exchangeEmailText() {
    return new TopicExchange(exchangeEmailText);
  }

  @Bean
  public Queue userMailQueue() {
    return new Queue(sendEmailQueue);
  }

  @Bean
  public Queue userMailLendBookQueue() {
    return new Queue(sendEmailLendBookQueue);
  }

  @Bean
  public Queue userEmailTextQueue() {
    return new Queue(sendEmailTextQueue);
  }

  @Bean
  public MessageConverter converter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public Binding userMailBinding() {
    return BindingBuilder.bind(userMailQueue()).to(exchange()).with(sendEmailRouter);
  }

  @Bean
  public Binding userSendEmailTextBinding() {
    return BindingBuilder.bind(userEmailTextQueue())
        .to(exchangeEmailText())
        .with(sendEmailTextRouter);
  }

  @Bean
  public TopicExchange exchangeExcel() {
    return new TopicExchange(topicExchangeExportExcel);
  }

  @Bean
  public Queue exportExcelQueue() {
    return new Queue(exportExcelQueue);
  }

  @Bean
  public Binding exportExcelBinding() {
    return BindingBuilder.bind(this.exportExcelQueue())
        .to(exchangeExcel())
        .with(this.exportExcelRouter);
  }

  @Bean
  public Queue importExcelQueue() {
    return new Queue(this.importQueue);
  }

  @Bean
  public Binding importExcelBinding() {
    return BindingBuilder.bind(this.importExcelQueue())
        .to(this.exchangeExcel())
        .with(this.importExcelRouter);
  }

  @Bean
  public Binding userSendEmailLendBookBinding() {
    return BindingBuilder.bind(userMailLendBookQueue())
        .to(exchange())
        .with(sendEmailLendBookRouter);
  }
}
