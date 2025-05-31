package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.data.CacheData;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.dashboard.charts.*;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentBuilders.LabelBuilder;
import com.g15.library_system.view.swingComponentGenerators.TableGenerator;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DashBoardPanel extends JPanel {
  private Librarian librarian = CacheData.getCURRENT_LIBRARIAN();
  private WelcomePanel welcomePanel;
  private ContentPanel contentPanel;
  private QuickAccessPanel quickAccessPanel;

  public DashBoardPanel() {
    this.setLayout(new BorderLayout());
    this.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    welcomePanel = new WelcomePanel();
    this.add(welcomePanel, BorderLayout.NORTH);

    contentPanel = new ContentPanel();
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    TableGenerator.setColorScrollPane(
        scrollPane, Style.BLUE_MENU_BACKGROUND_COLOR, Style.CHART_BACKGROUND_COLOR);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    scrollPane.setBorder(null);
    this.add(scrollPane, BorderLayout.CENTER);
  }

  public class WelcomePanel extends JPanel {
    WelcomePanel() {
      this.setLayout(new GridBagLayout());
      this.setBackground(Style.LIGHT_WHITE_BACKGROUND);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 40, 10, 10);
      gbc.gridy = 0;
      gbc.fill = GridBagConstraints.NONE;

      //      AnimatedGradientTextLabel text3 =
      //          new AnimatedGradientTextLabel(
      //              "Hello, " + userName + "!",
      //              Style.FONT_BOLD_30,
      //              new Dimension(300, 50),
      //              SwingConstants.LEFT);
      JLabel greeting =
          LabelBuilder.builder()
              .text(
                  "<html><span style='color:black;'>Welcome, </span>"
                      + librarian.getFirstName()
                      + "!</html>")
              .font(Style.FONT_BOLD_30)
              .textColor(Style.BLUE_TEXT_COLOR)
              .preferredSize(new Dimension(400, 50))
              .horizontal(SwingConstants.LEFT);
      gbc.gridx = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.WEST;
      this.add(greeting, gbc);

      gbc.gridx = 1;
      gbc.weightx = 1;
      gbc.insets = new Insets(10, 10, 10, 10);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      JPanel glue = new JPanel();
      glue.setOpaque(false);
      this.add(glue, gbc);

      CustomButton operatingNotifyBt =
          CustomButtonBuilder.builder()
              .text(" Operating Hours: Monday to Saturday: 9 AM to 7 PM, Sunday: Closed")
              .font(Style.FONT_BOLD_15)
              .textColor(new Color(207, 156, 63))
              .backgroundColor(new Color(251, 242, 222))
              .hoverColor(null)
              .borderColor(new Color(207, 156, 63))
              .thickness(1)
              .radius(10)
              .alignment(SwingConstants.LEFT)
              .preferredSize(new Dimension(580, 40))
              .icon("/icons/infoYellowIcon.png", 20)
              .darkerWhenPress(false);
      gbc.gridx = 2;
      gbc.weightx = 0;
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      this.add(operatingNotifyBt, gbc);
    }
  }

  public class ContentPanel extends JPanel {
    private ReaderRegisteredTrendsChart readerChart;
    private ReaderTypesChart readerTypesChart;
    private BorrowOverviewChart booksByGenreChart;
    private BookAvailabilityChart bookAvailabilityChart;
    private LateBookReturnsChart lendingTrendsChart;
    private BorrowsByGenreChart borrowsByGenreChart;

    public ContentPanel() {
      this.setBackground(Style.LIGHT_WHITE_BACKGROUND);
      this.setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);
      gbc.fill = GridBagConstraints.BOTH;
      // ========== ROW 1 ==========
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3;
      quickAccessPanel = new QuickAccessPanel();
      this.add(quickAccessPanel, gbc);

      // ROW 1.5
      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 3;

      this.add(new TopChoicesPanel(), gbc);

      // ========== ROW 2 ==========
      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 2;
      readerChart = new ReaderRegisteredTrendsChart();
      this.add(readerChart, gbc);

      gbc.gridx = 2;
      gbc.gridwidth = 1;
      booksByGenreChart = new BorrowOverviewChart();
      this.add(booksByGenreChart, gbc);

      // ========== ROW 3 ==========
      gbc.gridx = 0;
      gbc.gridy++;
      gbc.gridwidth = 1;
      bookAvailabilityChart = new BookAvailabilityChart();
      this.add(bookAvailabilityChart, gbc);

      gbc.gridx = 1;
      gbc.gridwidth = 2;
      borrowsByGenreChart = new BorrowsByGenreChart();
      this.add(borrowsByGenreChart, gbc);
      // ========== ROW 4 ===========
//      gbc.gridx = 0;
//      gbc.gridy++;
//      gbc.gridwidth = 2;
//      lendingTrendsChart = new LateBookReturnsChart();
//      this.add(lendingTrendsChart, gbc);
//
//      gbc.gridx = 2;
//      gbc.gridwidth = 1;
//      readerTypesChart = new ReaderTypesChart();
//      this.add(readerTypesChart, gbc);
    }
  }

  public void setTotalBooksCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setTotalBooksCardButtonListener(listener);
  }

  public void setLendedBooksCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setLendedBooksCardButtonListener(listener);
  }

  public void setReturnedBooksCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setReturnedBooksCardButtonListener(listener);
  }

  public void setReadersCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setReadersCardButtonListener(listener);
  }

  public void setLibrariansCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setLibrariansCardButtonListener(listener);
  }

  public void setMyAccountCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setMyAccountCardButtonListener(listener);
  }
}
