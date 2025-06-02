package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.entity.Reader;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.returnBooks.strategies.FineStrategyType;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.swingComponentBuilders.ButtonBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.*;

public class AddReturnBookPanel extends JPanel {
  private ReaderInfoPanel readerInfoPanel;
  private CheckboxTablePanel borrowBookTablePanel;
  private JButton confirmBt, cancelBt;
  private JComboBox<FineStrategyType> comboStrategy;
  private JTextField searchReaderTxt,
      readerIdTxt,
      fullNameTxt,
      emailTxt,
      phoneTxt,
      returnDateTxt,
      lateFeeTxt,
      statusTxt;
  private JTextArea notesTxtArea;
  private JLabel currentStaff, noDataLabel;
  private LocalDate today = LocalDate.now();

  // data
  private String[] columnNames = {
    "",
    "Borrow ID",
    "Book ID",
    "Cover Image",
    "Book Title",
    "Quantity",
    "Borrow Date",
    "Due Date",
    "Return Quantity",
    "Status"
  };
  private String[] statuses = {"On time", "Overdue", "Lost"};
  private Object[][] bookBorrowData = new Object[][] {};

  public AddReturnBookPanel() {
    this.setLayout(new BorderLayout(10, 15));
    readerInfoPanel = new ReaderInfoPanel();

    RoundedShadowPanel leftPanel = new RoundedShadowPanel();
    leftPanel.setBorder(BorderFactory.createEmptyBorder(30, 25, 10, 25));
    leftPanel.add(createHeaderPanel("Reader information"), BorderLayout.NORTH);
    leftPanel.add(readerInfoPanel, BorderLayout.CENTER);

    RoundedShadowPanel rightPanel = new RoundedShadowPanel();
    rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 25, 20, 25));
    rightPanel.add(createHeaderPanel("Books borrowed"), BorderLayout.NORTH);
    rightPanel.add(new BorrowedBookTable(), BorderLayout.CENTER);

    this.add(leftPanel, BorderLayout.WEST);
    this.add(rightPanel, BorderLayout.CENTER);
  }

  private class ReaderInfoPanel extends JPanel {
    public ReaderInfoPanel() {
      this.setLayout(new GridBagLayout());
      this.setBackground(Color.WHITE);

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(6, 8, 6, 8);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
      searchPanel.setOpaque(false);
      searchReaderTxt =
          TextFieldBuilder.builder()
              .font(Style.FONT_PLAIN_13)
              .preferredSize(new Dimension(350, 35));
      searchReaderTxt.putClientProperty("JTextField.placeholderText", "Enter Reader ID...");

      {
        searchPanel.add(searchReaderTxt);
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
        readerIdTxt = new JTextField(15);
        readerIdTxt.setEditable(false);
        this.add(readerIdTxt, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        fullNameTxt = new JTextField(15);
        fullNameTxt.setEditable(false);
        this.add(fullNameTxt, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailTxt = new JTextField(18);
        emailTxt.setEditable(false);
        this.add(emailTxt, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1;
        phoneTxt = new JTextField(15);
        phoneTxt.setEditable(false);
        this.add(phoneTxt, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Return Date:"), gbc);
        gbc.gridx = 1;
        returnDateTxt = new JTextField(today.toString(), 15);
        returnDateTxt.setEditable(false);
        this.add(returnDateTxt, gbc);

        gbc.gridx = 2;
        statusTxt = new JTextField();
        statusTxt.setFont(Style.FONT_BOLD_13);
        statusTxt.setHorizontalAlignment(JTextField.CENTER);
        statusTxt.setEditable(false);
        this.add(statusTxt, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Fine (VND):"), gbc);
        gbc.gridx = 1;
        lateFeeTxt = new JTextField("0");
        lateFeeTxt.setEditable(false);
        this.add(lateFeeTxt, gbc);

        gbc.gridx = 2;
        comboStrategy = new JComboBox<>(FineStrategyType.values());
        this.add(comboStrategy, gbc);
        //        comboStrategy.addActionListener(e -> {
        //          setComboBoxAction();
        //        });

        gbc.gridy++;
        gbc.gridx = 0;
        this.add(new JLabel("Processed By:"), gbc);
        gbc.gridx = 1;
        currentStaff = new JLabel("Nguyen Van A");
        this.add(currentStaff, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        this.add(new JLabel("Notes:"), gbc);
        gbc.gridy++;
        notesTxtArea = new JTextArea(6, 20);
        notesTxtArea.setLineWrap(true);
        notesTxtArea.setWrapStyleWord(true);
        JScrollPane noteScroll = new JScrollPane(notesTxtArea);
        this.add(noteScroll, gbc);
      }
      gbc.gridy++;
      JPanel buttonPanel = new JPanel();
      buttonPanel.setOpaque(false);

      confirmBt =
          ButtonBuilder.builder("Confirm Return")
              .backgroundColor(new Color(76, 175, 80))
              .textColor(Color.white)
              .font(Style.FONT_BOLD_15)
              .preferredSize(new Dimension(150, 40));

      cancelBt =
          ButtonBuilder.builder("Cancel")
              .backgroundColor(Color.WHITE)
              .textColor(Color.BLACK)
              .font(Style.FONT_BOLD_15)
              .preferredSize(new Dimension(150, 40));
      cancelBt.addActionListener(e -> clearFormAndTableData());

      buttonPanel.add(cancelBt);
      buttonPanel.add(confirmBt);
      this.add(buttonPanel, gbc);
    }
  }

  private class BorrowedBookTable extends JPanel {
    private JPanel noDataPn;

    public BorrowedBookTable() {
      this.setOpaque(false);
      this.setLayout(new OverlayLayout(this));

      noDataPn = new JPanel(new BorderLayout());
      noDataPn.setOpaque(false);
      // Create no data label
      noDataLabel = new JLabel("No borrowed books found", SwingConstants.CENTER);
      noDataLabel.setFont(Style.FONT_BOLD_20);
      noDataLabel.setForeground(Style.LOGOUT_RED);
      noDataPn.add(noDataLabel, BorderLayout.CENTER);
      noDataPn.setVisible(false);

      borrowBookTablePanel = new CheckboxTablePanel(columnNames, bookBorrowData);
      borrowBookTablePanel.setAlwaysEditableColumns(Set.of(8, 9));
      borrowBookTablePanel.setStatuses(statuses);

      this.add(noDataPn);
      this.add(borrowBookTablePanel);
    }

    public void showNoDataMessage(boolean show) {
      noDataPn.setVisible(show);
      borrowBookTablePanel.setVisible(!show);
    }
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

  // validate textField and table
  public boolean isSearchFieldEmpty(Reader readerFound) {
    if (searchReaderTxt.getText().isEmpty() && readerIdTxt.getText().isEmpty()) {
      JOptionPane.showMessageDialog(
          this, "Please enter a reader ID", "Error", JOptionPane.ERROR_MESSAGE);
      return true;
    }

    if (readerFound == null) {
      JOptionPane.showMessageDialog(
          this, "Please select a valid reader", "Error", JOptionPane.ERROR_MESSAGE);
      return true;
    }
    return false;
  }

  public boolean hasNoBookSelected() {

    if (borrowBookTablePanel.getCheckedRows().size() == 0) {
      JOptionPane.showMessageDialog(
          this, "Please select books to return", "Error", JOptionPane.ERROR_MESSAGE);
      return true;
    }
    return false;
  }

  // get and set data textFields and table
  public List<Object[]> getSelectedRowsData() {
    return borrowBookTablePanel.getSelectedRowsData();
  }

  public FineStrategyType getSelectedStrategy() {
    return (FineStrategyType) comboStrategy.getSelectedItem();
  }

  public String getLateFeeText() {
    return lateFeeTxt.getText().trim();
  }

  public void setLateFeeText(String lateFee) {
    lateFeeTxt.setText(lateFee);
  }

  public String getNotesText() {
    return notesTxtArea.getText();
  }

  public String getSearchFieldText() {
    return searchReaderTxt.getText().trim();
  }

  public void setReaderFields(String id, String fullName, String email, String phone) {
    readerIdTxt.setText(id);
    fullNameTxt.setText(fullName);
    emailTxt.setText(email);
    phoneTxt.setText(phone);
  }

  public void setNewBorrowDataForTable(Object[][] tableData) {
    this.bookBorrowData = tableData;
    borrowBookTablePanel.setNewDataForTable(tableData);

    // Show "No data" message if table is empty
    boolean isEmpty = tableData == null || tableData.length == 0;
    // Tìm BorrowedBookTable parent
    Container parent = borrowBookTablePanel;
    while (parent != null && !(parent instanceof BorrowedBookTable)) {
      parent = parent.getParent();
    }

    if (parent instanceof BorrowedBookTable) {
      ((BorrowedBookTable) parent).showNoDataMessage(isEmpty);
    }
  }

  public void setStatusFieldText(String status) {
    statusTxt.setText(status);
    if (status.equals("On due date")) {
      statusTxt.setBackground(Style.GREEN_STATUS_BACKGROUND_COLOR);
      statusTxt.setForeground(Style.GREEN_STATUS_FOREGROUND_COLOR);
    } else if (status.equals("Overdue")) {
      statusTxt.setBackground(Style.RED_STATUS_BACKGROUND_COLOR);
      statusTxt.setForeground(Style.RED_STATUS_FOREGROUND_COLOR);
    } else {
      statusTxt.setBackground(Style.YELLOW_STATUS_BACKGROUND_COLOR);
      statusTxt.setForeground(Style.YELLOW_STATUS_FOREGROUND_COLOR);
    }
  }

  public void setCurrentStaff(String staffName) {
    currentStaff.setText(staffName);
  }

  // action listener
  public void setConfirmBtListener(ActionListener actionListener) {
    confirmBt.addActionListener(actionListener);
  }

  public void setCancelBtListener(ActionListener actionListener) {
    cancelBt.addActionListener(actionListener);
  }

  public void setComboBoxListener(ActionListener actionListener) {
    comboStrategy.addActionListener(actionListener);
  }

  public void setSearchFieldListener(ActionListener actionListener) {
    searchReaderTxt.addActionListener(actionListener);
  }

  public void setupSearchFieldPopupListener(
      Function<String, List<String>> contextProvider, Consumer<String> onSelect) {
    TextFieldBuilder newField =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(350, 35))
            .popupMenu(contextProvider, onSelect);
    newField.putClientProperty("JTextField.placeholderText", "Enter Reader ID...");

    // Replace old field with new one
    ActionListener[] existingListeners = searchReaderTxt.getActionListeners();

    Container parent = searchReaderTxt.getParent();
    int index = Arrays.asList(parent.getComponents()).indexOf(searchReaderTxt);
    parent.remove(searchReaderTxt);
    searchReaderTxt = newField;
    for (ActionListener listener : existingListeners) {
      searchReaderTxt.addActionListener(listener);
    }
    parent.add(searchReaderTxt, index);
    parent.revalidate();
    parent.repaint();
  }

  public void clearFormAndTableData() {
    clearTableData();
    clearAllTextField();
  }

  public void clearAllTextField() {
    searchReaderTxt.setText("");
    readerIdTxt.setText("");
    fullNameTxt.setText("");
    emailTxt.setText("");
    phoneTxt.setText("");
    lateFeeTxt.setText("0.0");
    statusTxt.setText("");
    notesTxtArea.setText("");
  }

  public void clearTableData() {
    borrowBookTablePanel.removeAllDataTable();
    borrowBookTablePanel.clearHeaderCheckboxSelection();
  }
}
