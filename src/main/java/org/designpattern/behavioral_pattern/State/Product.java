package org.designpattern.behavioral_pattern.State;

public enum Product {
  COFFEE(500,1), COKE(1500,2), JUICE(1200,1);

  private final int price;
  private int stock;
  
  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }


  Product(int price, int stock) {
    this.price = price;
    this.stock = stock;
  }

  public int getPrice() {
    return price;
  }

}
