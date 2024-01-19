package org.designpattern.structural_pattern.Decorator;

public class MushroomDecorator extends PizzaToppingDecorator{
  public MushroomDecorator(Pizza pizza) {
    super(pizza);
  }
  @Override
  public String getDescription() {
    return super.getDescription() + ", with Mushrooms";
  }
  @Override
  public double getCost() {
    return super.getCost()+3_000;
  }
}
