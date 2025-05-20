package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import javax.swing.*;

public class ButtonPanel extends JPanel {
  public JButton editBt;
  public JButton removeBt;
  public JButton addBt;
  public JButton cancelBt;

  private ButtonPanelMode mode;

  public ButtonPanel(AvatarPanel avatar, FormPanel form) {
    setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 30));
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    setPreferredSize(new Dimension(this.getWidth(), 70));

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
          JOptionPane.showMessageDialog(this, "Member edit successfully!");
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
          form.resetFields();
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
    }

    revalidate();
    repaint();
  }

  private void removeAllButtons() {
    remove(editBt);
    remove(removeBt);
    remove(addBt);
    remove(cancelBt);
  }
}
