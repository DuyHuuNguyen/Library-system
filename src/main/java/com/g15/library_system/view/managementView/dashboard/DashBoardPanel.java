package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.AnimatedGradientTextLabel;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.swingComponentGenerators.ButtonGenerator;
import com.g15.library_system.view.swingComponentGenerators.TableGenerator;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DashBoardPanel extends JPanel {
  private String userName = "Huy Hoang";
  private WelcomePanel welcomePanel;
  private ContentPanel contentPanel;
  private QuickAccessPanel quickAccessPanel;

  public DashBoardPanel() {
    this.setLayout(new BorderLayout());
    setBackground(Style.LIGHT_BLUE_BACKGROUND);
    welcomePanel = new WelcomePanel();
    this.add(welcomePanel, BorderLayout.NORTH);

    contentPanel = new ContentPanel();
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    TableGenerator.setColorScrollPane(
        scrollPane, Style.PURPLE_MAIN_THEME, Style.CHART_BACKGROUND_COLOR);
    add(scrollPane, BorderLayout.CENTER);
  }

  public class WelcomePanel extends JPanel {
    WelcomePanel() {
      this.setLayout(new GridBagLayout());
      setBackground(Style.LIGHT_BLUE_BACKGROUND);
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 5, 10, 10);
      gbc.gridy = 0;
      gbc.fill = GridBagConstraints.NONE;

      AnimatedGradientTextLabel text3 =
          new AnimatedGradientTextLabel(
              "Hello, " + userName + "!",
              Style.FONT_BOLD_30,
              new Dimension(300, 50),
              SwingConstants.LEFT);
      gbc.gridx = 0;
      gbc.weightx = 0;
      gbc.anchor = GridBagConstraints.WEST;
      this.add(text3, gbc);

      gbc.gridx = 1;
      gbc.weightx = 1;
      gbc.fill = GridBagConstraints.HORIZONTAL;
      JPanel glue = new JPanel();
      glue.setOpaque(false);
      this.add(glue, gbc);

      CustomButton operatingNotifyBt =
          ButtonGenerator.createCustomButton(
              "Operating Hours: Monday to Saturday: 9 AM to 7 PM, Sunday: Closed",
              Style.FONT_BOLD_15,
              new Color(207, 156, 63),
              new Color(251, 242, 222),
              null,
              new Color(207, 156, 63),
              1,
              10,
              SwingConstants.LEFT,
              new Dimension(570, 40));
      operatingNotifyBt.setIsDarkerWhenPress(false);
      ButtonGenerator.setButtonIcon(
          "src/main/java/view/LibraryUI/icons/infoYellowIcon.png", operatingNotifyBt, 23);

      gbc.gridx = 2;
      gbc.weightx = 0;
      gbc.fill = GridBagConstraints.NONE;
      gbc.anchor = GridBagConstraints.EAST;
      this.add(operatingNotifyBt, gbc);
    }
  }

  public class ContentPanel extends JPanel {
    private LenderChartPanel lenderChartPanel;
    private ReaderTypesChartPanel readerTypesChartPanel;
    private BookAvailabilityChartPanel bookAvailabilityChartPanel;
    private LendingTrendsChartPanel lendingTrendsChartPanel;

    public ContentPanel() {
      setBackground(Style.LIGHT_BLUE_BACKGROUND);
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(5, 5, 5, 5);
      gbc.fill = GridBagConstraints.BOTH;
      // ========== ROW 1 ==========
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 3; // chiếm 3 cột
      quickAccessPanel = new QuickAccessPanel();
      add(quickAccessPanel, gbc);
      // ========== ROW 2 ==========
      // Visitors Chart
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 2;
      lendingTrendsChartPanel = new LendingTrendsChartPanel();
      add(lendingTrendsChartPanel, gbc);

      // Book Allocation
      gbc.gridx = 2;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      readerTypesChartPanel = new ReaderTypesChartPanel();
      add(readerTypesChartPanel, gbc);

      // ========== ROW 3 ==========
      // Book Availability
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 1;
      bookAvailabilityChartPanel = new BookAvailabilityChartPanel();
      add(bookAvailabilityChartPanel, gbc);

      // Book Lending Trends
      gbc.gridx = 1;
      gbc.gridy = 2;
      gbc.gridwidth = 2;
      lenderChartPanel = new LenderChartPanel();
      add(lenderChartPanel, gbc);
      // ========== ROW 4 ===========
      // Visitors Chart
      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.gridwidth = 2;
      //            lendingTrendsChartPanel = new LendingTrendsChartPanel();
      add(new LendingTrendsChartPanel(), gbc);

      // Book Allocation
      gbc.gridx = 2;
      gbc.gridy = 3;
      gbc.gridwidth = 1;
      //            readerTypesChartPanel = new ReaderTypesChartPanel();
      add(new ReaderTypesChartPanel(), gbc);
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

  public void setAvailableBooksCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setAvailableBooksCardButtonListener(listener);
  }

  public void setTotalUsersCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setTotalUsersCardButtonListener(listener);
  }

  public void setOverdueBooksCardButtonListener(ActionListener listener) {
    this.quickAccessPanel.setOverdueBooksCardButtonListener(listener);
  }
}
