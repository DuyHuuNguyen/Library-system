package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;

// public class ReaderPanel extends JPanel {
//  private ToolPanel toolPn;
//  private ReaderPanelMain readerPanelMain;
//  private AddMemberPanel addMemberPanel;
//  private CardLayout cardLayout;
//
//  public ReaderPanel() {
//    cardLayout = new CardLayout(10, 10);
//    this.setLayout(cardLayout);
//
//    readerPanelMain = new ReaderPanelMain();
//    this.add(readerPanelMain, "readerPanel");
//
//    addMemberPanel = new AddMemberPanel();
//    backToTableAction();
//    this.add(addMemberPanel, "addMemberPanel");
//
//    cardLayout.show(this, "readerPanel");
//  }
//
//  public void showPanel(String panelTitle) {
//    cardLayout.show(this, panelTitle);
//  }
//
//  private class AddMemberPanel extends JPanel {
//    private JButton addBtn;
//    private JButton cancelBtn;
//    public AddMemberPanel() {
//      setLayout(new BorderLayout());
//      ShowPanel showPn = new ShowPanel("Add", "Cancel");
//      addBtn = showPn.btnPn.editBt;
//      cancelBtn = showPn.btnPn.removeBt;
//
//      add(showPn, BorderLayout.EAST);
//    }
//
//    public void setListenerCancelBt(ActionListener actionListener) {
//      cancelBtn.addActionListener(actionListener);
//    }
//
//    public void setListenerAddBt(ActionListener actionListener) {
//      addBtn.addActionListener(actionListener);
//    }
//  }
//
//  private void backToTableAction() {
//    addMemberPanel.setListenerAddBt(
//            e -> {
//              int result =
//                      JOptionPane.showConfirmDialog(
//                              JOptionPane.getFrameForComponent(this),
//                              "Are you sure you want to add this book return for the reader?",
//                              "Confirm Return",
//                              JOptionPane.YES_NO_OPTION);
//
//              if (result == JOptionPane.YES_OPTION) {
//                showPanel("tablePanel");
//                new ToastNotification(
//                        JOptionPane.getFrameForComponent(this),
//                        ToastNotification.Type.SUCCESS,
//                        ToastNotification.Location.BOTTOM_RIGHT,
//                        "New book added successfully!")
//                        .showNotification();
//              }
//            });
//    addMemberPanel.setListenerCancelBt(e -> showPanel("readerPanel"));
//  }
//
//
// }

@Getter
@Setter
public class ReaderPanel extends JPanel {
  private ContentPanel contentPn = new ContentPanel(this);
  private ToolPanel toolPn = new ToolPanel(this);
  private FooterPanel footerPn = new FooterPanel();

  public ReaderPanel() {
    setLayout(new BorderLayout());
    // Tool Panel
    add(toolPn, BorderLayout.NORTH);

    // Content Panel
    add(contentPn, BorderLayout.CENTER);

    // Footer Panel
    add(footerPn, BorderLayout.SOUTH);
  }

  //  private void setAddBtListener() {
  //    toolPn.setAddButtonListener(e -> showPanel("addMemberPanel"));
  //  }
}
