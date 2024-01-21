package org.designpattern.behavioral_pattern.TemplateMethod;

public enum Item {
  BASIC_PIZZA(12_000), CHEESE_PIZZA(15_000);
  private final int price;
  Item(int price) {
    this.price = price;
  }
  public int getPrice(){
    return price;
  }
}
