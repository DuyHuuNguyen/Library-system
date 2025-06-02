package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.entity.Book;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.dashboard.BookCoverPanel;
import com.g15.library_system.view.managementView.dashboard.chartObserver.FilterObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.TitlePanel;
import com.g15.library_system.view.managementView.dashboard.statistics.BookStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;
import javax.swing.*;

public class TopChoicesPanel extends RoundedShadowPanel implements FilterObserver, ReaderObserver {
  private JPanel booksPanel;
  private BookStatistics bookStatistics = new BookStatistics();
  private Map<Book, Long> bookData;
  private int thisYear = LocalDate.now().getYear();

  public TopChoicesPanel() {
    this.setBackground(Color.WHITE);
    this.setLayout(new BorderLayout());

    bookData = bookStatistics.getMostBorrowedBooks(6, thisYear);

    TitlePanel titlePn = new TitlePanel("Top Choices");
    titlePn.getYearComboBox().setSelectedItem(thisYear);

    booksPanel = new JPanel();
    booksPanel.setOpaque(false);
    booksPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.addTopChoicesDetails();

    this.add(titlePn, BorderLayout.NORTH);
    titlePn.addObserver(this);
    this.add(booksPanel, BorderLayout.CENTER);
  }

  private void addTopChoicesDetails() {
    if (bookData == null || bookData.isEmpty()) {
      booksPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      JLabel noDataLabel = new JLabel("No data available", SwingConstants.CENTER);
      noDataLabel.setPreferredSize(new Dimension(500, 50));
      noDataLabel.setFont(Style.FONT_BOLD_15);
      noDataLabel.setForeground(Style.LOGOUT_RED);
      booksPanel.add(noDataLabel);
      return;
    }
    booksPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    for (Map.Entry<Book, Long> entry : bookData.entrySet()) {
      Book book = entry.getKey();
      String imagePath = book.getFirstImage();
      String title = book.getTitle();
      String author = book.getAuthor();

      BookCoverPanel bookCoverPanel = new BookCoverPanel(imagePath, title, author, 160, 220);
      booksPanel.add(bookCoverPanel);
    }
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    if (month == null || month.equalsIgnoreCase("All")) {
      bookData = bookStatistics.getMostBorrowedBooks(6, year);
    } else {
      bookData = bookStatistics.getMostBorrowedBooks(6, month, year);
    }

    this.booksPanel.removeAll();
    this.addTopChoicesDetails();
    this.booksPanel.revalidate();
    this.booksPanel.repaint();
  }

  @Override
  public void updateReaderData() {
    bookData.clear();
    bookData = bookStatistics.getMostBorrowedBooks(6, LocalDate.now().getYear());
    this.booksPanel.removeAll();
    this.addTopChoicesDetails();
    this.booksPanel.revalidate();
    this.booksPanel.repaint();
  }
}
