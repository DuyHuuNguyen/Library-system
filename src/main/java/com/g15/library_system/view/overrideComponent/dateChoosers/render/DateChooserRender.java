package com.g15.library_system.view.overrideComponent.dateChoosers.render;

import com.g15.library_system.view.overrideComponent.dateChoosers.DateBetween;
import com.g15.library_system.view.overrideComponent.dateChoosers.DateChooser;
import java.util.Date;

public interface DateChooserRender {
  public String renderLabelCurrentDate(DateChooser dateChooser, Date date);

  public String renderTextFieldDate(DateChooser dateChooser, Date date);

  public String renderTextFieldDateBetween(DateChooser dateChooser, DateBetween dateBetween);

  public String renderDateCell(DateChooser dateChooser, Date date);
}
