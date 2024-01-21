package org.designpattern.behavioral_pattern.State;
//Concrete State
public class DispensingState implements State {
  @Override
  public void selectProduct(VendingMachine vendingMachine, Product product) {
    System.out.println("Dispensing in progress. Please wait.");
  }

  @Override
  public void insertCoin(VendingMachine vendingMachine, int amount) {
    System.out.println("Transaction in progress. Please wait for product dispensing.");
  }

  @Override
  public void dispenseProduct(VendingMachine vendingMachine) {
    Product selectedProduct = vendingMachine.getSelectedProduct();
    int productStock = selectedProduct.getStock();
    if (productStock > 0) {
      selectedProduct.setStock(selectedProduct.getStock()-1);
      vendingMachine.setUserBalance(vendingMachine.getUserBalance() - vendingMachine.getSelectedProduct().getPrice());
      System.out.printf("Product dispensed. Thank you for your purchase! Balance : %d%n", vendingMachine.getUserBalance());
      vendingMachine.setCurrentState(new IdleState());
    } else {
      System.out.println("Out of stock. Please make another selection.");
      vendingMachine.setCurrentState(new IdleState());
    }
  }

  @Override
  public void cancelTransaction(VendingMachine vendingMachine) {
    System.out.println("Can not cancel the transaction. The dispensing is in progress.");
  }
}
