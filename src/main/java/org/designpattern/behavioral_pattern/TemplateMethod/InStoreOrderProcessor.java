package org.designpattern.behavioral_pattern.TemplateMethod;
// ConcreteClass: In-store 주문 처리를 특화시킨 클래스
public class InStoreOrderProcessor extends OrderProcessor{
  @Override
  protected void validateOrder() {
    System.out.println("Validating in-store order");
  }

  @Override
  protected void placeOrder(OrderType orderType) {
    System.out.println("Placing in-store order");
  }
}
