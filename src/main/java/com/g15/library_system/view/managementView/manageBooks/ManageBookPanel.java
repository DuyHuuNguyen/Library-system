package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.NotifyNewBookPanel;
import com.g15.library_system.view.overrideComponent.TablePanel;
import com.g15.library_system.view.overrideComponent.UpsertBookPanel;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
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

  private final String CONSTRAINT_TABLE_BOOK = "book_table";
  private final String CONSTRAINT_ADD_NEW_BOOK = "add_new_book";
  private final String CONSTRAINT_MODIFY_BOOK = "modify_book";
  private final String CONSTRAINT_NOTIFY = "notify_new_book";

  public ManageBookPanel() {
    setLayout(new BorderLayout());
    add(new ToolPanel(), BorderLayout.NORTH);

    tools = new JPanel();
    tools.setLayout(new FlowLayout(FlowLayout.LEFT));
    tools.setBackground(Color.YELLOW);
    var btn1 = CustomButtonBuilder.builder().text("table");
    var btn2 = new Button("add");
    var btn3 = new Button("update");
    var btn4 = new Button("NOTIFY");
    tools.add(btn1);
    tools.add(btn2);
    tools.add(btn3);
    tools.add(btn4);

    add(tools, BorderLayout.NORTH);

    this.cardLayout = new CardLayout();

    btn1.addActionListener(e -> cardLayout.show(panelContent, CONSTRAINT_TABLE_BOOK));
    btn2.addActionListener(e -> cardLayout.show(panelContent, CONSTRAINT_ADD_NEW_BOOK));
    btn3.addActionListener(e -> cardLayout.show(panelContent, CONSTRAINT_MODIFY_BOOK));
    btn4.addActionListener(e -> cardLayout.show(panelContent, CONSTRAINT_NOTIFY));

    this.panelContent = new JPanel(cardLayout);
    this.panelContent.setBackground(Color.GREEN);

    this.initHeaderTable();
    this.table = new TablePanel(columns, data, 200, 200);

    this.panelContent.add(table, CONSTRAINT_TABLE_BOOK);

    this.bookFormAndDropImagesPanel = new JPanel(new BorderLayout());
    this.bookFormAndDropImagesPanel.setBackground(Color.PINK);
    this.bookFormPanel = new UpsertBookPanel(1000, 500);

    this.bookFormAndDropImagesPanel.add(bookFormPanel, BorderLayout.CENTER);

    this.panelContent.add(bookFormAndDropImagesPanel, CONSTRAINT_ADD_NEW_BOOK);

    this.panelContent.add(new Panel(), CONSTRAINT_MODIFY_BOOK);
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
