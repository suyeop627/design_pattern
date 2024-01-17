package org.designpattern.structural_pattern.Decorator;

public class PepperoniDecorator extends PizzaToppingDecorator{
  public PepperoniDecorator(Pizza pizza) {
    super(pizza);
  }

  @Override
  public String getDescription() {
    return super.getDescription() + ", with Pepperoni";
  }

  @Override
  public double getCost() {
    return super.getCost()+2_000;
  }
}
