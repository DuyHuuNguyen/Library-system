package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.entity.Reader;
import com.g15.library_system.view.Style;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ShowPanel extends JPanel {
  public AvatarPanel avtPn;
  public ButtonPanel btnPn;
  public FormPanel formPn;

  public ShowPanel(String btn1, String btn2, ReaderPanel readerPn) {
    avtPn = new AvatarPanel();
    btnPn = new ButtonPanel(new AvatarPanel(), readerPn);
    formPn = new FormPanel();

    // Set up the panel
    setLayout(new BorderLayout(5, 0));
    setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
    setBackground(Color.WHITE);
    setBorder(createTitleLineBorder("Member Information"));
    //    setPreferredSize(new Dimension(400, 580));

    // Avatar Panel
    add(avtPn, BorderLayout.NORTH);

    // Form Panel
    add(formPn, BorderLayout.CENTER);

    // Buttons panel
    add(btnPn, BorderLayout.SOUTH);
  }

  public TitledBorder createTitleLineBorder(String title) {
    LineBorder border = new LineBorder(Style.BLUE_MENU_BACKGROUND_COLOR, 2);
    TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title);
    titledBorder.setTitleColor(Style.BLUE_MENU_BACKGROUND_COLOR);
    titledBorder.setTitleFont(Style.FONT_BOLD_20);
    return titledBorder;
  }
}
