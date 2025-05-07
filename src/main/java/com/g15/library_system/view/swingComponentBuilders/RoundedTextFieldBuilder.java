package com.g15.library_system.view.swingComponentBuilders;

import com.g15.library_system.view.overrideComponent.textFields.RoundedPlaceholderTextField;

public class RoundedTextFieldBuilder extends RoundedPlaceholderTextField {

  public static TextFieldBuilder builder() {
    return new TextFieldBuilder();
  }
}
