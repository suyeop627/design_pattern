package org.designpattern.behavioral_pattern.Observer;

import java.util.ArrayList;
import java.util.List;

public class Client {
  public static void main(String[] args) {
    StockManager stockManager = new StockManager();
    Store store = new Store(stockManager);
    List<Customer> customers = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      Customer customer = new Customer("손님" + i, store);
      customers.add(customer);
    }

    Thread stockManagerThread = new Thread(store);
    stockManagerThread.start();

    for (Customer customer : customers) {
      Thread customerThread = new Thread(customer);
      customerThread.start();
    }
  }
}
