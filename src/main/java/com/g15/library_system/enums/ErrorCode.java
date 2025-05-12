package com.g15.library_system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  PERMISSION_DENIED("1000", "Permission denied"),
  JWT_INVALID("1003", "Invalid JWT"),
  USERNAME_OR_PASSWORD_DOES_NOT_MATCHED("1004", "Username or password does not matched"),
  OTP_INVALID("1005", "Invalid OTP"),
  PASSWORD_DOES_NOT_MATCHED("1006", "Password does not matched"),
  USER_ALREADY_EXISTS("1009", "User already exists"),
  USER_NOT_FOUND("1009", "User not found"),
  BLOCK_USER("1010", "Was blocked"),
  ROLE_NOT_FOUND("1011", "Role not found"),

  BOOK_NOT_FOUND("112", "Book not found"),
  FINE_NOT_FOUND("113", "Fine not found"),
  LIBRARIAN_NOT_FOUND("114", "Librarian not found"),
  LIBRARY_NOT_FOUND("115", "Library not found"),
  LIBRARY_CARD_NOT_FOUND("116", "LibraryCard not found"),
  READER_NOT_FOUND("117", "Reader not found"),
  STUDENT_READER_TYPE_NOT_FOUND("119", "StudentReaderType not found"),
  SUBJECT_NOT_FOUND("120", "Subject not found"),
  TRANSACTION_NOT_FOUND("121", "Transaction not found"),
  USER_ROLE_NOT_FOUND("122", "UserRole not found"),
  IMAGE_NOT_FOUND("111", "Image not found");

  private final String code;
  private final String message;
}
