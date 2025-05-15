package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.LabelBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

@Deprecated
public class OptionPanel extends JPanel {
  private JLabel lendingStatusL, finePolicyL;
  private JComboBox<String> statusCB;
  private JTextField fineTF;

  public OptionPanel() {
    Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
    Border titled =
        BorderFactory.createTitledBorder(whiteLine, "Additional Options", 0, 0, Style.FONT_BOLD_20);
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

    lendingStatusL = LabelGenerator.createRequireLabel("Lending Status");
    statusCB = new JComboBox<>();

    finePolicyL =
        LabelBuilder.builder()
            .text("OverdueFee Policy")
            .font(Style.FONT_PLAIN_13)
            .horizontal(SwingConstants.LEFT);
    fineTF =
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(new Dimension(300, 25));

    gbc.gridy = 0;
    gbc.gridwidth = 4;
    gbc.weightx = 1;
    JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
    add(separatorBot, gbc);

    gbc.insets = new Insets(5, 5, 5, 100);
    gbc.gridwidth = 1;
    gbc.weightx = 0;

    gbc.gridx++;
    gbc.gridy = 1;
    add(lendingStatusL, gbc);
    gbc.gridy = 2;
    add(statusCB, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(finePolicyL, gbc);
    gbc.gridy = 2;
    add(fineTF, gbc);
  }

  public void cancel() {
    JComboBox[] CBs = {statusCB};
    for (JComboBox CB : CBs) {
      if (CB.getItemCount() > 0) CB.setSelectedIndex(0);
    }
    fineTF.setText("");
  }
}
