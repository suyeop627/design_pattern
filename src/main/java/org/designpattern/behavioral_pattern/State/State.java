package org.designpattern.behavioral_pattern.State;

public interface State {
  void selectProduct(VendingMachine vendingMachine, Product product);

  void insertCoin(VendingMachine vendingMachine, int amount);

  void dispenseProduct(VendingMachine vendingMachine);

  void cancelTransaction(VendingMachine vendingMachine);
}
