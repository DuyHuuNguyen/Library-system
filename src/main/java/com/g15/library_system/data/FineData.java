package com.g15.library_system.data;

import com.g15.library_system.entity.OverdueFine;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class FineData implements Data<OverdueFine> {
  private static final FineData INSTANCE = new FineData();
  private final List<OverdueFine> overdueFines = new ArrayList<>();

  private FineData() {
    this.initializeData();
  }

  @Override
  public void add(OverdueFine b) {
    this.overdueFines.add(b);
  }

  @Override
  public void add(List<OverdueFine> t) {}

  @Override
  public void remove(OverdueFine OverdueFine) {}

  @Override
  public void remove(int index) {}

  public static FineData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {
    List<OverdueFine> overdueFineInt =
        List.of(
            OverdueFine.builder().price(3500).build(),
            OverdueFine.builder().price(3500).build(),
            OverdueFine.builder().price(3500).build(),
            OverdueFine.builder().price(3500).build(),
            OverdueFine.builder().price(3500).build(),
            OverdueFine.builder().price(3500).build(),
            OverdueFine.builder().price(3500).build());

    overdueFines.addAll(overdueFineInt);
  }
}
