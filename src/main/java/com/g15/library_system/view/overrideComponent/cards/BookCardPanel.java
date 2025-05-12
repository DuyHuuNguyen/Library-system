package com.g15.library_system.view.overrideComponent.cards;

import com.g15.library_system.entity.Book;
import java.awt.*;
import javax.swing.*;
import lombok.Getter;

public class BookCardPanel extends JPanel {
  @Getter private final Book book;
  private final JTextField quantityTF;

  public BookCardPanel(Book book, Runnable onRemove) {
    this.book = book;
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createLineBorder(Color.GRAY));
    setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

    JLabel info =
        new JLabel(
            "<html><b>"
                + book.getTitle()
                + "</b><br/>Author: "
                + book.getAuthor()
                + "<br/>Genre: "
                + book.getGenreType()
                + "</html>");
    add(info, BorderLayout.CENTER);

    JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton minusBtn = new JButton("-");
    JButton plusBtn = new JButton("+");
    quantityTF = new JTextField("1", 3);
    quantityTF.setHorizontalAlignment(JTextField.CENTER);
    JButton removeBtn = new JButton("Remove");

    minusBtn.addActionListener(
        e -> {
          int current = Integer.parseInt(quantityTF.getText());
          if (current > 1) quantityTF.setText(String.valueOf(current - 1));
        });

    plusBtn.addActionListener(
        e -> {
          int current = Integer.parseInt(quantityTF.getText());
          if (current < book.getCurrentQuantity()) quantityTF.setText(String.valueOf(current + 1));
        });

    removeBtn.addActionListener(
        e -> {
          Container parent = getParent();
          if (parent != null) {
            parent.remove(this);
            parent.revalidate();
            parent.repaint();
            onRemove.run();
          }
        });

    rightPanel.add(minusBtn);
    rightPanel.add(quantityTF);
    rightPanel.add(plusBtn);
    rightPanel.add(removeBtn);
    add(rightPanel, BorderLayout.EAST);
  }

  public int getQuantity() {
    try {
      return Integer.parseInt(quantityTF.getText());
    } catch (NumberFormatException e) {
      return 1;
    }
  }
}
