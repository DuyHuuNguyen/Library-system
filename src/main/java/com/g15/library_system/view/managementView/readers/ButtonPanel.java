package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.controller.ReaderController;
import com.g15.library_system.data.ReaderData;
import com.g15.library_system.entity.LibraryCard;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.StudentReaderType;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.util.DateUtil;
import com.g15.library_system.util.NameUtil;
import com.g15.library_system.util.PathUtil;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ButtonPanel extends JPanel {
  private JButton saveBt;
  private JButton editBt;
  private JButton removeBt;
  private JButton addBt;
  private JButton cancelBt;
  private ReaderController readerController =
      ApplicationContextProvider.getBean(ReaderController.class);

  private ButtonPanelMode mode;

  public ButtonPanel(ReaderPanel readerPn) {
    setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 30));
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
    setPreferredSize(new Dimension(this.getWidth(), 70));

    // Save button
    saveBt =
        CustomButtonBuilder.builder()
            .text("OK")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30))
            .icon("/icons/save.png", 12);

    saveBt.addActionListener(
        e -> {
          FormPanel formPn = readerPn.getContentPn().getShowInforPn().getFormPn();
          System.out.println(Long.valueOf(formPn.getMemberIdField().getText()));
          if (formPn.validateForm()) {
            Reader readerNeedRm =
                ReaderData.getInstance().findId(Long.valueOf(formPn.getMemberIdField().getText()));

            List<Transaction> transactionsList =
                readerNeedRm.getLibraryCard().getBorrowTransactions();

            if (readerNeedRm != null) {
              ReaderData.getInstance().remove(readerNeedRm);
            }

            // -- Lưu dữ liệu đã nhập vào database ở đây
            Reader reader =
                Reader.builder()
                    .id(Long.valueOf(formPn.getMemberIdField().getText()))
                    .email(formPn.getEmailField().getText())
                    .lastName(NameUtil.getLastName(formPn.getFullNameField().getText()))
                    .firstName(NameUtil.getFirstName(formPn.getFullNameField().getText()))
                    .address(formPn.getAddressField().getText())
                    .dateOfBirth(
                        DateUtil.convertToEpochMilli(
                            LocalDate.parse(
                                formPn.getBirthDateField().getText(),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                    .createdAt(
                        DateUtil.convertToEpochMilli(
                            LocalDate.parse(
                                formPn.getMembershipDateField().getText(),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                    .avatarKey(
                        PathUtil.convertFullPathToRelativePath(
                            readerPn.getContentPn().getShowInforPn().getAvtPn().getImageUrl()))
                    //                        .avatarKey("/images/Emma.jpg")
                    .phoneNumber(formPn.getPhoneField().getText())
                    .isSubscribe(true)
                    .readerType(
                        StudentReaderType.builder()
                            .faculty("Information Technology")
                            .enrollmentYear(2021)
                            .studentID("IT2021001")
                            .build())
                    .build();

            LibraryCard libCard =
                LibraryCard.builder()
                    .id(reader.getId())
                    .createdAt(System.currentTimeMillis())
                    .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000) // 2 months
                    .libraryCardStatus(LibraryCardStatus.ACTIVE)
                    .build();

            libCard.addBorrowTransactions(transactionsList);
            reader.addLibraryCard(libCard);
            System.out.println(ReaderData.getInstance().getAvailableIds().toString());
            ReaderData.getInstance().add(reader);
            System.out.println(ReaderData.getInstance().getAvailableIds().toString());
            //            System.out.println(
            //                PathUtil.convertFullPathToRelativePath(
            //                    readerPn.contentPn.showInforPn.avtPn.getImageUrl()));
            readerPn.getContentPn().getTablePn().refreshTable();
            readerPn.getContentPn().getShowInforPn().getBtnPn().setMode(ButtonPanelMode.VIEW);
            JOptionPane.showMessageDialog(this, "Saved!");

            readerPn
                .getToolPn()
                .enableTextFields(readerPn.getContentPn().getShowInforPn().getFormPn(), false);
          }
        });

    // Edit button
    editBt =
        CustomButtonBuilder.builder()
            .text("Edit")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30))
            .icon("/icons/edit.png", 12);
    editBt.addActionListener(
        e -> {
          // Add functionality here
          readerPn
              .getToolPn()
              .enableTextFields(readerPn.getContentPn().getShowInforPn().getFormPn(), true);
          readerPn.getContentPn().getShowInforPn().getBtnPn().setMode(ButtonPanelMode.EDIT);
        });

    // Remove button
    removeBt =
        CustomButtonBuilder.builder()
            .text("Remove")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(220, 68, 84))
            .hoverColor(new Color(220, 68, 84).darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(120, 30))
            .icon("/icons/deleteIcon.png", 12);
    removeBt.addActionListener(
        e -> {
          FormPanel formPn = readerPn.getContentPn().getShowInforPn().getFormPn();
          Reader readerNeedRm =
              ReaderData.getInstance().findId(Long.valueOf(formPn.getMemberIdField().getText()));
          ReaderData.getInstance().remove(readerNeedRm);
          readerPn.getContentPn().getTablePn().refreshTable();

          new ToastNotification(
                  JOptionPane.getFrameForComponent(this),
                  ToastNotification.Type.SUCCESS,
                  ToastNotification.Location.BOTTOM_RIGHT,
                  "Remove successfully!!")
              .showNotification();
          readerPn.getToolPn().enableTextFields(formPn, false);
        });

    // Add Button
    addBt =
        CustomButtonBuilder.builder()
            .text("Add")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30));

    add(addBt);

    addBt.addActionListener(
        e -> {
          FormPanel formPn = readerPn.getContentPn().getShowInforPn().getFormPn();
          if (formPn.validateForm()) {
            // -- Lưu dữ liệu đã nhập vào database ở đây
            Reader reader =
                Reader.builder()
                    .id(null)
                    .email(formPn.getEmailField().getText())
                    .lastName(NameUtil.getLastName(formPn.getFullNameField().getText()))
                    .firstName(NameUtil.getFirstName(formPn.getFullNameField().getText()))
                    .address(formPn.getAddressField().getText())
                    .dateOfBirth(
                        DateUtil.convertToEpochMilli(
                            LocalDate.parse(
                                formPn.getBirthDateField().getText(),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                    .createdAt(
                        DateUtil.convertToEpochMilli(
                            LocalDate.parse(
                                formPn.getMembershipDateField().getText(),
                                DateTimeFormatter.ofPattern("dd-MM-yyyy"))))
                    .avatarKey(
                        PathUtil.convertFullPathToRelativePath(
                            readerPn.getContentPn().getShowInforPn().getAvtPn().getImageUrl()))
                    //                        .avatarKey("/images/Emma.jpg")
                    .phoneNumber(formPn.getPhoneField().getText())
                    .isSubscribe(true)
                    .readerType(
                        StudentReaderType.builder()
                            .faculty("Information Technology")
                            .enrollmentYear(2021)
                            .studentID("IT2021001")
                            .build())
                    .build();

            LibraryCard libCard =
                LibraryCard.builder()
                    .id(reader.getId())
                    .createdAt(System.currentTimeMillis())
                    .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000) // 2 months
                    .libraryCardStatus(LibraryCardStatus.ACTIVE)
                    .build();

            reader.addLibraryCard(libCard);

            ReaderData.getInstance().add(reader);
            System.out.println(
                PathUtil.convertFullPathToRelativePath(
                    readerPn.getContentPn().getShowInforPn().getAvtPn().getImageUrl()));
            readerPn.getContentPn().getTablePn().refreshTable();
            // -------------------------------------------
            new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.SUCCESS,
                    ToastNotification.Location.BOTTOM_RIGHT,
                    "Add successfully!!")
                .showNotification();

            // Gửi mail xác nhận đăng kí thành công qua gmail của reader
            // ---------------------------------------------------------

            readerController.sendEmailAddMemberSuccessful(reader.getEmail());

            // ---------------------------------------------------------

            readerPn.getToolPn().enableTextFields(formPn, false);
            readerPn
                .getContentPn()
                .getShowInforPn()
                .getFormPn()
                .getMemberIdField()
                .setText(reader.getId() + "");
            this.setMode(ButtonPanelMode.VIEW);
          }
        });

    // Cancel button
    cancelBt =
        CustomButtonBuilder.builder()
            .text("Cancel")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(220, 68, 84))
            .hoverColor(new Color(220, 68, 84).darker())
            .radius(12)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(120, 30));
    cancelBt.setVisible(false);
    add(cancelBt);
  }

  public void setMode(ButtonPanelMode mode) {
    this.mode = mode;
    updateButtons();
    //    setEditable(mode == InfoPanelMode.ADD);
  }

  private void updateButtons() {
    removeAllButtons();

    if (mode == ButtonPanelMode.VIEW) {
      add(editBt);
      add(removeBt);
    } else if (mode == ButtonPanelMode.ADD) {
      add(addBt);
      add(cancelBt);
    } else if (mode == ButtonPanelMode.EDIT) {
      add(saveBt);
    }

    revalidate();
    repaint();
  }

  private void removeAllButtons() {
    remove(saveBt);
    remove(editBt);
    remove(removeBt);
    remove(addBt);
    remove(cancelBt);
  }
}
