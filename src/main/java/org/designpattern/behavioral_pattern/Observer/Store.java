package org.designpattern.behavioral_pattern.Observer;

import java.util.ArrayList;
import java.util.List;

// ConcreteSubject
class Store implements Subject, Runnable {
  private List<Observer> waitingList = new ArrayList<>();
  int stock =0;
  private final StockManager stockManager;
  public Store(StockManager stockManager) {
    this.stockManager = stockManager;
  }
  public synchronized void changeStock(int value){
    this.stock+=value;
  }
  @Override
  public void addObserver(Observer observer) {
    waitingList.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    waitingList.remove(observer);
  }

  @Override
  public void notifyObservers() {
    String message = "Message from store : Item have restocked. Remaining items : %d, Customers waiting items : %d".formatted(stock, waitingList.size());
    for (Observer customer : waitingList) {
      customer.update(message);
    }
  }

  public synchronized boolean sellItem(Customer customer) {
    if (stock > 0) {
      changeStock(-1);
      removeObserver(customer); // 손님 구독 해지
      System.out.println("Store : " + customer.getName() + " is removed from waiting list.");
      return true;
    } else {
      if(!waitingList.contains(customer)){
        addObserver(customer);
        System.out.println(customer.getName() + " is added to waiting list. Remaining items : "+stock);
      }else{
        System.out.println(customer.getName() + " is fail to buy item. Remaining items : "+stock);
      }
      return false;
    }
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep(5000);
        stockManager.restock(this);
        notifyObservers();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
