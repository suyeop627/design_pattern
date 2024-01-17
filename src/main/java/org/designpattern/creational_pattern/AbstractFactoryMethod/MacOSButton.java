package org.designpattern.creational_pattern.AbstractFactoryMethod;

public class MacOSButton implements Button{
  @Override
  public void click() {
    System.out.println("MacOS button clicked");
  }
}
