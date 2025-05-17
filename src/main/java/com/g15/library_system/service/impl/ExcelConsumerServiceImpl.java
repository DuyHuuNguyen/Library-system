package com.g15.library_system.service.impl;

import com.g15.library_system.dto.request.ExportExcelRequest;
import com.g15.library_system.dto.request.ImportExcelRequest;
import com.g15.library_system.service.ExcelConsumerService;
import com.g15.library_system.service.ExcelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelConsumerServiceImpl implements ExcelConsumerService {

  private final ExcelService excelService;

  @Override
  @RabbitHandler
  @RabbitListener(queues = {"${rabbitmq.sendExportExcelQueue}"})
  public void receive(ExportExcelRequest exportExcelRequest) {
    log.info("🙌🙌🙌 I received message ExportExcelRequest ");
    this.excelService.exportExcelBook(
        exportExcelRequest.getBooks(),
        exportExcelRequest.getNameFile(),
        exportExcelRequest.getHeaderSheet());
  }

  @Override
  @RabbitHandler
  @RabbitListener(queues = {"${rabbitmq.importQueue}"})
  public void receive(ImportExcelRequest importExcelRequest) {
    log.info("👌👌👌👌 I received message ExportExcelRequest {} ", importExcelRequest);
    var book = this.excelService.readExcelFileToBooks(importExcelRequest.getUrl());
    //    log.info("hello {} " , book.getFirst());
    //    for(var item :  this.excelService.readExcelFileToBooks(importExcelRequest.getUrl())) {
    //      log.info("read -> {} ", item);
    //    }

  }
}
