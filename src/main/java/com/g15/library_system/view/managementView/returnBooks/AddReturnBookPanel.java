package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AddReturnBookPanel extends JPanel {
  private ReaderInfoPanel readerInfoPanel;
  private CheckboxTablePanel tablePanel;
  private CustomButton confirmBt, cancelBt;
  private JTextField txtReaderID, txtFullName, txtEmail, txtPhone, txtReturnDate, txtLateFee;
  private JTextArea txtNotes;
  private JLabel lblStaff;
  private String[] columnNames = {
    "", "Book ID", "Cover Image", "Book Title", "Borrow Date", "Due Date", "Status"
  };
  private Object[][] borrowData;

  public AddReturnBookPanel() {
    this.setLayout(new BorderLayout());
    this.setBackground(Color.WHITE);
    readerInfoPanel = new ReaderInfoPanel();

    JPanel rightPanel = new JPanel(new BorderLayout());
    rightPanel.setBorder(createTitleLineBorder("Borrowed Books"));

    borrowData =
        new Object[][] {
          {
            false,
            "B01",
            new ImageIcon(
                getClass()
                    .getResource("/images/Harry_Potter_The_Prison_of_Azkaban_Book_cover.jpg")),
            "Java Programming",
            "2025-04-20",
            "2025-04-30",
            "lost"
          },
          {false, "B02", null, "Data Structures", "2025-04-15", "2025-04-25", "lost"}
        };

    tablePanel = new CheckboxTablePanel(columnNames, borrowData);
    tablePanel.setEditableColumns(Set.of(5));
    tablePanel.setStatusEditable(true);
    rightPanel.add(tablePanel, BorderLayout.CENTER);

    add(readerInfoPanel, BorderLayout.WEST);
    this.add(rightPanel, BorderLayout.CENTER);
  }

  private class ReaderInfoPanel extends JPanel {
    ReaderInfoPanel() {
      this.setLayout(new GridBagLayout());

      this.setBorder(createTitleLineBorder("Return Information"));

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(6, 8, 6, 8);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      JTextField txtSearchReader = new JTextField(25);
      searchPanel.add(txtSearchReader);
      JButton btnSearch = new JButton("🔎 Search");
      searchPanel.add(btnSearch);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      this.add(searchPanel, gbc);
      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 3;
      this.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);

      // Reader Info
      gbc.gridwidth = 1;
      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Reader ID:"), gbc);
      gbc.gridx = 1;
      txtReaderID = new JTextField(15);
      txtReaderID.setEditable(false);
      txtReaderID.setText("DG001");
      this.add(txtReaderID, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Full Name:"), gbc);
      gbc.gridx = 1;
      txtFullName = new JTextField(15);
      txtFullName.setEditable(false);
      txtFullName.setText("John Doe");
      this.add(txtFullName, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Email:"), gbc);
      gbc.gridx = 1;
      txtEmail = new JTextField(15);
      txtEmail.setEditable(false);
      txtEmail.setText("xxx@gmail.com");
      this.add(txtEmail, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Phone:"), gbc);
      gbc.gridx = 1;
      txtPhone = new JTextField(15);
      txtPhone.setEditable(false);
      txtPhone.setText("0912345678");
      this.add(txtPhone, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Return Date:"), gbc);
      gbc.gridx = 1;
      txtReturnDate = new JTextField(LocalDate.now().toString(), 15);
      txtReturnDate.setEditable(false);
      this.add(txtReturnDate, gbc);

      gbc.gridx = 2;
      JTextField statusField = new JTextField("On due date");
      statusField.setHorizontalAlignment(JTextField.CENTER);
      statusField.setBackground(Style.GREEN_STATUS_BACKGROUND_COLOR);
      statusField.setForeground(Style.GREEN_STATUS_FOREGROUND_COLOR);
      statusField.setEditable(false);
      this.add(statusField, gbc);

      gbc.gridy++;
      gbc.gridx = 0;
      this.add(new JLabel("Late Fee (VND):"), gbc);
      gbc.gridx = 1;
      txtLateFee = new JTextField(10);
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
      confirmBt =
          CustomButtonBuilder.builder()
              .text("Confirm Return")
              .backgroundColor(new Color(76, 175, 80))
              .textColor(Color.white)
              .drawBorder(false)
              .preferredSize(new Dimension(150, 40))
              .radius(12)
              .hoverColor(new Color(76, 175, 80).darker());

      cancelBt =
          CustomButtonBuilder.builder()
              .text("Cancel")
              .backgroundColor(Color.WHITE)
              .textColor(Color.BLACK)
              .drawBorder(false)
              .radius(12)
              .hoverColor(Color.white.darker())
              .preferredSize(new Dimension(150, 40));
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

  public TitledBorder createTitleLineBorder(String title) {
    LineBorder border = new LineBorder(Style.BLUE_MENU_BACKGROUND_COLOR, 2);
    TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title);
    titledBorder.setTitleColor(Style.BLUE_MENU_BACKGROUND_COLOR);
    titledBorder.setTitleFont(Style.FONT_BOLD_20);
    return titledBorder;
  }
}
