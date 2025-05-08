package com.g15.library_system;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.g15.library_system.entity.*;
import com.g15.library_system.enums.*;
import com.g15.library_system.view.LoginFrame;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LibrarySystemApplication {
  public static void main(String[] args) {
    // improve Swing UI
    FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("raven.themes");
    UIManager.put(
        "defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13)); // set default font
    UIManager.put("Separator.foreground", Color.WHITE);
    FlatMacLightLaf.setup(); // light mode
    //    FlatMacDarkLaf.setup();//dark mode (dang bug)

    SpringApplication app = new SpringApplication(LibrarySystemApplication.class);
    app.setHeadless(false);
    app.run(args);
    var login = new LoginFrame();
    login.setVisible(true);

    var c1 =
        Transaction.builder()
            .id(1L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(
                    Book.builder()
                        .id(1L)
                        .title("Harry Potter and the Sorcerer's Stone")
                        .author("J.K. Rowling")
                        .build()))
            .librarian(User.builder().id(101L).firstName("Alice").lastName("Nguyen").build())
            .expectedReturnAt(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(0L)
            .transactionType(TransactionType.BORROW)
            .build();

    var c2 =
        Transaction.builder()
            .id(2L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(List.of(Book.builder().id(2L).title("1984").author("George Orwell").build()))
            .librarian(User.builder().id(102L).firstName("Bob").lastName("Tran").build())
            .expectedReturnAt(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000L)
            .transactionType(TransactionType.RETURN)
            .build();

    var c3 =
        Transaction.builder()
            .id(3L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(
                    Book.builder()
                        .id(3L)
                        .title("To Kill a Mockingbird")
                        .author("Harper Lee")
                        .build(),
                    Book.builder()
                        .id(4L)
                        .title("The Great Gatsby")
                        .author("F. Scott Fitzgerald")
                        .build()))
            .librarian(User.builder().id(103L).firstName("Charlie").lastName("Le").build())
            .expectedReturnAt(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(0L)
            .transactionType(TransactionType.BORROW)
            .build();

    var c4 =
        Transaction.builder()
            .id(4L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(Book.builder().id(5L).title("The Hobbit").author("J.R.R. Tolkien").build()))
            .librarian(User.builder().id(104L).firstName("David").lastName("Pham").build())
            .expectedReturnAt(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(System.currentTimeMillis() + 8 * 24 * 60 * 60 * 1000L)
            .transactionType(TransactionType.RETURN)
            .build();

    var c5 =
        Transaction.builder()
            .id(5L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(Book.builder().id(6L).title("War and Peace").author("Leo Tolstoy").build()))
            .librarian(User.builder().id(105L).firstName("Emma").lastName("Vu").build())
            .expectedReturnAt(System.currentTimeMillis() + 12 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(0L)
            .transactionType(TransactionType.BORROW)
            .build();

    // Tạo thêm 15 transaction nữa... ＞﹏＜
    var c6 =
        Transaction.builder()
            .id(6L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(
                    Book.builder()
                        .id(7L)
                        .title("One Hundred Years of Solitude")
                        .author("Gabriel García Márquez")
                        .build()))
            .librarian(User.builder().id(106L).firstName("Sophia").lastName("Hoang").build())
            .expectedReturnAt(System.currentTimeMillis() + 15 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000L)
            .transactionType(TransactionType.RETURN)
            .build();

    var c7 =
        Transaction.builder()
            .id(7L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(
                    Book.builder()
                        .id(8L)
                        .title("The Adventures of Huckleberry Finn")
                        .author("Mark Twain")
                        .build()))
            .librarian(User.builder().id(107L).firstName("James").lastName("Ly").build())
            .expectedReturnAt(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(0L)
            .transactionType(TransactionType.BORROW)
            .build();

    var c8 =
        Transaction.builder()
            .id(8L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(
                    Book.builder()
                        .id(9L)
                        .title("Pride and Prejudice")
                        .author("Jane Austen")
                        .build()))
            .librarian(User.builder().id(108L).firstName("Isabella").lastName("Dang").build())
            .expectedReturnAt(System.currentTimeMillis() + 20 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(0L)
            .transactionType(TransactionType.BORROW)
            .build();

    var c9 =
        Transaction.builder()
            .id(9L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(List.of(Book.builder().id(10L).title("The Odyssey").author("Homer").build()))
            .librarian(User.builder().id(109L).firstName("Ethan").lastName("Vo").build())
            .expectedReturnAt(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(System.currentTimeMillis() + 6 * 24 * 60 * 60 * 1000L)
            .transactionType(TransactionType.RETURN)
            .build();

    var c10 =
        Transaction.builder()
            .id(10L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .books(
                List.of(
                    Book.builder()
                        .id(11L)
                        .title("Great Expectations")
                        .author("Charles Dickens")
                        .build()))
            .librarian(User.builder().id(110L).firstName("Mia").lastName("Nguyen").build())
            .expectedReturnAt(System.currentTimeMillis() + 12 * 24 * 60 * 60 * 1000L)
            .actualReturnAt(0L)
            .transactionType(TransactionType.BORROW)
            .build();

    var lc1 =
        LibraryCard.builder()
            .id(1L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(1L)
                    .firstName("Nguyen")
                    .lastName("Huu Duy")
                    .email("duynguyen@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(101L).transactionType(TransactionType.BORROW).build(),
                    Transaction.builder().id(102L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc2 =
        LibraryCard.builder()
            .id(2L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(2L)
                    .firstName("Alice")
                    .lastName("Tran")
                    .email("alice.tran@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 2 * 365L * 24 * 60 * 60 * 1000) // 2 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(103L).transactionType(TransactionType.BORROW).build()))
            .libraryCardStatus(LibraryCardStatus.SUSPENDED)
            .build();

    var lc3 =
        LibraryCard.builder()
            .id(3L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(3L)
                    .firstName("Bob")
                    .lastName("Le")
                    .email("bob.le@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 3 * 365L * 24 * 60 * 60 * 1000) // 3 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(104L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc4 =
        LibraryCard.builder()
            .id(4L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(4L)
                    .firstName("Charlie")
                    .lastName("Pham")
                    .email("charlie.pham@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(105L).transactionType(TransactionType.BORROW).build()))
            .libraryCardStatus(LibraryCardStatus.EXPIRED)
            .build();

    var lc5 =
        LibraryCard.builder()
            .id(5L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(5L)
                    .firstName("David")
                    .lastName("Vu")
                    .email("david.vu@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 4 * 365L * 24 * 60 * 60 * 1000) // 4 năm sau
            .transactions(List.of())
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc6 =
        LibraryCard.builder()
            .id(6L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(6L)
                    .firstName("Emma")
                    .lastName("Hoang")
                    .email("emma.hoang@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 5 * 365L * 24 * 60 * 60 * 1000) // 5 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(106L).transactionType(TransactionType.BORROW).build(),
                    Transaction.builder().id(107L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc7 =
        LibraryCard.builder()
            .id(7L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(7L)
                    .firstName("Sophia")
                    .lastName("Ly")
                    .email("sophia.ly@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 2 * 365L * 24 * 60 * 60 * 1000) // 2 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(108L).transactionType(TransactionType.BORROW).build()))
            .libraryCardStatus(LibraryCardStatus.SUSPENDED)
            .build();

    var lc8 =
        LibraryCard.builder()
            .id(8L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(8L)
                    .firstName("James")
                    .lastName("Vo")
                    .email("james.vo@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 3 * 365L * 24 * 60 * 60 * 1000) // 3 năm sau
            .transactions(List.of())
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc9 =
        LibraryCard.builder()
            .id(9L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(9L)
                    .firstName("Isabella")
                    .lastName("Dang")
                    .email("isabella.dang@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 năm sau
            .libraryCardStatus(LibraryCardStatus.EXPIRED)
            .build();

    var lc10 =
        LibraryCard.builder()
            .id(10L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(10L)
                    .firstName("Ethan")
                    .lastName("Nguyen")
                    .email("ethan.nguyen@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 4 * 365L * 24 * 60 * 60 * 1000) // 4 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(110L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    List<Reader> readers =
        List.of(
            Reader.builder()
                .id(1L)
                .libraryCard(lc1)
                .email("reader1@gmail.com")
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .dateOfBirth(3487562934875L)
                .avatarKey("avatar1")
                .phoneNumber("123456789")
                .build(),
            Reader.builder()
                .id(2L)
                .libraryCard(lc2)
                .email("reader2@gmail.com")
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Elm St")
                .dateOfBirth(3487562934876L)
                .avatarKey("avatar2")
                .phoneNumber("987654321")
                .build(),
            Reader.builder()
                .id(3L)
                .libraryCard(lc3)
                .email("reader3@gmail.com")
                .firstName("Alice")
                .lastName("Johnson")
                .address("789 Oak St")
                .dateOfBirth(3487562934877L)
                .avatarKey("avatar3")
                .phoneNumber("555666777")
                .build(),
            Reader.builder()
                .id(4L)
                .libraryCard(lc4)
                .email("reader4@gmail.com")
                .firstName("Bob")
                .lastName("Brown")
                .address("321 Pine St")
                .dateOfBirth(3487562934878L)
                .avatarKey("avatar4")
                .phoneNumber("111222333")
                .build(),
            Reader.builder()
                .id(5L)
                .libraryCard(lc5)
                .email("reader5@gmail.com")
                .firstName("Charlie")
                .lastName("Davis")
                .address("654 Maple St")
                .dateOfBirth(3487562934879L)
                .avatarKey("avatar5")
                .phoneNumber("444555666")
                .build(),
            Reader.builder()
                .id(6L)
                .libraryCard(lc6)
                .email("reader6@gmail.com")
                .firstName("David")
                .lastName("Miller")
                .address("987 Birch St")
                .dateOfBirth(3487562934880L)
                .avatarKey("avatar6")
                .phoneNumber("777888999")
                .build(),
            Reader.builder()
                .id(7L)
                .libraryCard(lc7)
                .email("reader7@gmail.com")
                .firstName("Eve")
                .lastName("Wilson")
                .address("159 Cedar St")
                .dateOfBirth(3487562934881L)
                .avatarKey("avatar7")
                .phoneNumber("333222111")
                .build(),
            Reader.builder()
                .id(8L)
                .libraryCard(lc8)
                .email("reader8@gmail.com")
                .firstName("Frank")
                .lastName("Moore")
                .address("753 Walnut St")
                .dateOfBirth(3487562934882L)
                .avatarKey("avatar8")
                .phoneNumber("666777888")
                .build(),
            Reader.builder()
                .id(9L)
                .libraryCard(lc9)
                .email("reader9@gmail.com")
                .firstName("Grace")
                .lastName("Taylor")
                .address("852 Chestnut St")
                .dateOfBirth(3487562934883L)
                .avatarKey("avatar9")
                .phoneNumber("999000111")
                .build(),
            Reader.builder()
                .id(10L)
                .libraryCard(lc10)
                .email("reader10@gmail.com")
                .firstName("Hank")
                .lastName("Anderson")
                .address("369 Redwood St")
                .dateOfBirth(3487562934884L)
                .avatarKey("avatar10")
                .phoneNumber("222333444")
                .build());

    List<Fine> fines =
        List.of(
            Fine.builder().price(0.2f).overdueDays(5).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(0.3f).overdueDays(6).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(0.4f).overdueDays(7).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(0.5f).overdueDays(8).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(0.6f).overdueDays(9).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(0.7f).overdueDays(10).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(0.8f).overdueDays(11).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(0.9f).overdueDays(12).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(1.0f).overdueDays(13).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().price(1.1f).overdueDays(14).fineStatus(FineStatus.DEMO).build());

    List<Librarian> librarians = new ArrayList<>();

    for (int i = 1; i <= 5; i++) {
      Librarian librarian =
          Librarian.builder()
              .firstName("Librarian " + i)
              .email("librarian" + i + "@example.com")
              .phoneNumber("0" + i + i + "-" + i + i + i + "-")
              .password("password" + i)
              .build();
      librarians.add(librarian);
    }
  }
}
