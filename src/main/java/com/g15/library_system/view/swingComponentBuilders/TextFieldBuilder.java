package com.g15.library_system.view.swingComponentBuilders;

import com.g15.library_system.view.Style;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldBuilder extends JTextField {
  private Color borderColor;
  private static final int LIMIT_POPUPMENU = 8;
  private boolean popupEnabled = true;

  public static TextFieldBuilder builder() {
    return new TextFieldBuilder();
  }

  public TextFieldBuilder text(String text) {
    this.setText(text);
    return this;
  }

  public TextFieldBuilder font(Font font) {
    this.setFont(font);
    return this;
  }

  public TextFieldBuilder textColor(Color color) {
    this.setForeground(color);
    return this;
  }

  public TextFieldBuilder preferredSize(Dimension dimension) {
    this.setPreferredSize(dimension);
    return this;
  }

  public TextFieldBuilder editable(boolean editable) {
    this.setEditable(editable);
    return this;
  }

  public TextFieldBuilder borderColor(Color borderColor) {
    this.setBorder(BorderFactory.createLineBorder(borderColor, 1));
    this.borderColor = borderColor;
    return this;
  }

  public TextFieldBuilder placeholder(String placeholderText) {
    text(placeholderText).textColor(Color.GRAY);
    this.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            setForeground(Color.BLACK);
            if (getText().equals(placeholderText)) {
              setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (getText().isEmpty()) {
              setForeground(Color.GRAY);
              setText(placeholderText);
            }
          }
        });
    return this;
  }

  public TextFieldBuilder border(Border border) {
    this.setBorder(border);
    return this;
  }

  public TextFieldBuilder withFocusBorderEffect(Color borderColor) {
    borderColor(borderColor);
    this.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            setBorder(BorderFactory.createLineBorder(borderColor, 3));
          }

          @Override
          public void focusLost(FocusEvent e) {
            setBorder(BorderFactory.createLineBorder(borderColor, 1));
          }
        });
    return this;
  }

  /**
   * Enables auto-suggestion on this text field using a dynamic popup menu.
   *
   * <p>This method attaches a {@link DocumentListener} with a debounced delay (300ms) that listens
   * to text changes. When typing stops, it calls the provided {@code contextProvider} function to
   * retrieve a list of matching suggestions. These suggestions are displayed in a {@link
   * JPopupMenu} beneath the text field.
   *
   * <p>When a user selects an item from the popup, the text field is updated to reflect the
   * selected value, and the optional {@code onSelect} consumer is triggered.
   *
   * <p>This method is safe to use even if {@code onSelect} is {@code null}.
   *
   * @param contextProvider A function that takes the current text input and returns a list of
   *     suggestion strings. Should handle partial matches.
   * @param onSelect (Optional) A consumer function that receives the selected string when the user
   *     chooses a suggestion from the list. Can be {@code null}.
   * @return This {@code TextFieldBuilder} instance for method chaining.
   */
  public TextFieldBuilder popupMenu(
      Function<String, List<String>> contextProvider, Consumer<String> onSelect) {
    JPopupMenu popupMenu = new JPopupMenu();
    popupMenu.setFocusable(false);

    List<JMenuItem> menuItems = new ArrayList<>();

    Timer debounceTimer =
        new Timer(
            300,
            e -> {
              if (!popupEnabled) return;
              String input = getText().trim();
              popupMenu.setVisible(false);
              popupMenu.removeAll();
              menuItems.clear();

              if (input.isEmpty()) return;

              List<String> suggestions = contextProvider.apply(input);
              if (suggestions == null || suggestions.isEmpty()) return;

              for (int i = 0; i < suggestions.size() && i < LIMIT_POPUPMENU; i++) {
                String suggestion = suggestions.get(i);
                JMenuItem item = buildMenuItem(suggestion, onSelect, popupMenu);
                popupMenu.add(item);
                menuItems.add(item);
              }

              popupMenu.setPopupSize(
                  getWidth(), Math.min(suggestions.size(), LIMIT_POPUPMENU) * 30);
              popupMenu.show(TextFieldBuilder.this, 0, getHeight());

              setupKeyNavigation(menuItems, popupMenu);
            });
    debounceTimer.setRepeats(false);

    this.getDocument()
        .addDocumentListener(
            new DocumentListener() {
              public void insertUpdate(DocumentEvent e) {
                popupEnabled = true;
                debounceTimer.restart();
              }

              public void removeUpdate(DocumentEvent e) {
                popupEnabled = true;
                debounceTimer.restart();
              }

              public void changedUpdate(DocumentEvent e) {}
            });

    this.addFocusListener(
        new FocusAdapter() {
          public void focusLost(FocusEvent e) {
            popupMenu.setVisible(false);
          }
        });

    return this;
  }

  private JMenuItem buildMenuItem(
      String suggestion, Consumer<String> onSelect, JPopupMenu popupMenu) {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    panel.setOpaque(false);

    JLabel label = LabelBuilder.builder().text(suggestion).font(Style.FONT_PLAIN_13);
    panel.add(label);
    JMenuItem item = new JMenuItem();
    item.setLayout(new BorderLayout());
    item.add(panel, BorderLayout.CENTER);
    item.setPreferredSize(new Dimension(getWidth(), 25));
    item.addActionListener(
        event -> {
          setText(suggestion);
          popupMenu.setVisible(false);
          if (onSelect != null) onSelect.accept(suggestion);
          popupEnabled = false;
        });
    return item;
  }

  private void setupKeyNavigation(List<JMenuItem> menuItems, JPopupMenu popupMenu) {
    KeyAdapter keyAdapter =
        new KeyAdapter() {
          int selectedIndex = -1;

          @Override
          public void keyPressed(KeyEvent e) {
            if (!popupMenu.isVisible()) return;

            int key = e.getKeyCode();
            if (key == KeyEvent.VK_DOWN) {
              selectedIndex = (selectedIndex + 1) % menuItems.size();
              highlight(selectedIndex);
            } else if (key == KeyEvent.VK_UP) {
              selectedIndex = (selectedIndex - 1 + menuItems.size()) % menuItems.size();
              highlight(selectedIndex);
            } else if (key == KeyEvent.VK_ENTER && selectedIndex >= 0) {
              menuItems.get(selectedIndex).doClick();
            }
          }

          private void highlight(int index) {
            for (int i = 0; i < menuItems.size(); i++) {
              menuItems.get(i).setArmed(i == index);
            }
          }
        };

    for (KeyListener l : this.getKeyListeners()) {
      if (l instanceof KeyAdapter) removeKeyListener(l);
    }
    this.addKeyListener(keyAdapter);
  }
}
