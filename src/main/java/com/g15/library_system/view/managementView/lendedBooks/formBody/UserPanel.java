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

    nameL =
        LabelBuilder.builder()
            .text("Lender Name")
            .font(Style.FONT_PLAIN_13)
            .horizontal(SwingConstants.LEFT);
    nameTF =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .popupMenu(
                name -> {
                  return readerController.searchNameContains(name);
                },
                selectedName -> {
                  Reader reader = readerController.findByName(selectedName).orElse(null);
                  if (reader != null) {
                    idTF.setText(String.valueOf(reader.getId()));
                    emailTF.setText(reader.getEmail());
                    KeyboardFocusManager.getCurrentKeyboardFocusManager()
                        .focusNextComponent(emailTF);
                  }
                });

    idL = LabelGenerator.createRequireLabel("Membership ID");
    idTF =
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(new Dimension(300, 25));

    emailL = LabelGenerator.createRequireLabel("Email");
    emailTF =
        TextFieldBuilder.builder()
            .placeholder("www.example.com")
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25));

    gbc.gridy++;
    add(nameL, gbc);
    gbc.gridy++;
    add(nameTF, gbc);

    gbc.gridy++;
    add(idL, gbc);
    gbc.gridy++;
    add(idTF, gbc);

    gbc.gridy++;
    add(emailL, gbc);
    gbc.gridy++;
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
