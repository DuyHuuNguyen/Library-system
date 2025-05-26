package com.g15.library_system.view.managementView.returnBooks.commands;

import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportExcelCommand implements Command {
  private ReturnBookPanel returnBookPanel;

  public ExportExcelCommand(ReturnBookPanel returnBookPanel) {
    this.returnBookPanel = returnBookPanel;
  }

  @Override
  public void execute() {
    Object[][] tableData = returnBookPanel.getTableDataForExport();
    String[] columnNames = returnBookPanel.getColumnNames();

    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save as Excel");
    int userSelection = fileChooser.showSaveDialog(returnBookPanel);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      try (Workbook workbook = new XSSFWorkbook()) {
        Sheet sheet = workbook.createSheet("ReturnBooks");
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columnNames.length; i++) {
          headerRow.createCell(i).setCellValue(columnNames[i]);
        }
        for (int i = 0; i < tableData.length; i++) {
          Row row = sheet.createRow(i + 1);
          for (int j = 0; j < tableData[i].length; j++) {
            row.createCell(j).setCellValue(String.valueOf(tableData[i][j]));
          }
        }
        try (FileOutputStream fos = new FileOutputStream(fileToSave + ".xlsx")) {
          workbook.write(fos);
        }

        new ToastNotification(
                JOptionPane.getFrameForComponent(returnBookPanel),
                ToastNotification.Type.SUCCESS,
                ToastNotification.Location.TOP_CENTER,
                "Exporting to Excel file successfully!")
            .showNotification();
      } catch (Exception ex) {
        new ToastNotification(
                JOptionPane.getFrameForComponent(returnBookPanel),
                ToastNotification.Type.SUCCESS,
                ToastNotification.Location.TOP_CENTER,
                "Export failed!")
            .showNotification();
      }
    }
  }
}
