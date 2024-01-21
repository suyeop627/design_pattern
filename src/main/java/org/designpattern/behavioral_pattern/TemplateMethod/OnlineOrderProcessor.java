package org.designpattern.behavioral_pattern.TemplateMethod;
// ConcreteClass: Online 주문 처리를 특화시킨 클래스
public class OnlineOrderProcessor extends OrderProcessor{
  @Override
  protected void validateOrder() {
    System.out.println("Validating online order");
  }
  // Hook Method를 재정의하여 Online 주문에 맞는 할인 적용
  @Override
  protected void applyDiscount(int totalPrice) {
    totalPrice *=0.95;
    System.out.printf("Discount applied for online order. Total amount after discount: %d%n", totalPrice);
  }
  // Hook Method를 재정의하여 Online Express 주문 여부 확인
  @Override
  protected boolean isExpressDelivery(OrderType orderType) {
    return orderType==OrderType.ONLINE_EXPRESS;
  }

  @Override
  protected void placeOrder(OrderType orderType) {
    System.out.println("Place online order");
  }
}
