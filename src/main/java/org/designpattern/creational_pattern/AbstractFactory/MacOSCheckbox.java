package org.designpattern.creational_pattern.AbstractFactory;

public class MacOSCheckbox implements Checkbox{

  @Override
  public void check() {
    System.out.println("MacOS checkbox checked");
  }
}
