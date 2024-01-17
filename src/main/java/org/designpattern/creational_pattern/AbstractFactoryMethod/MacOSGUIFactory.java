package org.designpattern.creational_pattern.AbstractFactoryMethod;

public class MacOSGUIFactory implements GUIFactory{

  private static class SingletonInstanceHolder {
    private static final MacOSGUIFactory INSTANCE = new MacOSGUIFactory();
  }

  public static MacOSGUIFactory getInstance(){
    return SingletonInstanceHolder.INSTANCE;
  }


  @Override
  public Button createButton() {
    return new MacOSButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new MacOSCheckbox();
  }
}
