package org.designpattern.behavioral_pattern.TemplateMethod;

import java.util.List;
// AbstractClass: 주문 처리 알고리즘의 구조를 정의하는 추상 클래스
abstract class OrderProcessor {
  // Template Method: 주문 처리 알고리즘의 전체 구조를 정의
  public final void processOrder(List<Item> items, OrderType orderType) {
    validateOrder();
    int totalPrice = calculateTotal(items);
    applyDiscount(totalPrice);
    if (isExpressDelivery(orderType)) {
      performExpressDelivery();
    }
    placeOrder(orderType);
  }

  protected abstract void validateOrder();

  protected int calculateTotal(List<Item> items) {
    int totalPrice = items.stream()
        .map(Item::getPrice)
        .mapToInt(Integer::intValue)
        .sum();
    System.out.printf("Total amount calculated for in-store order: %d%n", totalPrice);
    return totalPrice;
  }
  // Hook Method: 서브클래스에서 선택적으로 재정의 가능한 메서드
  protected void applyDiscount(int totalPrice) {
    System.out.println("No discount applied");
  }
  // Hook Method: 서브클래스에서 선택적으로 재정의 가능한 메서드로, 구현을 통해 분기점을 생성할 수 있음
  protected boolean isExpressDelivery(OrderType orderType) {
    return false;
  }

  protected void performExpressDelivery() {
    System.out.println("Express delivery selected");
  }

  protected abstract void placeOrder(OrderType orderType);
}