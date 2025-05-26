package com.g15.library_system.entity;

import com.g15.library_system.entity.strategies.OverdueFineStrategy;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@Getter
@SuperBuilder
@AllArgsConstructor
public class OverdueFine {
  private double price;
  @Setter private OverdueFineStrategy strategy;

  public void calculateFine(Transaction transaction, LocalDate returnDate) {
    this.price = this.strategy.calculateFine(transaction, returnDate);
  }
}
