package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.LibraryCard;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.util.DateUtil;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;

public class ReaderMapper {
  private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

  // Nếu danh sách kết quả đã được tạo checkbox thì
  // - hasCheckBox = true (không cần gắn Boolean.FALSE cho ds output)
  // - ___________ = false (chưa được tạo sẵn, cần tạo cho ds output)
  public static Object[] mapReaderToObjectRow(Reader reader, boolean hasCheckbox) {
    // Lấy full name
    //        String lastName = reader.getLastName();
    //        String firstName = reader.getFirstName();
    String fullName = reader.getFullName();

    // Tạo avatar từ key (giả sử dùng key để lấy ảnh từ resources)
    String imageUrl = reader.getAvatarKey();
    ImageIcon avatar =
        new ImageIcon(ReaderMapper.class.getResource(imageUrl)); // hoặc dựa vào avatarKey

    // Trạng thái: bạn cần xác định từ đâu (ví dụ hardcode hoặc từ isSubscribe)
    UserStatus status = reader.getIsSubscribe() ? UserStatus.ACTIVE : UserStatus.INACTIVE;

    // Format ngày
    String dobStr = sdf.format(new java.util.Date(reader.getDateOfBirth()));
    String createdStr = sdf.format(new java.util.Date(reader.getCreatedAt()));

    // Kiểu người dùng (ví dụ)
    String readerType = "Student";

    // Số lượng sách đang mượn (giả sử 0 nếu chưa có dữ liệu)
    int borrowedBooks = 0;

    return (!hasCheckbox)
        ? new Object[] {
          Boolean.FALSE,
          reader.getId(), // ID hiển thị
          avatar,
          fullName,
          //                lastName,
          //                firstName,
          readerType,
          status,
          dobStr,
          reader.getPhoneNumber(),
          reader.getEmail(),
          reader.getAddress(),
          createdStr,
          borrowedBooks
        }
        : new Object[] {
          reader.getId(), // ID hiển thị
          avatar,
          fullName,
          //                lastName,
          //                firstName,
          readerType,
          status,
          dobStr,
          reader.getPhoneNumber(),
          reader.getEmail(),
          reader.getAddress(),
          createdStr,
          borrowedBooks
        };
  }

  public static Object[][] mapAllReadersToTableData(List<Reader> readers, boolean hasCheckbox) {
    Object[][] data = new Object[readers.size()][];
    for (int i = 0; i < readers.size(); i++) {
      data[i] = mapReaderToObjectRow(readers.get(i), hasCheckbox);
    }
    return data;
  }

  public static Object[][] getCurrentBorrowedBooksAsTable(Reader reader) {
    List<Object[]> rows = new ArrayList<>();

    LibraryCard card = reader.getLibraryCard();
    for (Transaction transaction : card.getBorrowTransactions()) {
      if (transaction.getTransactionType() == TransactionType.BORROW
          && transaction.getActualReturnAt() == null) {
        for (Map.Entry<Book, Integer> entry : transaction.getBooks().entrySet()) {
          Book book = entry.getKey();
          int quantity = entry.getValue();

          Object[] row =
              new Object[] {
                Boolean.FALSE,
                book.getId(),
                book.getTitle(),
                quantity,
                DateUtil.convertToLocalDate(transaction.getCreatedAt())
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                DateUtil.convertToLocalDate(transaction.getExpectedReturnAt())
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
              };

          rows.add(row);
        }
      }
    }

    return rows.toArray(new Object[0][0]);
  }
}
