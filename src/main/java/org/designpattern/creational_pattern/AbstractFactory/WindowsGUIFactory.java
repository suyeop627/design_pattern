package org.designpattern.creational_pattern.AbstractFactory;

public class WindowsGUIFactory implements GUIFactory{

  private static class SingletonInstanceHolder{
    private static final WindowsGUIFactory INSTANCE = new WindowsGUIFactory();
  }

  public static WindowsGUIFactory getInstance(){
    return SingletonInstanceHolder.INSTANCE;
  }
  @Override
  public Button createButton() {
    return new WindowsButton();
  }

  @Override
  public Checkbox createCheckbox() {
    return new WindowsCheckbox();
  }
}
