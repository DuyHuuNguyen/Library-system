package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.controller.ReaderController;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.LabelBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class UserPanel extends JPanel {
  private JTextField nameTF, idTF, emailTF;
  private JLabel nameL, idL, emailL;

  private ReaderController readerController =
      ApplicationContextProvider.getBean(ReaderController.class);

  public UserPanel() {
    Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
    Border titled =
        BorderFactory.createTitledBorder(whiteLine, "User Information", 0, 0, Style.FONT_BOLD_20);
    Border padding = BorderFactory.createEmptyBorder(30, 20, 10, 20);
    setBorder(BorderFactory.createCompoundBorder(padding, titled));
    setOpaque(false);
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;

    nameL =
        LabelBuilder.builder()
            .text("Lender Name")
            .font(Style.FONT_PLAIN_13)
            .horizontal(SwingConstants.LEFT);
    nameTF =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .autoSuggest(
                name -> {
                  return readerController.searchNameContains(name);
                },
                selectedName -> {
                  Reader reader = readerController.findByName(selectedName).orElse(null);
                  if (reader != null) {
                    idTF.setText(String.valueOf(reader.getId()));
                    emailTF.setText(reader.getEmail());
                  }
                })
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);
    idL = LabelGenerator.createRequireLabel("Membership ID");
    idTF =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);

    emailL = LabelGenerator.createRequireLabel("Email");
    emailTF =
        TextFieldBuilder.builder()
            .placeholder("www.example.com")
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);

    gbc.gridy = 0;
    gbc.gridwidth = 3;
    gbc.weightx = 1;
    JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
    add(separatorBot, gbc);

    gbc.insets = new Insets(5, 5, 5, 10);
    gbc.gridwidth = 1;
    gbc.weightx = 0;
    gbc.gridy = 1;
    add(nameL, gbc);
    gbc.gridy = 2;
    add(nameTF, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(idL, gbc);
    gbc.gridy = 2;
    add(idTF, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(emailL, gbc);
    gbc.gridy = 2;
    add(emailTF, gbc);
  }

  @Override
  public void addNotify() {
    super.addNotify();
    addAncestorListener(
        new AncestorListener() {
          @Override
          public void ancestorAdded(AncestorEvent event) {
            SwingUtilities.invokeLater(() -> nameTF.requestFocusInWindow());
          }

          @Override
          public void ancestorRemoved(AncestorEvent event) {}

          @Override
          public void ancestorMoved(AncestorEvent event) {}
        });
  }

  public void cancel() {
    SwingUtilities.invokeLater(() -> nameTF.requestFocusInWindow());
    JTextField[] TFs = {nameTF, idTF};
    for (JTextField TF : TFs) {
      TF.setText("");
    }
    emailTF.setText("www.example.com");
  }
}
