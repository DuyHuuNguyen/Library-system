package com.g15.library_system.service;

import com.g15.library_system.dto.request.ExportExcelRequest;
import com.g15.library_system.dto.request.ImportExcelRequest;

public interface ExcelConsumerService {
  void receive(ExportExcelRequest exportExcelRequest);

  void receive(ImportExcelRequest importExcelRequest);
}
