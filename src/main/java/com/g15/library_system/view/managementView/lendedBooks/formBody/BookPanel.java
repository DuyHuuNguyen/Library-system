package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.BookMapper;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.cards.BookCardPanel;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class BookPanel extends JPanel {
  private ButtonPanel buttonPanel;
  private JPanel cardPanel;
  private TablePanel tablePanel;
  private AddBookPanel addBookPanel;
  private CardLayout cardLayout;
  private Map<Book, Integer> bookWithQuantity = new TreeMap<>();

  private final BookController bookController =
      ApplicationContextProvider.getBean(BookController.class);
  private final BookMapper bookMapper = ApplicationContextProvider.getBean(BookMapper.class);

  private class ButtonPanel extends JPanel {
    private CustomButton addBookBtn, backBtn, summitBtn;

    private enum ButtonState {
      ADD_BOOK {
        @Override
        public void update(JButton addBookBtn, JButton backBtn, JButton summitBtn) {
          addBookBtn.setVisible(true);
          backBtn.setVisible(false);
          summitBtn.setVisible(false);
        }
      },
      BACK {
        @Override
        public void update(JButton addBookBtn, JButton backBtn, JButton summitBtn) {
          addBookBtn.setVisible(false);
          backBtn.setVisible(true);
          summitBtn.setVisible(true);
        }
      };

      public abstract void update(JButton addBookBtn, JButton backBtn, JButton summitBtn);
    }

    public ButtonPanel() {
      setLayout(new FlowLayout(FlowLayout.LEFT));
      setOpaque(false);
      addBookBtn =
          CustomButtonBuilder.builder()
              .text("Add Book")
              .font(Style.FONT_SANS_SERIF_PLAIN_15)
              .textColor(Color.WHITE)
              .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
              .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
              .radius(6)
              .alignment(SwingConstants.LEFT)
              .drawBorder(false)
              .preferredSize(new Dimension(120, 40));
      backBtn =
          CustomButtonBuilder.builder()
              .text("Back")
              .font(Style.FONT_SANS_SERIF_PLAIN_15)
              .textColor(Color.WHITE)
              .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
              .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
              .radius(6)
              .alignment(SwingConstants.LEFT)
              .drawBorder(false)
              .preferredSize(new Dimension(120, 40));
      backBtn.setVisible(false);

      summitBtn =
          CustomButtonBuilder.builder()
              .text("Summit")
              .font(Style.FONT_SANS_SERIF_PLAIN_15)
              .textColor(Color.WHITE)
              .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
              .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
              .radius(6)
              .alignment(SwingConstants.LEFT)
              .drawBorder(false)
              .preferredSize(new Dimension(120, 40));
      summitBtn.setVisible(false);

      add(addBookBtn);
      add(backBtn);
      add(summitBtn);

      addBookBtn.addActionListener(
          e -> {
            cardLayout.show(cardPanel, "ADD");
            showButton(ButtonState.BACK);
          });

      backBtn.addActionListener(
          e -> {
            addBookPanel.cancel();
            cardLayout.show(cardPanel, "TABLE");
            showButton(ButtonState.ADD_BOOK);
          });

      summitBtn.addActionListener(
          e -> {
            tablePanel.updateTable(addBookPanel.getSelectedBooks());
            cardLayout.show(cardPanel, "TABLE");
            showButton(ButtonState.ADD_BOOK);
            addBookPanel.cancel();
          });
    }

    public void showButton(ButtonState state) {
      state.update(addBookBtn, backBtn, summitBtn);
    }
  }

  private class TablePanel extends JPanel {
    private CheckboxTablePanel bookTable;

    public TablePanel() {
      setLayout(new BorderLayout());
      setOpaque(false);
      String[] columnNames = {
        "", "Title", "Author", "Publisher", "Public Year", "GenreType", "Quantity"
      };

      Object[][] tableData = bookMapper.toBookDataWithQuantity(bookWithQuantity);

      bookTable = new CheckboxTablePanel(columnNames, tableData);
      add(bookTable, BorderLayout.CENTER);
    }

    public void cancel() {
      bookTable.removeAllDataTable();
    }

    public void updateTable(Map<Book, Integer> selectedBooks) {
      for (Map.Entry<Book, Integer> entry : selectedBooks.entrySet()) {
        Integer value = bookWithQuantity.getOrDefault(entry.getKey(), entry.getValue() - 1) + 1;
        bookWithQuantity.put(entry.getKey(), value);
      }
      bookTable.removeAllDataTable();
      bookTable.addDataToTable(bookMapper.toBookDataWithQuantity(bookWithQuantity));
    }
  }

  private class AddBookPanel extends JPanel {
    private JTextField titleTF;
    private JLabel titleL;
    private JPanel bookCardHolder;
    private JScrollPane scrollPane;

    private final Set<String> addedBookTitles = new HashSet<>();

    public AddBookPanel() {
      setLayout(new BorderLayout());
      setOpaque(false);

      JPanel formPanel = new JPanel(new GridBagLayout());
      formPanel.setOpaque(false);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.insets = new Insets(5, 5, 5, 5);

      titleL = LabelGenerator.createRequireLabel("Book Title");
      titleTF =
          TextFieldBuilder.builder()
              .font(Style.FONT_PLAIN_13)
              .popupMenu(
                  text -> bookController.searchTitleContains(text),
                  selectedTitle -> {
                    Book book = bookController.findByTitle(selectedTitle).orElse(null);
                    if (book != null) {
                      addBookCard(book);
                      KeyboardFocusManager.getCurrentKeyboardFocusManager()
                          .focusNextComponent(titleTF);
                    }
                    titleTF.setText("");
                    titleTF.requestFocusInWindow();
                  })
              .preferredSize(new Dimension(300, 25));

      gbc.gridx = 1;
      gbc.gridy = 1;
      formPanel.add(titleL, gbc);
      gbc.gridx++;
      formPanel.add(titleTF, gbc);

      bookCardHolder = new JPanel();
      bookCardHolder.setBackground(Color.WHITE);
      bookCardHolder.setLayout(new BoxLayout(bookCardHolder, BoxLayout.Y_AXIS));
      scrollPane = new JScrollPane(bookCardHolder);
      scrollPane.setBackground(Color.WHITE);
      scrollPane.setPreferredSize(new Dimension(600, 250));
      scrollPane.setBorder(BorderFactory.createTitledBorder("Selected Books"));

      add(formPanel, BorderLayout.NORTH);
      add(scrollPane, BorderLayout.CENTER);
    }

    private void addBookCard(Book book) {
      String titleKey = book.getTitle().trim().toLowerCase();
      if (addedBookTitles.contains(titleKey)) {
        JOptionPane.showMessageDialog(this, "This book is already added.");
        return;
      }

      BookCardPanel cardPanel = new BookCardPanel(book, () -> addedBookTitles.remove(titleKey));
      bookCardHolder.add(cardPanel);
      bookCardHolder.revalidate();
      bookCardHolder.repaint();
      addedBookTitles.add(titleKey);
    }

    public void cancel() {
      titleTF.setText("");
      bookCardHolder.removeAll();
      bookCardHolder.revalidate();
      bookCardHolder.repaint();
      addedBookTitles.clear();
    }

    public Map<Book, Integer> getSelectedBooks() {
      Map<Book, Integer> list = new TreeMap<>();
      for (Component comp : bookCardHolder.getComponents()) {
        if (comp.getClass().equals(BookCardPanel.class)) {
          BookCardPanel card = (BookCardPanel) comp;
          list.put(card.getBook(), card.getQuantity());
        }
      }
      return list;
    }

    @Override
    public void addNotify() {
      super.addNotify();
      addAncestorListener(
          new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
              SwingUtilities.invokeLater(() -> titleTF.requestFocusInWindow());
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {}

            @Override
            public void ancestorMoved(AncestorEvent event) {}
          });
    }
  }

  public BookPanel() {
    Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
    Border titled =
        BorderFactory.createTitledBorder(whiteLine, "Book Information", 0, 0, Style.FONT_BOLD_20);
    Border padding = BorderFactory.createEmptyBorder(0, 20, 10, 20);
    setBorder(BorderFactory.createCompoundBorder(padding, titled));
    setOpaque(false);
    init();
  }

  private void init() {
    setLayout(new BorderLayout());
    cardLayout = new CardLayout(0, 0);
    cardPanel = new JPanel(cardLayout);
    cardPanel.setPreferredSize(new Dimension(500, 650));
    cardPanel.setOpaque(false);

    addBookPanel = new AddBookPanel();
    buttonPanel = new ButtonPanel();
    tablePanel = new TablePanel();
    cardPanel.add(tablePanel, "TABLE");
    cardPanel.add(addBookPanel, "ADD");

    add(buttonPanel, BorderLayout.NORTH);
    add(cardPanel, BorderLayout.CENTER);
  }

  public void cancel() {
    cardLayout.show(cardPanel, "TABLE");
    buttonPanel.showButton(ButtonPanel.ButtonState.ADD_BOOK);
    addBookPanel.cancel();
    tablePanel.cancel();
    bookWithQuantity.clear();
  }

  public void accept(Transaction transaction) {
    transaction.setBooks(bookWithQuantity);
    transaction.setDescription(
        TransactionType.BORROW
            + ": "
            + bookWithQuantity.entrySet().stream()
                .map(entry -> entry.getKey().getTitle() + " quantity: " + entry.getValue())
                .collect(Collectors.joining(", ")));
  }

  public void isValidate() {
    if (bookWithQuantity.isEmpty()) {
      throw new IllegalArgumentException("Empty book!!");
    }
  }

  public void updateBookQuantity() {
    bookController.updateBookQuantity(bookWithQuantity);
  }
}
