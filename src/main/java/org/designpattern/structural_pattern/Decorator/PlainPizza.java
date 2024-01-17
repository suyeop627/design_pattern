package org.designpattern.structural_pattern.Decorator;

public class PlainPizza implements Pizza{

  @Override
  public String getDescription() {
    return "Pizza";
  }

  @Override
  public double getCost() {
    return 12_000;
  }
}
