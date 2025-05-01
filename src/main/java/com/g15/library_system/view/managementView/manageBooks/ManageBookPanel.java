package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.NotifyNewBookPanel;
import com.g15.library_system.view.overrideComponent.TablePanel;
import com.g15.library_system.view.overrideComponent.UpsertBookPanel;

import java.awt.*;
import javax.swing.*;

public class ManageBookPanel extends JPanel {

  private JPanel panelContent;
  private JPanel tools;

  private TablePanel table;
  private String[] columns;
  private Object[][] data;

  private JPanel bookFormAndDropImagesPanel;
  private UpsertBookPanel bookFormPanel;

  private CardLayout cardLayout;

  public static final String CONSTRAINT_TABLE_BOOK = "book_table";
  public static final String CONSTRAINT_ADD_NEW_BOOK = "add_new_book";
  public static final String CONSTRAINT_MODIFY_BOOK = "modify_book";
  public static final String CONSTRAINT_NOTIFY = "notify_new_book";

  public ManageBookPanel() {
    setLayout(new BorderLayout());
    this.cardLayout = new CardLayout();
    this.panelContent = new JPanel(cardLayout);

    this.add(new ToolPanel(this.cardLayout, this.panelContent), BorderLayout.NORTH);

    this.panelContent.setBackground(Color.GREEN);

    this.initHeaderTable();
    this.table = new TablePanel(columns, data, 200, 200);

    this.panelContent.add(table, CONSTRAINT_TABLE_BOOK);

    this.bookFormAndDropImagesPanel = new JPanel(new BorderLayout());
    this.bookFormAndDropImagesPanel.setBackground(Color.PINK);
    this.bookFormPanel = new UpsertBookPanel(1000, 500);

    this.bookFormAndDropImagesPanel.add(bookFormPanel, BorderLayout.CENTER);

    this.panelContent.add(bookFormAndDropImagesPanel, CONSTRAINT_ADD_NEW_BOOK);

    this.panelContent.add(new UpsertBookPanel(1000,500), CONSTRAINT_MODIFY_BOOK);// add book
    this.panelContent.add(new NotifyNewBookPanel(), CONSTRAINT_NOTIFY);

    add(panelContent, BorderLayout.CENTER);
    this.setBackground(Style.LIGHT_WHITE_BACKGROUND);
  }

  private void initHeaderTable() {
    this.columns =
        new String[] {
          "",
          "Book ID",
          "Book Title",
          "Author(s)",
          "Genre/Category",
          "Language",
          "Total Copies",
          "Status"
        };
  }
}
