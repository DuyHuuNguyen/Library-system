package com.g15.library_system.view.overrideComponent.dateChoosers.listener;

import com.g15.library_system.view.overrideComponent.dateChoosers.DateBetween;
import java.util.Date;

public interface DateChooserListener {
  public void dateChanged(Date date, DateChooserAction action);

  public void dateBetweenChanged(DateBetween date, DateChooserAction action);
}
