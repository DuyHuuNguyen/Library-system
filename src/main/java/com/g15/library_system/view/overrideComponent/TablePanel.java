package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TablePanel extends Panel {
  private String[] columns;
  private Object[][] data;
  private int height;
  private int width;
  private JTable table;
  private DefaultTableModel model;
  private JScrollPane scrollPane;

  public TablePanel(String[] columns, Object[][] data, int width, int height) {
    this.width = width;
    this.height = height;
    this.columns = columns;
    this.data = data;

    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(this.width, this.height));

    model =
        new DefaultTableModel(this.data, this.columns) {
          @Override
          public boolean isCellEditable(int row, int column) {
            return false;
          }
        };

    table = new JTable(model);
    table.setRowHeight(30);
    table.setFont(new Font("SansSerif", Font.PLAIN, 14));
    table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
    table.setShowGrid(false);
    table.setGridColor(new Color(220, 220, 220));

    table.setDefaultRenderer(
        Object.class,
        new DefaultTableCellRenderer() {
          @Override
          public Component getTableCellRendererComponent(
              JTable table,
              Object value,
              boolean isSelected,
              boolean hasFocus,
              int row,
              int column) {
            Component c =
                super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);

            if (!isSelected) {
              c.setBackground(row % 2 == 1 ? new Color(230, 247, 255) : Color.WHITE);
            } else {
              c.setBackground(new Color(184, 207, 229));
            }

            return c;
          }
        });

    table.getColumnModel().getColumn(6).setCellRenderer(new StatusRenderer());

    scrollPane = new JScrollPane(table);
    add(scrollPane, BorderLayout.CENTER);
  }

  static class StatusRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      JLabel label = new JLabel(value.toString(), SwingConstants.CENTER);
      label.setOpaque(true);
      label.setFont(new Font("SansSerif", Font.BOLD, 13));

      switch (value.toString()) {
        case "Available":
          label.setBackground(new Color(200, 255, 220, 120));
          label.setForeground(new Color(0, 150, 90));
          break;
        case "Lended":
          label.setBackground(new Color(255, 230, 150, 120));
          label.setForeground(new Color(204, 120, 0));
          break;
        case "Damaged":
          label.setBackground(new Color(255, 180, 180, 120));
          label.setForeground(new Color(200, 0, 0));
          break;
        default:
          label.setBackground(new Color(255, 255, 255, 100));
          label.setForeground(Color.BLACK);
      }

      return label;
    }
  }

  public static void main(String[] args) {

    String[] columnNames = {
      "Book ID", "Book Title", "Author(s)", "Genre/Category", "Language", "Total Copies", "Status"
    };

    Object[][] data = {
      {
        "ASP-BO-01",
        "The Great Gatsby",
        "F. Scott Fitzgerald",
        "Fiction",
        "English",
        10,
        "Available"
      },
      {
        "ASP-BO-02",
        "To Kill a Mockingbird",
        "George Orwell",
        "Ux-UI Design Book",
        "English",
        5,
        "Available"
      },
      {"ASP-BO-03", "Pirates of the Caribbean", "Jane Austen", "Non-Fiction", "Tamil", 3, "Lended"},
      {"ASP-BO-04", "Pride and Prejudice", "J.D. Salinger", "Romance", "English", 2, "Available"},
      {
        "ASP-BO-05",
        "Sapiens: A Brief History",
        "Stephen Hawking",
        "Ux-UI Design Book",
        "English",
        1,
        "Damaged"
      },
      {"ASP-BO-06", "The Catcher in the Rye", "John Peter", "Fiction", "English", 5, "Damaged"},
      {"ASP-BO-07", "The Alchemist", "Sara Jones", "Non-Fiction", "English", 10, "Lended"},
      {"ASP-BO-08", "A Brief History of Time", "Will Turner", "Science", "English", 20, "Lended"},
      {"ASP-BO-09", "The Diary of a Young", "Dwayne Smith", "Memoir", "English", 30, "Lended"},
      {"ASP-BO-10", "Ux-UI Design Book", "Anne Frank", "Visual Design", "English", 50, "Lended"},

    };
    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Book Table");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(1000, 400);
          frame.setLocationRelativeTo(null);
          frame.setContentPane(new TablePanel(columnNames, data, 1000, 800));
          frame.setVisible(true);
        });
  }
}
