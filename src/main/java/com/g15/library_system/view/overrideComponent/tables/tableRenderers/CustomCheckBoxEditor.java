package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import com.coveo.Check;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.function.Consumer;
import javax.swing.*;

public class CustomCheckBoxEditor extends DefaultCellEditor {
  private JCheckBox checkBox;
  private Consumer<Boolean> selectCheckBoxListener;

  public CustomCheckBoxEditor() {
    super(new JCheckBox());
    checkBox = (JCheckBox) getComponent();
    checkBox.setHorizontalAlignment(SwingConstants.CENTER);
    checkBox.setBackground(Color.WHITE);
  }

}
