package com.g15.library_system.entity;

public interface OverdueFeeStrategy {
  Double calculateOverdueFee(Transaction transaction);
}
