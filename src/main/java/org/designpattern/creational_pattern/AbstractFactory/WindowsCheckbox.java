package org.designpattern.creational_pattern.AbstractFactory;

public class WindowsCheckbox implements Checkbox{
  @Override
  public void check() {
    System.out.println("Windows checkbox checked");
  }
}
