package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import java.awt.*;
import javax.swing.*;

public class CustomCheckBoxEditor extends DefaultCellEditor {
  private JCheckBox checkBox;

  public CustomCheckBoxEditor() {
    super(new JCheckBox());
    checkBox = (JCheckBox) getComponent();
    checkBox.setHorizontalAlignment(SwingConstants.CENTER);
    checkBox.setBackground(Color.PINK);
  }
}
