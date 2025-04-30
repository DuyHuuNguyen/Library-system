package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class DetailPanel extends JPanel {
  private JLabel lendDateL, dueDateL, copiesL;
  private JTextField lendDateTF, dueDateTF, copiesTF;

  public DetailPanel() {
    Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
    Border titled =
        BorderFactory.createTitledBorder(whiteLine, "Lending Details", 0, 0, Style.FONT_BOLD_20);
    Border padding = BorderFactory.createEmptyBorder(0, 20, 10, 20);
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

    lendDateL = LabelGenerator.createRequireLabel("Lending Date");
    lendDateTF =
        TextFieldBuilder.builder()
            .placeholder("YYYY-MM-DD")
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);

    dueDateL = LabelGenerator.createRequireLabel("Due Date");
    dueDateTF =
        TextFieldBuilder.builder()
            .placeholder("YYYY-MM-DD")
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);

    copiesL = LabelGenerator.createRequireLabel("Copies Lent");
    copiesTF =
        TextFieldBuilder.builder()
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
    add(lendDateL, gbc);
    gbc.gridy = 2;
    add(lendDateTF, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(dueDateL, gbc);
    gbc.gridy = 2;
    add(dueDateTF, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(copiesL, gbc);
    gbc.gridy = 2;
    add(copiesTF, gbc);
  }

  public void cancel() {
    JTextField[] TFs = {copiesTF};
    for (JTextField TF : TFs) {
      TF.setText("");
    }
    lendDateTF.setText("YYYY-MM-DD");
    dueDateTF.setText("YYYY-MM-DD");
  }
}
