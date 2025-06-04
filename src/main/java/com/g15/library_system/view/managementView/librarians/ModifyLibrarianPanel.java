package com.g15.library_system.view.managementView.librarians;

import com.g15.library_system.controller.LibrarianController;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentGenerators.TextFieldGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModifyLibrarianPanel extends JPanel {
    private JTextField txtLibrarianFirstName, txtLibrarianLastName, txtEmail, txtPassword, txtPhoneNum,
            txtAvatarKey, txtDateOfBirth, txtAddress;

    private LibrarianController librarianController = ApplicationContextProvider.getBean(LibrarianController.class);

    private Optional<Librarian> librarian;

    private int width;
    private int height;

    public ModifyLibrarianPanel(int width, int height) {
        this.width = width;
        this.height = height;
        initPanel();
    }
    public void initPanel() {
        this.setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel librarianInfoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        librarianInfoPanel.setBorder(BorderFactory.createTitledBorder("Librarian Information"));

        txtLibrarianFirstName =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));
        txtLibrarianLastName =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));
        txtEmail =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));
        txtPassword =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));
        txtPhoneNum =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));
        txtAvatarKey =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));
        txtDateOfBirth =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));
        txtAddress =
                TextFieldGenerator.createTextField(
                        "",
                        Style.FONT_PLAIN_13,
                        Style.WORD_COLOR_BLACK,
                        Style.BLUE_HEADER_TABLE_AND_BUTTON,
                        new Dimension(200, 25));

        var panelFirstName = createFieldPanel("First Name *", txtLibrarianFirstName);
        panelFirstName.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        var panelLastName = createFieldPanel("Last Name", txtLibrarianLastName);
        panelLastName.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        var panelEmail = createFieldPanel("Email *", txtEmail);
        panelEmail.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        var panelPassword = createFieldPanel("Password *", txtPassword);
        panelPassword.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        var panelQPhoneNum = createFieldPanel("Phone Number", txtPhoneNum);
        panelQPhoneNum.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        var panelAvartaKey = createFieldPanel("Avatar Key", txtAvatarKey);
        panelAvartaKey.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        var panelDateOfBirth = createFieldPanel("Date of birth", txtDateOfBirth);
        panelDateOfBirth.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        var panelAddress = createFieldPanel("Address", txtAddress);
        panelAddress.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        librarianInfoPanel.add(panelFirstName);
        librarianInfoPanel.add(panelLastName);
        librarianInfoPanel.add(panelEmail);
        librarianInfoPanel.add(panelPassword);
        librarianInfoPanel.add(panelQPhoneNum);
        librarianInfoPanel.add(panelAvartaKey);
        librarianInfoPanel.add(panelDateOfBirth);
        librarianInfoPanel.add(panelAddress);
        librarianInfoPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnModify = CustomButtonBuilder.builder().text("Save");
        btnModify.addActionListener(
                e -> {
                    var modifiedLibrarian = this.getModifiedLibrarian();
                    this.librarianController.updateLibrarian(modifiedLibrarian.get());
                    this.clearDataInPanel();

                    ToastNotification notification =
                            new ToastNotification(
                                    JOptionPane.getFrameForComponent(this),
                                    ToastNotification.Type.INFO,
                                    ToastNotification.Location.TOP_CENTER,
                                    "Save librarian successful");
                    notification.showNotification();
                });

        JButton btnCancel = CustomButtonBuilder.builder().text("Cancel");
        btnCancel.addActionListener(
                e -> {
                    this.clearDataInPanel();
                    ToastNotification notification =
                            new ToastNotification(
                                    JOptionPane.getFrameForComponent(this),
                                    ToastNotification.Type.INFO,
                                    ToastNotification.Location.TOP_CENTER,
                                    "Cancel successful");
                    notification.showNotification();
                });

        buttonPanel.add(btnModify);
        buttonPanel.add(btnCancel);
        buttonPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

        panel.add(librarianInfoPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.setBackground(Style.LIGHT_WHITE_BACKGROUND);
        add(panel);
        setVisible(true);
    }

    private JPanel createFieldPanel(String label, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        JLabel jLabel = new JLabel(label);
        jLabel.setPreferredSize(new Dimension(90, 22));
        panel.add(jLabel, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }
    public void clearDataInPanel() {
        txtLibrarianFirstName.setText("");
        txtLibrarianLastName.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtPhoneNum.setText("");
        txtAvatarKey.setText("");
        txtDateOfBirth.setText("");
        txtAddress.setText("");
    }
    public void addData(Optional<Librarian> librarianModify) {
        txtLibrarianFirstName.setText(librarianModify.get().getFirstName());
        txtLibrarianLastName.setText(librarianModify.get().getLastName());
        txtEmail.setText(librarianModify.get().getEmail());
        txtPassword.setText(librarianModify.get().getPassword());
        txtPhoneNum.setText(librarianModify.get().getPhoneNumber());
        txtAvatarKey.setText(librarianModify.get().getAvatarKey());
        txtDateOfBirth.setText(String.valueOf(librarianModify.get().getDateOfBirth()));
        txtAddress.setText(librarianModify.get().getAddress());
    }
    public Optional<Librarian> getModifiedLibrarian() {
        if(librarian == null || librarian.isEmpty()) {
            var modifyLibrarian =
                    Librarian.builder()
                            .firstName(txtLibrarianFirstName.getText())
                            .lastName(txtLibrarianLastName.getText())
                            .email(txtEmail.getText())
                            .password(txtPassword.getText())
                            .phoneNumber(txtPhoneNum.getText())
                            .avatarKey(txtAvatarKey.getText())
                            .dateOfBirth(Long.valueOf(txtDateOfBirth.getText()))
                            .address(txtAddress.getText())
                            .build();
            log.info("modify librarian {}", modifyLibrarian.toString());
            return this.librarian = Optional.of(modifyLibrarian);
        }
        return this.librarian;
    }

}