package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ContentPanel extends JPanel {
  public TablePanel tablePn;
  public ShowPanel showInforPn;
  public BorrowedHistoryPanel borrowedPanel;

  public ContentPanel(ReaderPanel readerPn) {
    tablePn = new TablePanel(readerPn);
    showInforPn = new ShowPanel("Edit", "Remove", readerPn);
    borrowedPanel = new BorrowedHistoryPanel();

    setLayout(new GridLayout(1, 2, 5, 20));

    CheckboxTablePanel table = tablePn.tablePanel;
    table.setRowSelectionHandler(
        modelRow -> {
          // Viết hành động bạn muốn tại đây
          Map<String, Object> rowsData = new HashMap<>();
          for (int i = 1; i < tablePn.columnNames.length; i++) {
            for (int j = i-1; j < tablePn.memberData[modelRow].length; j++) {
              rowsData.put(tablePn.columnNames[i], tablePn.memberData[modelRow][j]);
              break;
            }
          }

            showInforPn.formPn.updateInfo(rowsData);
            showInforPn.formPn.revalidate(); // Bắt buộc
            showInforPn.formPn.repaint();
            showInforPn.btnPn.setMode(ButtonPanelMode.VIEW);
            readerPn.contentPn.borrowedPanel.setVisible(true);
          // Ví dụ: mở form, bật checkbox, đổi trạng thái...
        });

    add(tablePn);

    JPanel showPn = new JPanel();
    showPn.setLayout(new BorderLayout());
    showPn.add(showInforPn, BorderLayout.CENTER);
    showPn.add(borrowedPanel, BorderLayout.SOUTH);

    add(showPn);

  }
}
