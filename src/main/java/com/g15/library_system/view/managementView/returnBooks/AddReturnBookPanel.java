package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.controller.ReaderController;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.swingComponentBuilders.ButtonBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Set;
import javax.swing.*;

public class AddReturnBookPanel extends JPanel {
  private ReaderInfoPanel readerInfoPanel;
  private CheckboxTablePanel tablePanel;
  private JButton confirmBt, cancelBt;
  private JTextField txtSearchReader,
      txtReaderID,
      txtFullName,
      txtEmail,
      txtPhone,
      txtReturnDate,
      txtLateFee,
      statusField;
  private JTextArea txtNotes;
  private JLabel lblStaff;
  // data
  private String[] columnNames = {
    "", "Book ID", "Cover Image", "Book Title", "Borrow Date", "Due Date", "Status"
  };
  private Object[][] borrowData = new Object[][] {};
  // controller
  private ReaderController readerController =
      ApplicationContextProvider.getBean(ReaderController.class);

  public AddReturnBookPanel() {
    this.setLayout(new BorderLayout(10, 15));
    readerInfoPanel = new ReaderInfoPanel();

    RoundedShadowPanel leftPanel = new RoundedShadowPanel();
    leftPanel.setLayout(new BorderLayout());
    leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 25, 10, 25));

    JPanel leftHeaderPanel = createHeaderPanel("Reader information");
    leftPanel.add(leftHeaderPanel, BorderLayout.NORTH);
    leftPanel.add(readerInfoPanel, BorderLayout.CENTER);

    RoundedShadowPanel rightPanel = new RoundedShadowPanel();
    rightPanel.setLayout(new BorderLayout());
    rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 25, 20, 25));

    //    borrowData =
    //        new Object[][] {
    //          {
    //            "B01",
    //            new ImageIcon(
    //                getClass()
    //
    // .getResource("/images/Harry_Potter_The_Prison_of_Azkaban_Book_cover.jpg")),
    //            "Java Programming",
    //            "2025-04-20",
    //            "2025-04-30",
    //            "lost"
    //          },
    //          {"B02", null, "Data Structures", "2025-04-15", "2025-04-25", "lost"}
    //        };

    tablePanel = new CheckboxTablePanel(columnNames, borrowData);
    tablePanel.setAlwaysEditableColumns(Set.of(6));

    JPanel rightHeaderPanel = createHeaderPanel("Books borrowed");

    rightPanel.add(rightHeaderPanel, BorderLayout.NORTH);
    rightPanel.add(tablePanel, BorderLayout.CENTER);

    this.add(leftPanel, BorderLayout.WEST);
    this.add(rightPanel, BorderLayout.CENTER);
  }

  private class ReaderInfoPanel extends JPanel {
    ReaderInfoPanel() {
      this.setLayout(new GridBagLayout());
      this.setBackground(Color.WHITE);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(6, 8, 6, 8);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      searchPanel.setOpaque(false);
      txtSearchReader =
          TextFieldBuilder.builder()
              .font(Style.FONT_PLAIN_13)
              .preferredSize(new Dimension(350, 35))
              .popupMenu(
                  name -> {
                    return readerController.searchIdContains(name);
                  },
                  selectedName -> {
                    Reader reader = readerController.findById(selectedName).orElse(null);
                    if (reader != null) {
                      txtReaderID.setText(String.valueOf(reader.getId()));
                      txtFullName.setText(reader.getFullName());
                      txtEmail.setText(reader.getEmail());
                      txtPhone.setText(reader.getPhoneNumber());
                      //                          txtReturnDate.setText(reader);

                    }
                  });

      txtSearchReader.putClientProperty("JTextField.placeholderText", "Enter Reader ID...");
      searchPanel.add(txtSearchReader);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      this.add(searchPanel, gbc);
      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 3;
      JSeparator sp = new JSeparator(SwingConstants.HORIZONTAL);
      sp.setPreferredSize(new Dimension(200, 2));
      sp.setForeground(Color.LIGHT_GRAY);
      this.add(sp, gbc);

      // Reader Info
      gbc.gridwidth = 1;
      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Reader ID:"), gbc);
      gbc.gridx = 1;
      txtReaderID = new JTextField(15);
      txtReaderID.setEditable(false);
      this.add(txtReaderID, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Full Name:"), gbc);
      gbc.gridx = 1;
      txtFullName = new JTextField(15);
      txtFullName.setEditable(false);
      this.add(txtFullName, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Email:"), gbc);
      gbc.gridx = 1;
      txtEmail = new JTextField(18);
      txtEmail.setEditable(false);
      this.add(txtEmail, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Phone:"), gbc);
      gbc.gridx = 1;
      txtPhone = new JTextField(15);
      txtPhone.setEditable(false);
      this.add(txtPhone, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Return Date:"), gbc);
      gbc.gridx = 1;
      txtReturnDate = new JTextField(LocalDate.now().toString(), 15);
      txtReturnDate.setEditable(false);
      this.add(txtReturnDate, gbc);

      gbc.gridx = 2;
      statusField = new JTextField("On due date");
      statusField.setFont(Style.FONT_BOLD_13);
      statusField.setHorizontalAlignment(JTextField.CENTER);
      statusField.setBackground(Style.GREEN_STATUS_BACKGROUND_COLOR);
      statusField.setForeground(Style.GREEN_STATUS_FOREGROUND_COLOR);
      statusField.setEditable(false);
      this.add(statusField, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Fine (VND):"), gbc);
      gbc.gridx = 1;
      txtLateFee = new JTextField("0");
      txtLateFee.setEditable(false);
      this.add(txtLateFee, gbc);

      gbc.gridx = 2;
      JComboBox<String> comboStrategy =
          new JComboBox<>(new String[] {"Default", "Per Day Penalty", "Flat Fee", "No Fee"});
      this.add(comboStrategy, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Processed By:"), gbc);
      gbc.gridx = 1;
      lblStaff = new JLabel("Nguyen Van A");
      this.add(lblStaff, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      gbc.gridwidth = 3;
      this.add(new JLabel("Notes:"), gbc);
      gbc.gridy++;
      txtNotes = new JTextArea(6, 20);
      txtNotes.setLineWrap(true);
      txtNotes.setWrapStyleWord(true);
      JScrollPane noteScroll = new JScrollPane(txtNotes);
      this.add(noteScroll, gbc);

      gbc.gridy++;
      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);

      confirmBt =
          ButtonBuilder.builder("Confirm Return")
              .backgroundColor(new Color(76, 175, 80))
              .textColor(Color.white)
              .font(Style.FONT_BOLD_15)
              .preferredSize(new Dimension(150, 40));
      confirmBt.addActionListener(e -> clearPanelData());

      cancelBt =
          ButtonBuilder.builder("Cancel")
              .backgroundColor(Color.WHITE)
              .textColor(Color.BLACK)
              .font(Style.FONT_BOLD_15)
              .preferredSize(new Dimension(150, 40));
      cancelBt.addActionListener(e -> clearPanelData());

      buttonPanel.add(cancelBt);
      buttonPanel.add(confirmBt);
      this.add(buttonPanel, gbc);
    }
  }

  public void setListenerConfirmBt(ActionListener actionListener) {
    confirmBt.addActionListener(actionListener);
  }

  public void setListenerCancelBt(ActionListener actionListener) {
    cancelBt.addActionListener(actionListener);
  }

  public void clearPanelData() {
    clearTableData();
    clearAllTextField();
  }

  public void clearAllTextField() {
    txtSearchReader.setText("");
    txtReaderID.setText("");
    txtFullName.setText("");
    txtEmail.setText("");
    txtPhone.setText("");
    txtReturnDate.setText("");
    txtLateFee.setText("0");
    statusField.setText("");
    txtNotes.setText("");
  }

  public void clearTableData() {
    tablePanel.removeAllDataTable();
  }

  private JPanel createHeaderPanel(String title) {
    JPanel headerPanel = new JPanel();
    headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
    headerPanel.setOpaque(false);

    JLabel titleLabel = new JLabel(title, SwingConstants.LEFT);
    titleLabel.setFont(Style.FONT_BOLD_20);

    JSeparator separator = new JSeparator();
    //    separator.setForeground(new Color(153, 153, 153, 55));
    separator.setForeground(Color.LIGHT_GRAY);
    separator.setPreferredSize(new Dimension(200, 2));

    headerPanel.add(titleLabel);
    headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    headerPanel.add(separator);
    headerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    return headerPanel;
  }
}
