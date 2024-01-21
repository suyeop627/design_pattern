package org.designpattern.behavioral_pattern.State;
//Concrete State
public class PaymentPendingState implements State {
  @Override
  public void selectProduct(VendingMachine vendingMachine, Product product) {
    System.out.println("Payment pending. Please complete the current transaction.");
  }

  @Override
  public void insertCoin(VendingMachine vendingMachine, int amount) {
    int totalAmount = vendingMachine.getUserBalance() + amount;
    vendingMachine.setUserBalance(totalAmount);

    if (totalAmount >= vendingMachine.getSelectedProduct().getPrice()) {
      vendingMachine.setCurrentState(new DispensingState());
      System.out.printf("Payment completed. Dispensing product. Inserted coins : %d%n", amount);
    } else {
      System.out.printf("Payment pending. Please insert more coins. Balance : %d%n", vendingMachine.getUserBalance());
    }
  }

  @Override
  public void dispenseProduct(VendingMachine vendingMachine) {
    System.out.println("Payment pending. Please insert coins.");
  }

  @Override
  public void cancelTransaction(VendingMachine vendingMachine) {
    System.out.println("The transaction canceled. Go back to Idle State.");
    vendingMachine.setCurrentState(new IdleState());
  }
}
