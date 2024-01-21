package org.designpattern.behavioral_pattern.Observer;

// ConcreteObserver - Subject의 알림을 받음
public class Customer implements Observer, Runnable {
  private String name;
  private Store store;
  private boolean isBoughtItem = false;

  public Customer(String name, Store store) {
    this.name = name;
    this.store = store;
  }

  @Override
  public void update(String message) {
    System.out.println(message);
  }

  private void requestItem(){
    isBoughtItem = store.sellItem(this);
    if(isBoughtItem){
      System.out.println(this.name + " is bought item.");
    }
  }

  @Override
  public void run() {
    while (!isBoughtItem) {
      try {
        Thread.sleep(2000);
        requestItem();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public String getName() {
    return name;
  }
}
