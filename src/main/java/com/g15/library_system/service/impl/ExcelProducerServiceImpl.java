package com.g15.library_system.service.impl;

import com.g15.library_system.dto.request.ExportExcelRequest;
import com.g15.library_system.service.ExcelProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelProducerServiceImpl implements ExcelProducerService {

  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbitmq.topicExchangeExportExcel}")
  private String topicExchangeExportExcel;

  @Value("${rabbitmq.exportExcelRouter}")
  private String exportExcelRouter;

  @Override
  public void export(ExportExcelRequest exportExcelRequest) {
    log.info("👌👌👌 I send..... ExportExcelRequest");
    this.rabbitTemplate.convertAndSend(
        topicExchangeExportExcel, exportExcelRouter, exportExcelRequest);
  }
}
