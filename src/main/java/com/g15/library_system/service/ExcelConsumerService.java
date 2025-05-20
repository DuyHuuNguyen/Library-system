package com.g15.library_system.service;

import com.g15.library_system.dto.request.ExportExcelRequest;

public interface ExcelConsumerService {
  void receive(ExportExcelRequest exportExcelRequest);
}
