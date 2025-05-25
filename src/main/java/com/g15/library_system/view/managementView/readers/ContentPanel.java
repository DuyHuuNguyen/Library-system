package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.data.ReaderData;
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
          int i = 1, j = 0;
          if (tablePn.memberData[modelRow][0] instanceof Boolean) {
            j = i;
          } else {
            j = i - 1;
          }
          while (i < tablePn.columnNames.length && j < tablePn.memberData[modelRow].length) {
            rowsData.put(tablePn.columnNames[i], tablePn.memberData[modelRow][j]);
            i++;
            j++;
          }
          //          for (; i < ; i++) {
          //            for (; j < tablePn.memberData[modelRow].length; j++) {
          //                if (tablePn.memberData[modelRow][j] instanceof Boolean) {
          //
          //                }
          //
          //              break;
          //            }
          //          }
          showInforPn.avtPn.setImageUrlAbsolute(rowsData.get("Cover Image") + "");
          System.out.println(rowsData.get("Cover Image") + "");
          showInforPn.avtPn.setSize(120, 120);
          showInforPn.formPn.updateInfo(rowsData);
          showInforPn.formPn.revalidate(); // Bắt buộc
          showInforPn.formPn.repaint();
          showInforPn.btnPn.setMode(ButtonPanelMode.VIEW);
          readerPn.contentPn.borrowedPanel.setVisible(true);
          readerPn.contentPn.borrowedPanel.setReader(
              ReaderData.getInstance().findId(Long.valueOf(rowsData.get("ID") + "")));
          readerPn.contentPn.borrowedPanel.refreshTable();
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
