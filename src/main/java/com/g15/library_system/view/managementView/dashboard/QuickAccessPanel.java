package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.data.BookData;
import com.g15.library_system.data.LibrarianData;
import com.g15.library_system.data.ReaderData;
import com.g15.library_system.observers.BookObserver;
import com.g15.library_system.observers.LibrarianObserver;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.service.bookService.BookService;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuickAccessPanel extends RoundedShadowPanel
    implements BookObserver, LibrarianObserver, ReaderObserver {
  private DashboardCard totalBooksCard,
      lendedBooksCard,
      returnedBookCard,
      readersCard,
      librariansCard,
      overdueBooksCard,
      myAccountCard;
  private int totalBooks, lendedBooks, returnedBooks, overdueBooks, readers, librarians;
  private BookService bookService = new BookService();

  public QuickAccessPanel() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setLayout(new FlowLayout(FlowLayout.CENTER, 13, 15));
    // Total Books
    this.totalBooks = BookData.getInstance().totalBooksQuantity();
    BookData.getInstance().registerObserver(this);
    totalBooksCard =
        new DashboardCard(
            "/icons/totalBook2.png",
            "Total Books",
            String.valueOf(totalBooks),
            new Color(255, 238, 215), // old color
            new Color(255, 160, 0),
            new Color(170, 105, 0));

    // Lended Books
    this.lendedBooks = bookService.getTotalBorrowedBooks();
    lendedBooksCard =
        new DashboardCard(
            "/icons/lendBook.png",
            "Lended Books",
            String.valueOf(lendedBooks),
            new Color(255, 245, 200),
            new Color(255, 204, 0),
            new Color(145, 117, 17));

    this.returnedBooks = bookService.getTotalReturnedBooks();
    returnedBookCard =
        new DashboardCard(
            "/icons/returnedBook.png",
            "Returned Books",
            String.valueOf(returnedBooks),
            new Color(235, 235, 255),
            new Color(130, 130, 250),
            new Color(90, 90, 180));

    // Overdue Books
    //    this.overdueBooks = bookService.getTotalOverdueBooks();
    //    overdueBooksCard =
    //        new DashboardCard(
    //            "/icons/overdue.png",
    //            "Overdue Books",
    //            String.valueOf(overdueBooks),
    //            new Color(230, 255, 255),
    //            new Color(70, 200, 200),
    //            new Color(40, 140, 140));

    // Available Books
    this.readers = ReaderData.getInstance().getReaders().size();
    ReaderData.getInstance().registerObserver(this);
    readersCard =
        new DashboardCard(
            "/icons/readers.png",
            "Total Readers",
            String.valueOf(readers),
            new Color(230, 255, 255),
            new Color(70, 200, 200),
            new Color(40, 140, 140));

    // Total Users
    this.librarians = LibrarianData.getInstance().getLibrarians().size();
    LibrarianData.getInstance().registerObserver(this);
    librariansCard =
        new DashboardCard(
            "/icons/users2.png",
            "Total Librarians",
            String.valueOf(librarians),
            new Color(230, 255, 230),
            new Color(80, 200, 80),
            new Color(50, 140, 50));

    myAccountCard =
        new DashboardCard(
            "/icons/myAccount.png",
            "My Account",
            String.valueOf(1),
            new Color(255, 235, 235),
            new Color(255, 105, 180),
            new Color(180, 70, 120));

    this.add(totalBooksCard);
    this.add(lendedBooksCard);
    this.add(returnedBookCard);
    //    this.add(overdueBooksCard);
    this.add(readersCard);
    this.add(librariansCard);
    this.add(myAccountCard);
  }

  public void setTotalBooksCardButtonListener(ActionListener listener) {
    this.totalBooksCard.setDashBoardCardButtonListener(listener);
  }

  public void setLendedBooksCardButtonListener(ActionListener listener) {
    this.lendedBooksCard.setDashBoardCardButtonListener(listener);
  }

  public void setReturnedBooksCardButtonListener(ActionListener listener) {
    this.returnedBookCard.setDashBoardCardButtonListener(listener);
  }

  public void setReadersCardButtonListener(ActionListener listener) {
    this.readersCard.setDashBoardCardButtonListener(listener);
  }

  public void setLibrariansCardButtonListener(ActionListener listener) {
    this.librariansCard.setDashBoardCardButtonListener(listener);
  }

  public void setOverdueBooksCardButtonListener(ActionListener listener) {
    this.overdueBooksCard.setDashBoardCardButtonListener(listener);
  }

  public void setMyAccountCardButtonListener(ActionListener listener) {
    this.myAccountCard.setDashBoardCardButtonListener(listener);
  }

  @Override
  public void updateLibrarianData() {
    this.librarians = LibrarianData.getInstance().getLibrarians().size();
    librariansCard.setAmount(String.valueOf(librarians));
  }

  @Override
  public void updateReaderData() {
    this.readers = ReaderData.getInstance().getReaders().size();
    this.lendedBooks = bookService.getTotalBorrowedBooks();
    this.returnedBooks = bookService.getTotalReturnedBooks();
    //    this.overdueBooks = bookService.getTotalOverdueBooks();
    readersCard.setAmount(String.valueOf(readers));
    lendedBooksCard.setAmount(String.valueOf(lendedBooks));
    returnedBookCard.setAmount(String.valueOf(returnedBooks));
    //    overdueBooksCard.setAmount(String.valueOf(overdueBooks));
  }

  @Override
  public void updateBookData() {
    this.totalBooks = BookData.getInstance().totalBooksQuantity();
    totalBooksCard.setAmount(String.valueOf(totalBooks));
  }
}
