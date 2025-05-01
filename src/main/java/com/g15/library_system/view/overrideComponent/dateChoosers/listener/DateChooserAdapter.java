package com.g15.library_system.view.overrideComponent.dateChoosers.listener;


import com.g15.library_system.view.overrideComponent.dateChoosers.DateBetween;

import java.util.Date;

public abstract class DateChooserAdapter implements DateChooserListener {

    @Override
    public void dateChanged(Date date, DateChooserAction action) {
    }

    @Override
    public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
    }
}
