package org.designpattern.behavioral_pattern.Observer;

public class StockManager{
  public void restock(Store store) {
    store.changeStock(3);
    System.out.println("Item restocked. Remaining items : " + store.stock);
  }
}
