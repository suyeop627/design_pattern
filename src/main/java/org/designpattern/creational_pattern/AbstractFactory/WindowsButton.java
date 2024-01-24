package org.designpattern.creational_pattern.AbstractFactory;

public class WindowsButton implements Button{
  @Override
  public void click() {
    System.out.println("Windows button clicked");
  }
}
