package org.designpattern.behavioral_pattern.State;

public class Client {
  public static void main(String[] args) {
    VendingMachine vendingMachine = new VendingMachine();

    vendingMachine.selectProduct(Product.COKE);
    vendingMachine.insertCoin(1000);
    vendingMachine.insertCoin(600);
    vendingMachine.dispenseProduct();
    System.out.println();
    vendingMachine.selectProduct(Product.COFFEE);
    vendingMachine.insertCoin(400);
    vendingMachine.dispenseProduct();
    System.out.println();
    vendingMachine.selectProduct(Product.COFFEE);
    vendingMachine.insertCoin(700);
    vendingMachine.dispenseProduct();
    System.out.println();
    vendingMachine.setSelectedProduct(Product.COFFEE);
    vendingMachine.insertCoin(1000);
    vendingMachine.cancelTransaction();
    vendingMachine.cancelTransaction();
  }
}
