package com.g15.library_system.data;

import com.g15.library_system.entity.Fine;
import com.g15.library_system.enums.FineStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class FineData implements Data<Fine> {
  private static final FineData INSTANCE = new FineData();
  private final List<Fine> fines = new ArrayList<>();

  private FineData() {
    this.initializeData();
  }

  @Override
  public void add(Fine b) {
    this.fines.add(b);
  }

  @Override
  public void add(List<Fine> t) {}

  @Override
  public void remove(Fine Fine) {}

  @Override
  public void remove(int index) {}

  public static FineData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {
    List<Fine> fineInt =
        List.of(
            Fine.builder().id(1L).price(0.2f).overdueDays(5).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(2L).price(0.3f).overdueDays(6).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(3L).price(0.4f).overdueDays(7).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(4L).price(0.5f).overdueDays(8).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(5L).price(0.6f).overdueDays(9).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(6L).price(0.7f).overdueDays(10).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(7L).price(0.8f).overdueDays(11).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(8L).price(0.9f).overdueDays(12).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(9L).price(1.0f).overdueDays(13).fineStatus(FineStatus.DEMO).build(),
            Fine.builder().id(10L).price(1.1f).overdueDays(14).fineStatus(FineStatus.DEMO).build());

    fines.addAll(fineInt);
  }
}
