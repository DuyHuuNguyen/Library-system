package com.g15.library_system.view.managementView.lendedBooks;

import com.g15.library_system.controller.TransactionController;
import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.TransactionMapper;
import com.g15.library_system.mapper.impl.TransactionMapperImpl;
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
  private TablePanel tablePn;
  private CardLayout cardLayout;
  private ContainerPn containerPn;

  private TransactionController transactionController =
      ApplicationContextProvider.getBean(TransactionController.class);

  private TransactionMapper transactionMapper =
      ApplicationContextProvider.getBean(TransactionMapperImpl.class);

  public LendedBookPanel() {
    setLayout(new BorderLayout());
    cardLayout = new CardLayout();
    containerPn = new ContainerPn();
    add(containerPn, BorderLayout.CENTER);
  }

  private class ContainerPn extends RoundedPanel {
    private final String FORM_PANEL = "form";
    private final String TABLE_PANEL = "table";

    public ContainerPn() {
      super(10, Color.WHITE, null);
      setLayout(cardLayout);

      formPn = new FormPanel();
      tablePn = new TablePanel(cardLayout, this);

      add(formPn, FORM_PANEL);
      add(tablePn, TABLE_PANEL);

      cardLayout.show(this, TABLE_PANEL);
    }

    public void showTablePanel() {
      cardLayout.show(this, TABLE_PANEL);
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
      buttonPn = new ButtonPanel();

      JPanel contentPanel = new JPanel();
      contentPanel.setLayout(new BorderLayout(10, 10));

      JPanel rightSidePanel = new JPanel(new BorderLayout(0, 10));
      rightSidePanel.setOpaque(false);

      RoundedShadowPanel bookContainer = new RoundedShadowPanel();
      bookContainer.setOpaque(false);
      bookContainer.add(bookPn, BorderLayout.NORTH);
      rightSidePanel.add(bookContainer, BorderLayout.CENTER);

      RoundedShadowPanel leftPanel = new RoundedShadowPanel();
      leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
      leftPanel.setOpaque(false);

      leftPanel.add(Box.createVerticalStrut(10));
      leftPanel.add(userPn);

      leftPanel.add(Box.createVerticalStrut(10));
      leftPanel.add(detailPn);

      JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
      separator.setForeground(new Color(200, 200, 200));
      separator.setAlignmentX(Component.CENTER_ALIGNMENT);

      leftPanel.add(separator);

      JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
      buttonWrapper.setOpaque(false);
      buttonWrapper.add(buttonPn);
      buttonWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
      buttonWrapper.setMaximumSize(new Dimension(Short.MAX_VALUE, 60));

      leftPanel.add(buttonWrapper);
      leftPanel.add(Box.createVerticalStrut(10));

      Dimension halfSize = new Dimension(400, 0);
      leftPanel.setPreferredSize(halfSize);

      contentPanel.add(leftPanel, BorderLayout.WEST);
      contentPanel.add(rightSidePanel, BorderLayout.CENTER);

      add(contentPanel, BorderLayout.CENTER);
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

    public void sendEmail(TransactionContentDTO transaction) {
      if (detailPn.enableNotification()) {
        transactionController.notifyBorrowTransaction(transaction);
      }
    }

    public void updateBookQuantity() {
      bookPn.updateBookQuantity();
    }
  }

  private class ButtonPanel extends JPanel {
    public ButtonPanel() {
      setLayout(new FlowLayout(FlowLayout.CENTER));
      setOpaque(false);
      JButton cancelButton =
          CustomButtonBuilder.builder()
              .text("Cancel")
              .font(Style.FONT_SANS_SERIF_PLAIN_15)
              .textColor(Style.BLUE_MENU_BACKGROUND_COLOR)
              .backgroundColor(Color.WHITE)
              .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
              .radius(6)
              .alignment(SwingConstants.CENTER)
              .borderColor(Style.BLUE_MENU_BACKGROUND_COLOR)
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
              containerPn.showTablePanel();
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
              Transaction transaction = formPn.createTransaction();
              transaction = transactionController.createTransaction(transaction);
              formPn.updateBookQuantity();
              if (transaction != null) {
                new ToastNotification(
                        JOptionPane.getFrameForComponent(this),
                        ToastNotification.Type.SUCCESS,
                        ToastNotification.Location.BOTTOM_RIGHT,
                        "Borrow successfully!!")
                    .showNotification();
                containerPn.showTablePanel();
                formPn.sendEmail(transactionMapper.convertToContentDTO(transaction));
                formPn.cancel();
              }
            }
          });
      add(cancelButton);
      add(lendButton);
    }
  }
}
