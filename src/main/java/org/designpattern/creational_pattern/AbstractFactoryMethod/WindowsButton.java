package org.designpattern.creational_pattern.AbstractFactoryMethod;

public class WindowsButton implements Button{
  @Override
  public void click() {
    System.out.println("Windows button clicked");
  }
}
