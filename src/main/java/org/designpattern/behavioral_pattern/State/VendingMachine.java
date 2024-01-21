package org.designpattern.behavioral_pattern.State;
//Context
public class VendingMachine {

  State currentState;
  private Product selectedProduct;
  private int userBalance;
  public VendingMachine() {
    userBalance = 0;
    currentState = new IdleState();
  }
  public void setCurrentState(State currentState) {
    this.currentState = currentState;
  }

  public Product getSelectedProduct() {
    return selectedProduct;
  }

  public void setSelectedProduct(Product selectedProduct) {
    this.selectedProduct = selectedProduct;
  }

  public int getUserBalance() {
    return userBalance;
  }

  public void setUserBalance(int userBalance) {
    this.userBalance = userBalance;
  }

  public void selectProduct(Product product) {
    currentState.selectProduct(this, product);
  }

  public void insertCoin(int amount) {
    currentState.insertCoin(this, amount);
  }

  public void dispenseProduct() {
    currentState.dispenseProduct(this);
  }
  public void cancelTransaction(){
    currentState.cancelTransaction(this);
  }
}
