package com.g15.library_system.data;

import java.util.List;

public interface Data<T> {
  void add(T t);

  void add(List<T> t);

  void remove(T t);

  void remove(int index);
}
