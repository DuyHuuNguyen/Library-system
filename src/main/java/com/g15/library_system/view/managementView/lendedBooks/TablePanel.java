package com.g15.library_system.view.managementView.lendedBooks;

import com.g15.library_system.controller.TransactionController;
import com.g15.library_system.data.TransactionData;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.TransactionMapper;
import com.g15.library_system.mapper.impl.TransactionMapperImpl;
import com.g15.library_system.observers.TransactionObserver;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class TablePanel extends RoundedPanel implements TransactionObserver {
  private final CheckboxTablePanel transactionTable;
  private final TransactionController transactionController =
      ApplicationContextProvider.getBean(TransactionController.class);
  private final TransactionMapper transactionMapper =
      ApplicationContextProvider.getBean(TransactionMapperImpl.class);
  private List<Transaction> transactions;
  private final JButton addNewButton, refreshButton;

  public TablePanel(CardLayout cardLayout, JPanel parentPanel) {
    super(10, Color.WHITE, null);
    setLayout(new BorderLayout(10, 10));

    TransactionData.getInstance().registerObserver(this);

    String[] columnNames = {"", "ID", "User Name", "Issue Date", "Due Date", "Description"};

    transactions = transactionController.getByType(TransactionType.BORROW);

    Object[][] tableData = transactionMapper.toTransactionBorrowData(transactions);

    transactionTable = new CheckboxTablePanel(columnNames, tableData);

    add(transactionTable, BorderLayout.CENTER);

    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setOpaque(false);
    headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    addNewButton =
        CustomButtonBuilder.builder()
            .text("Lend New Book")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .drawBorder(false)
            .preferredSize(new Dimension(150, 40));

    addNewButton.addActionListener(
        e -> {
          cardLayout.show(parentPanel, "form");
        });

    refreshButton =
            CustomButtonBuilder.builder()
            .text("Refresh")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .drawBorder(false)
            .preferredSize(new Dimension(150, 40));

    refreshButton.addActionListener(e -> updateTransactionData());

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    buttonPanel.setOpaque(false);
    buttonPanel.add(addNewButton);
    buttonPanel.add(refreshButton);
    headerPanel.add(buttonPanel, BorderLayout.WEST);

    add(headerPanel, BorderLayout.NORTH);

    updateTransactionData();
  }

  @Override
  public void updateTransactionData() {
    transactionTable.removeAllDataTable();

    transactions = transactionController.getByType(TransactionType.BORROW);

    Object[][] tableData = transactionMapper.toTransactionBorrowData(transactions);
    this.transactionTable.addDataToTable(tableData);
  }
}
