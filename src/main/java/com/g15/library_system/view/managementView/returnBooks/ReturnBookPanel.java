package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.awt.*;
import javax.swing.*;

public class ReturnBookPanel extends JPanel {
  private ToolPanel toolPn;
  private ContentPanel contentPn;
  private TablePanel tablePanel;
  private AddReturnBookPanel addReturnBookPanel;
  private CardLayout cardLayout;

  public ReturnBookPanel() {
    cardLayout = new CardLayout(10, 10);
    this.setLayout(cardLayout);

    tablePanel = new TablePanel();
    this.add(tablePanel, "tablePanel");

    addReturnBookPanel = new AddReturnBookPanel();
    backToTableAction();
    this.add(addReturnBookPanel, "addReturnBookPanel");

    cardLayout.show(this, "tablePanel");
  }

  public void showPanel(String panelTitle) {
    cardLayout.show(this, panelTitle);
  }

  private class TablePanel extends JPanel {
    public TablePanel() {
      setLayout(new BorderLayout());
      contentPn = new ContentPanel();
      this.add(contentPn, BorderLayout.CENTER);
      toolPn = new ToolPanel(contentPn);
      setAddBtListener();
      this.add(toolPn, BorderLayout.NORTH);
    }

    private void setAddBtListener() {
      toolPn.setAddButtonListener(e -> showPanel("addReturnBookPanel"));
    }
  }

  private void backToTableAction() {
    addReturnBookPanel.setListenerConfirmBt(
        e -> {
          int result =
              JOptionPane.showConfirmDialog(
                  JOptionPane.getFrameForComponent(this),
                  "Are you sure you want to add this book return for the reader?",
                  "Confirm Return",
                  JOptionPane.YES_NO_OPTION);

          if (result == JOptionPane.YES_OPTION) {
            showPanel("tablePanel");
            new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.SUCCESS,
                    ToastNotification.Location.TOP_CENTER,
                    "New book returned successfully!")
                .showNotification();
          }
        });
    addReturnBookPanel.setListenerCancelBt(e -> showPanel("tablePanel"));
  }
}
