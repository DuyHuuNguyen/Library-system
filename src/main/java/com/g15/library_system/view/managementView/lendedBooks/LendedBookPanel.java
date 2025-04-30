package com.g15.library_system.view.managementView.lendedBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.lendedBooks.formBody.*;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;

import java.awt.*;
import javax.swing.*;

public class LendedBookPanel extends JPanel {
  private FormPanel formPn;
  private ButtonPanel buttonPn;

  public LendedBookPanel() {
    setLayout(new FlowLayout(FlowLayout.CENTER));
    ContainerPn containerPn = new ContainerPn();
    containerPn.setPreferredSize(new Dimension(1200, 750));

    add(containerPn);
  }

  private class ContainerPn extends RoundedPanel {
    ContainerPn() {
      super(10, Color.WHITE, null);
      setLayout(new BorderLayout());
      formPn = new FormPanel();
      buttonPn = new ButtonPanel();
      add(formPn, BorderLayout.CENTER);
      add(buttonPn, BorderLayout.SOUTH);
    }
  }

  private class FormPanel extends RoundedPanel {
    private UserPanel userPn;
    private BookPanel bookPn;
    private DetailPanel detailPn;
    private OptionPanel optionPn;

    public FormPanel() {
      super(20, Color.WHITE, null);
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      userPn = new UserPanel();
      bookPn = new BookPanel();
      detailPn = new DetailPanel();
      optionPn = new OptionPanel();
      add(Box.createVerticalStrut(10));
      add(userPn);
      add(Box.createVerticalStrut(10));
      add(bookPn);
      add(Box.createVerticalStrut(10));
      add(detailPn);
      add(Box.createVerticalStrut(10));
      add(optionPn);
    }

    private void cancel() {
      userPn.cancel();
      bookPn.cancel();
      detailPn.cancel();
      optionPn.cancel();
    }
  }

  private class ButtonPanel extends JPanel {
    public ButtonPanel() {
      setLayout(new FlowLayout(FlowLayout.CENTER));
      JButton cancelButton =
          CustomButtonBuilder.builder()
              .text("Cancel")
              .backgroundColor(Color.WHITE)
              .borderColor(Color.BLACK)
              .thickness(1)
              .darkerWhenPress(true);
      cancelButton.addActionListener(e -> {
        int option = JOptionPane.showConfirmDialog(
                null,
                "Do you want to cancel?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (option == JOptionPane.YES_OPTION) {
          formPn.cancel();
        }
      });

      JButton lendButton =
          CustomButtonBuilder.builder()
              .text("Lend a Book")
              .textColor(Color.WHITE)
              .backgroundColor(Style.PURPLE_MAIN_THEME)
              .borderColor(Color.BLACK)
              .thickness(1)
              .darkerWhenPress(true);
      add(cancelButton);
      add(lendButton);
    }
  }
}
