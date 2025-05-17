package com.g15.library_system.service;

import com.g15.library_system.dto.request.ExportExcelRequest;
import com.g15.library_system.dto.request.ImportExcelRequest;

public interface ExcelProducerService {
  void export(ExportExcelRequest exportExcelRequest);

  void importExcel(ImportExcelRequest importExcelRequest);
}
