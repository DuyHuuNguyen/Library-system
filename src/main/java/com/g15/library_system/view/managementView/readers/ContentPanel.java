package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentPanel extends JPanel {
  private TablePanel tablePn;
  private ShowPanel showInforPn;
  private BorrowedHistoryPanel borrowedPanel;

  public ContentPanel(ReaderPanel readerPn) {
    tablePn = new TablePanel(readerPn);
    showInforPn = new ShowPanel("Edit", "Remove", readerPn);
    borrowedPanel = new BorrowedHistoryPanel();

    setLayout(new GridLayout(1, 2, 5, 20));

    CheckboxTablePanel table = tablePn.getTablePanel();
    table.setRowSelectionHandler(
        modelRow -> {
          // Viết hành động bạn muốn tại đây
          Map<String, Object> rowsData = new HashMap<>();
          int i = 1, j = 0;
          if (tablePn.getMemberData()[modelRow][0] instanceof Boolean) {
            j = i;
          } else {
            j = i - 1;
          }
          while (i < tablePn.getColumnNames().length
              && j < tablePn.getMemberData()[modelRow].length) {
            rowsData.put(tablePn.getColumnNames()[i], tablePn.getMemberData()[modelRow][j]);
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
          showInforPn.getAvtPn().setImageUrlAbsolute(rowsData.get("Cover Image") + "");
          System.out.println(rowsData.get("Cover Image") + "");
          showInforPn.getAvtPn().setSize(120, 120);
          showInforPn.getFormPn().updateInfo(rowsData);
          showInforPn.getFormPn().revalidate(); // Bắt buộc
          showInforPn.getFormPn().repaint();
          showInforPn.getBtnPn().setMode(ButtonPanelMode.VIEW);
          readerPn.getContentPn().borrowedPanel.setVisible(true);
          readerPn
              .getContentPn()
              .borrowedPanel
              .setReader(ReaderData.getInstance().findId(Long.valueOf(rowsData.get("ID") + "")));
          readerPn.getContentPn().borrowedPanel.refreshTable();
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
