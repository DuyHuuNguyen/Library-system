package com.g15.library_system.view.managementView.lendedBooks;

import com.g15.library_system.controller.TransactionController;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.lendedBooks.formBody.*;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import javax.swing.*;

public class LendedBookPanel extends JPanel {
  private FormPanel formPn;
  private ButtonPanel buttonPn;

  private TransactionController transactionController =
      ApplicationContextProvider.getBean(TransactionController.class);

  public LendedBookPanel() {
    setLayout(new BorderLayout());
    ContainerPn containerPn = new ContainerPn();
    add(containerPn, BorderLayout.CENTER);
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

  private class FormPanel extends JPanel {
    private UserPanel userPn;
    private BookPanel bookPn;
    private DetailPanel detailPn;

    public FormPanel() {
      setLayout(new BorderLayout(10, 10));

      userPn = new UserPanel();
      bookPn = new BookPanel();
      detailPn = new DetailPanel();

      RoundedShadowPanel leftPanel = new RoundedShadowPanel();
      setLayout(new BorderLayout(10, 10));
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      leftPanel.setOpaque(false);
      leftPanel.add(Box.createVerticalStrut(10));
      leftPanel.add(userPn);
      leftPanel.add(Box.createVerticalStrut(10));
      leftPanel.add(detailPn);
      Dimension halfSize = new Dimension(400, 0);
      leftPanel.setPreferredSize(halfSize);
      add(leftPanel, BorderLayout.WEST);

      RoundedShadowPanel rightPanel = new RoundedShadowPanel();
      rightPanel.setOpaque(false);
      rightPanel.add(bookPn, BorderLayout.NORTH);
      add(rightPanel, BorderLayout.CENTER);
    }

    private void cancel() {
      userPn.cancel();
      bookPn.cancel();
      detailPn.cancel();
    }

    public Transaction createTransaction() {
      Transaction transaction =
          Transaction.builder().transactionType(TransactionType.BORROW).build();
      userPn.accept(transaction);
      bookPn.accept(transaction);
      detailPn.accept(transaction);
      return transaction;
    }

    public boolean validateForm() {
      try {
        userPn.isValidate();
        bookPn.isValidate();
        return true;
      } catch (IllegalArgumentException e) {
        new ToastNotification(
                JOptionPane.getFrameForComponent(this),
                ToastNotification.Type.WARNING,
                ToastNotification.Location.TOP_CENTER,
                e.getMessage())
            .showNotification();
        return false;
      }
    }
  }

  private class ButtonPanel extends JPanel {
    public ButtonPanel() {
      setLayout(new FlowLayout(FlowLayout.CENTER));
      JButton cancelButton =
          CustomButtonBuilder.builder()
              .text("Cancel")
              .font(Style.FONT_SANS_SERIF_PLAIN_15)
              .textColor(Style.BLUE_MENU_BACKGROUND_COLOR)
              .backgroundColor(Color.WHITE)
              .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
              .radius(6)
              .alignment(SwingConstants.CENTER)
              .drawBorder(false)
              .preferredSize(new Dimension(120, 40));
      cancelButton.addActionListener(
          e -> {
            int option =
                JOptionPane.showConfirmDialog(
                    null,
                    "Do you want to cancel?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {
              formPn.cancel();
            }
          });

      JButton lendButton =
          CustomButtonBuilder.builder()
              .text("Lend a Book")
              .font(Style.FONT_SANS_SERIF_PLAIN_15)
              .textColor(Color.WHITE)
              .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
              .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
              .radius(6)
              .alignment(SwingConstants.LEFT)
              .drawBorder(false)
              .preferredSize(new Dimension(120, 40));
      lendButton.addActionListener(
          e -> {
            if (formPn.validateForm()) {
              Boolean isCreated =
                  transactionController.createTransaction(formPn.createTransaction());
              if (isCreated) {
                new ToastNotification(
                        JOptionPane.getFrameForComponent(this),
                        ToastNotification.Type.SUCCESS,
                        ToastNotification.Location.BOTTOM_RIGHT,
                        "Borrow successfully!!")
                    .showNotification();
                formPn.cancel();
              }
            }
          });
      add(cancelButton);
      add(lendButton);
    }
  }
}
