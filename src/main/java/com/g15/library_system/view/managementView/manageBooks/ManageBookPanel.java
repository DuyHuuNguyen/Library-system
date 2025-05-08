package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import javax.swing.*;

public class ManageBookPanel extends JPanel {

  private JPanel panelContent;

  private CheckboxTablePanel checkboxTablePanel;
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

    this.add(new ToolPanel(cardLayout, panelContent), BorderLayout.NORTH);

    this.panelContent.setBackground(Color.GREEN);

    this.initHeaderTable();

    this.initData();

    this.checkboxTablePanel = new CheckboxTablePanel(columns, data);
    this.panelContent.add(checkboxTablePanel, CONSTRAINT_TABLE_BOOK);

    this.bookFormAndDropImagesPanel = new JPanel(new BorderLayout());
    this.bookFormAndDropImagesPanel.setBackground(Color.PINK);
    this.bookFormPanel = new UpsertBookPanel(1000, 500);

    this.bookFormAndDropImagesPanel.add(bookFormPanel, BorderLayout.CENTER);

    this.panelContent.add(bookFormAndDropImagesPanel, CONSTRAINT_ADD_NEW_BOOK);

    this.panelContent.add(new UpsertBookPanel(1000, 500), CONSTRAINT_MODIFY_BOOK);

    this.panelContent.add(new NotifyNewBookPanel(), CONSTRAINT_NOTIFY);

    this.panelContent.add(
        new UpsertBookPanel(
            1000,
            500,
            Book.builder()
                .id(5L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("J.R.R. Tolkien")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Hobbit")
                .publisher("George Allen & Unwin")
                .publishYear(1937)
                .genreType(GenreType.FANTASY)
                .currentQuantity(7)
                .totalQuantity(35)
                .build()),
        CONSTRAINT_MODIFY_BOOK);

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

  private void initData() {
    this.data =
        new Object[][] {
          {
            false,
            "B001",
            "The Great Gatsby",
            "F. Scott Fitzgerald",
            "Classic",
            "English",
            5,
            "lost"
          },
          {false, "B002", "1984", "George Orwell", "Dystopian", "English", 3, "Checked Out"},
          {
            false,
            "B003",
            "To Kill a Mockingbird",
            "Harper Lee",
            "Classic",
            "English",
            4,
            "Available"
          },
          {
            false,
            "B004",
            "Chí Phèo",
            "Nam Cao",
            "Vietnamese Literature",
            "Vietnamese",
            2,
            "Checked Out"
          },
          {
            false,
            "B005",
            "Kafka on the Shore",
            "Haruki Murakami",
            "Fiction",
            "Japanese",
            6,
            "Available"
          }
        };
  }
}
