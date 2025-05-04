package com.g15.library_system.view.swingComponentBuilders;

import com.g15.library_system.view.Style;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldBuilder extends JTextField {
  private Color borderColor;
  private static final int LIMIT_POPUPMENU = 5;

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
   * to text changes. When typing stops, it calls the provided {@code suggestionProvider} function
   * to retrieve a list of matching suggestions. These suggestions are displayed in a {@link
   * JPopupMenu} beneath the text field.
   *
   * <p>When a user selects an item from the popup, the text field is updated to reflect the
   * selected value, and the optional {@code onSelect} consumer is triggered.
   *
   * <p>This method is safe to use even if {@code onSelect} is {@code null}.
   *
   * @param suggestionProvider A function that takes the current text input and returns a list of
   *     suggestion strings. Should handle partial matches.
   * @param onSelect (Optional) A consumer function that receives the selected string when the user
   *     chooses a suggestion from the list. Can be {@code null}.
   * @return This {@code TextFieldBuilder} instance for method chaining.
   */
  public TextFieldBuilder autoSuggest(
      Function<String, List<String>> suggestionProvider, Consumer<String> onSelect) {
    JPopupMenu suggestionsPopup = new JPopupMenu();
    suggestionsPopup.setFocusable(false);

    Timer debounceTimer = getTimer(suggestionProvider, onSelect, suggestionsPopup);

    this.getDocument()
        .addDocumentListener(
            new DocumentListener() {
              public void insertUpdate(DocumentEvent e) {
                debounceTimer.restart();
              }

              public void removeUpdate(DocumentEvent e) {
                debounceTimer.restart();
                ;
              }

              public void changedUpdate(DocumentEvent e) {}
            });

    this.addFocusListener(
        new FocusAdapter() {
          public void focusLost(FocusEvent e) {
            suggestionsPopup.setVisible(false);
          }
        });

    return this;
  }

  private Timer getTimer(
      Function<String, List<String>> suggestionProvider,
      Consumer<String> onSelect,
      JPopupMenu suggestionsPopup) {
    Timer debounceTimer =
        new Timer(
            300,
            e -> {
              String input = getText().trim();
              suggestionsPopup.setVisible(false);
              suggestionsPopup.removeAll();

              if (input.isEmpty()) return;

              List<String> suggestions = suggestionProvider.apply(input);
              if (suggestions == null || suggestions.isEmpty()) return;
              for (int i = 0; i < suggestions.size() && i < LIMIT_POPUPMENU; i++) {
                JMenuItem item = getMenuItem(onSelect, suggestions.get(i), suggestionsPopup);
                suggestionsPopup.add(item);
              }

              suggestionsPopup.setPopupSize(
                  getWidth(), Math.min(suggestions.size(), LIMIT_POPUPMENU) * 25);
              suggestionsPopup.show(TextFieldBuilder.this, 0, getHeight());
            });

    debounceTimer.setRepeats(false);
    return debounceTimer;
  }

  private JMenuItem getMenuItem(
      Consumer<String> onSelect, String suggestion, JPopupMenu suggestionsPopup) {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    panel.setOpaque(false);

    JLabel label = new JLabel(suggestion);
    label.setFont(Style.FONT_PLAIN_13);
    panel.add(label);
    JMenuItem item = new JMenuItem();
    item.setLayout(new BorderLayout());
    item.add(panel, BorderLayout.CENTER);
    item.setPreferredSize(new Dimension(getWidth(), 25));
    item.addActionListener(
        event -> {
          setText(suggestion);
          suggestionsPopup.setVisible(false);
          if (onSelect != null) onSelect.accept(suggestion);
        });
    return item;
  }
}
