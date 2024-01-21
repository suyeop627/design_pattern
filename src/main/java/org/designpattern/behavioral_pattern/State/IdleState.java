package org.designpattern.behavioral_pattern.State;
//Concrete State
public class IdleState implements State{
  @Override
  public void selectProduct(VendingMachine vendingMachine, Product product) {
    vendingMachine.setSelectedProduct(product);
    vendingMachine.setCurrentState(new PaymentPendingState());
    System.out.printf("Product %s (%s) is selected. Please insert coins. Balance : %d%n", product.name(), product.getPrice()+" won", vendingMachine.getUserBalance());
  }

  @Override
  public void insertCoin(VendingMachine vendingMachine, int amount) {
    System.out.println("Please select a product first.");
  }

  @Override
  public void dispenseProduct(VendingMachine vendingMachine) {
    System.out.println("Please select a product and insert coins.");
  }

  @Override
  public void cancelTransaction(VendingMachine vendingMachine) {
    if(vendingMachine.getUserBalance()>0){
      System.out.println("The transaction canceled. Return the balance : " + vendingMachine.getUserBalance());
      vendingMachine.setUserBalance(0);
      vendingMachine.setSelectedProduct(null);
    }else{
      System.out.println("No transaction to be canceled.");
    }
  }
}
