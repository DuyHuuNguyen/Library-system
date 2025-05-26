package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {
  public JButton saveBt;
  public JButton editBt;
  public JButton removeBt;
  public JButton addBt;
  public JButton cancelBt;

  private ButtonPanelMode mode;

  public ButtonPanel(AvatarPanel avatar, ReaderPanel readerPn) {
    setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 30));
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    setPreferredSize(new Dimension(this.getWidth(), 70));

    // OK button
    saveBt =
        CustomButtonBuilder.builder()
            .text("OK")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30))
            .icon("/icons/save.png", 12);

    saveBt.addActionListener(
        e -> {
          readerPn.toolPn.enableTextFields(readerPn.contentPn.showInforPn.formPn, false);
          readerPn.contentPn.showInforPn.btnPn.setMode(ButtonPanelMode.VIEW);
          JOptionPane.showMessageDialog(this, "Saved!");
        });

    // Add button
    editBt =
        CustomButtonBuilder.builder()
            .text("Edit")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30))
            .icon("/icons/edit.png", 12);
    editBt.addActionListener(
        e -> {
          // Add functionality here
          readerPn.toolPn.enableTextFields(readerPn.contentPn.showInforPn.formPn, true);
          readerPn.contentPn.showInforPn.btnPn.setMode(ButtonPanelMode.EDIT);
        });

    // Remove button
    removeBt =
        CustomButtonBuilder.builder()
            .text("Remove")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(220, 68, 84))
            .hoverColor(new Color(220, 68, 84).darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(120, 30))
            .icon("/icons/deleteIcon.png", 12);
    removeBt.addActionListener(
        e -> {
          readerPn.contentPn.showInforPn.formPn.resetFields();
          avatar.resetAvatar();
        });

    // Add Button
    addBt =
        CustomButtonBuilder.builder()
            .text("Add")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30));

    add(addBt);

    addBt.addActionListener(
        e -> {
          FormPanel formPn = readerPn.contentPn.showInforPn.formPn;
          if (formPn.validateForm()) {
            //                          Boolean isCreated =
            //
            // transactionController.createTransaction(formPn.createTransaction());
            //                          if (isCreated) {
            new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.SUCCESS,
                    ToastNotification.Location.BOTTOM_RIGHT,
                    "Borrow successfully!!")
                .showNotification();
            readerPn.toolPn.enableTextFields(formPn, false);
            //
            // -- Lưu dữ liệu đã nhập vào database ở đây
            //
            this.setMode(ButtonPanelMode.VIEW);
            //                          }
          }
        });

    // Cancel button
    cancelBt =
        CustomButtonBuilder.builder()
            .text("Cancel")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(220, 68, 84))
            .hoverColor(new Color(220, 68, 84).darker())
            .radius(12)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(120, 30));

    add(cancelBt);
  }

  public void setMode(ButtonPanelMode mode) {
    this.mode = mode;
    updateButtons();
    //    setEditable(mode == InfoPanelMode.ADD);
  }

  private void updateButtons() {
    removeAllButtons();

    if (mode == ButtonPanelMode.VIEW) {
      add(editBt);
      add(removeBt);
    } else if (mode == ButtonPanelMode.ADD) {
      add(addBt);
      add(cancelBt);
    } else if (mode == ButtonPanelMode.EDIT) {
      add(saveBt);
    }

    revalidate();
    repaint();
  }

  private void removeAllButtons() {
    remove(saveBt);
    remove(editBt);
    remove(removeBt);
    remove(addBt);
    remove(cancelBt);
  }
}
