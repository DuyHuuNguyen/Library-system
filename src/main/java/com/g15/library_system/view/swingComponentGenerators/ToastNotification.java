package com.g15.library_system.view.swingComponentGenerators;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedPanel;

import javax.swing.*;
import java.awt.*;

public class ToastNotification {
    public static final String SUCCESS = "success";
    public static final String REMOVED = "removed";
    public static final String ERROR = "error";
    public static final String WARNING = "warning";
    public static final String INFORMATION = "information";
    private static Color borderColor = Style.BUTTON_COLOR;

    public static void showToast(String message, String messageType, int duration, int x, int y) {
        JWindow window = new JWindow();
        window.setLayout(new BorderLayout());
        window.setBackground(new Color(0, 0, 0, 0));

        // Icon Panel
        JPanel iconPanel = createIconPanel(messageType);
        iconPanel.setOpaque(false);
        iconPanel.setMaximumSize(new Dimension(40, 40));
        iconPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Message Label
        JLabel messageLabel = LabelGenerator.createLabel(
                "<html>" + message + "</html>", Style.FONT_BOLD_18, Color.BLACK, SwingConstants.LEFT);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        messageLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // Đóng gói label vào panel để set size mềm mại hơn
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setOpaque(false);
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Close Button Panel
        JButton closeButton = ButtonGenerator.createJButton("x", Style.FONT_BOLD_20, Color.GRAY, Color.WHITE);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.addActionListener(e -> {
            window.setVisible(false);
            window.dispose();
        });

        JPanel closePanel = new JPanel();
        closePanel.setOpaque(false);
        closePanel.setLayout(new BoxLayout(closePanel, BoxLayout.Y_AXIS));
        closePanel.add(Box.createVerticalGlue());
        closePanel.add(closeButton);
        closePanel.add(Box.createVerticalGlue());
        closePanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Add các thành phần vào main panel
        RoundedPanel mainPanel = new RoundedPanel(20, Color.WHITE, borderColor);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
        mainPanel.add(iconPanel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(messagePanel);
        mainPanel.add(Box.createHorizontalStrut(10));
        mainPanel.add(closePanel);
        window.add(mainPanel);
        window.pack();

        int fixedWidth = 350;
        int dynamicHeight = messageLabel.getPreferredSize().height + 40;
        window.setSize(fixedWidth, dynamicHeight);

        // Tự động đặt xuống góc phải nếu x, y là -1
        if (x == -1 && y == -1) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.width - window.getWidth() - 10;
            y = screenSize.height - window.getHeight() - 50;
        }

        if (x == -1) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.width - window.getWidth() - 10;
            y = screenSize.height - window.getHeight() - 50 - y;
        }

        window.setLocation(x, y);
        window.setVisible(true);
        window.setOpacity(1.0f);

        new Timer(duration, e -> {
            window.setVisible(false);
            window.dispose();
        }).start();
    }

    public static JPanel createIconPanel(String messageType){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(70, 70));
        JLabel iconLabel = LabelGenerator.createLabel("",null,null,0);

        switch (messageType){
            case SUCCESS:
                panel.setBackground(Style.LIGHT_GREEN_2);
                borderColor = Style.CONFIRM_BUTTON_COLOR_GREEN;
                LabelGenerator.setIcon(iconLabel,"src/main/java/view/LibraryUI/icons/tickIcon.png",40,40);
                break;
            case REMOVED:
                panel.setBackground(Style.LIGHT_BLUE);
                borderColor = Style.CONFIRM_BUTTON_COLOR_GREEN;
                LabelGenerator.setIcon(iconLabel,"src/main/java/view/LibraryUI/icons/removeIconTrashBin.png",40,40);
                break;
            case ERROR:
                panel.setBackground(Style.LIGHT_RED_2);
                borderColor = Style.CANCEL_BUTTON_COLOR_RED;
                LabelGenerator.setIcon(iconLabel,"src/main/java/view/LibraryUI/icons/error_icon.png",40,40);
                break;
            case WARNING:
                panel.setBackground(Style.LIGHT_YELLOW_2);
                borderColor = Style.YELLOW_DARKER;
                LabelGenerator.setIcon(iconLabel,"src/main/java/view/LibraryUI/icons/warningIcon.png",40,40);
                break;
            case INFORMATION:
                panel.setBackground(Style.LIGHT_BLUE);
                borderColor = Style.BUTTON_COLOR;
                LabelGenerator.setIcon(iconLabel,"src/main/java/view/LibraryUI/icons/informationIcon.png",40,40);
                break;
            default:
                System.out.println("Invalid message Type!");
                break;
        }

        panel.add(iconLabel, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
            showToast("A new book added successfully!",ToastNotification.WARNING,9000,-1,20);

    }
}
