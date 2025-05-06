package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ContentPanel extends JPanel {
  public TablePanel tablePn;
  public ShowPanel showPn = new ShowPanel("Edit", "Remove");

  public ContentPanel(ReaderPanel readerPn) {
    tablePn = new TablePanel(readerPn);

    setLayout(new GridLayout(1, 2, 10, 20));

    CheckboxTablePanel table = tablePn.tablePanel;
    table.setRowSelectionHandler(
        modelRow -> {
          // Viết hành động bạn muốn tại đây
          Map<String, Object> rowsData = new HashMap<>();
          for (int i = 1; i < tablePn.columnNames.length; i++) {
            for (int j = i; j < tablePn.memberData[modelRow].length; j++) {
              rowsData.put(tablePn.columnNames[i], tablePn.memberData[modelRow][j]);
              break;
            }
          }

          showPn.formPn.updateInfo(rowsData);
          showPn.formPn.revalidate(); // Bắt buộc
          showPn.formPn.repaint();
          showPn.btnPn.setMode(ButtonPanelMode.VIEW);
          // Ví dụ: mở form, bật checkbox, đổi trạng thái...
        });

    add(tablePn);
    add(showPn);
  }
}
