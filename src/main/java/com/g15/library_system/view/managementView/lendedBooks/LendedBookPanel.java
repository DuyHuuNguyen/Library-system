package com.g15.library_system.view.managementView.lendedBooks;

import com.g15.library_system.view.managementView.lendedBooks.formBody.*;
import com.g15.library_system.view.overrideComponent.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class LendedBookPanel extends JPanel {

  public LendedBookPanel() {
    setLayout(new FlowLayout(FlowLayout.CENTER));
    ContainerPn containerPn =  new  ContainerPn();
    containerPn.setPreferredSize(new Dimension(1200, 750));

    add(containerPn);
  }

  private class ContainerPn extends RoundedPanel {
    ContainerPn(){
      super(10, Color.WHITE, null);
      setLayout(new BorderLayout());
      add(new FormPanel(), BorderLayout.CENTER);
      add(new ButtonPanel(), BorderLayout.SOUTH);
    }
  }

  private class FormPanel extends RoundedPanel {
    public FormPanel() {
      super(20, Color.WHITE, null);
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      add(new UserPanel());
      add(Box.createVerticalStrut(10));
      add(new BookPanel());
      add(Box.createVerticalStrut(10));
      add(new DetailPanel());
      add(Box.createVerticalStrut(10));
      add(new OptionPanel());
    }
  }

  private class ButtonPanel extends JPanel {
    public ButtonPanel() {
      setLayout(new FlowLayout(FlowLayout.RIGHT));
      JButton cancelButton = new JButton("Cancel");
      JButton lendButton = new JButton("Lend a Book");
      add(cancelButton);
      add(lendButton);
    }
  }
}

