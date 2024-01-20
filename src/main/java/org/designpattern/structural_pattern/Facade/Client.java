package org.designpattern.structural_pattern.Facade;

public class Client {
  public static void main(String[] args) {
    // 퍼사드를 통해 주문을 처리
    OrderFacade orderFacade = new OrderFacade();
    orderFacade.placeOrder("12345", 2, "CreditCard", 150.0, "경기도 시흥시");
  }
}
