package org.designpattern.structural_pattern.Decorator;

public abstract class PizzaToppingDecorator implements Pizza{
  protected Pizza decoratedPizza;

  public PizzaToppingDecorator(Pizza pizza){
    this.decoratedPizza = pizza;
  }

  @Override
  public String getDescription() {
    return decoratedPizza.getDescription();
  }

  @Override
  public double getCost() {
    return decoratedPizza.getCost();
  }

}
