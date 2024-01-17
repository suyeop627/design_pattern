package org.designpattern.structural_pattern.Decorator;

public class ShrimpDecorator extends PizzaToppingDecorator{
  public ShrimpDecorator(Pizza pizza) {
    super(pizza);
  }

  @Override
  public String getDescription() {
    return super.getDescription() + ", with Shrimp";
  }

  @Override
  public double getCost() {
    return super.getCost()+5_000;
  }
}
