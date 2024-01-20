package org.designpattern.structural_pattern.Facade;

// 서브시스템 - 주문 처리
class OrderProcessing {
  public void processOrder(String productId, int quantity) {
    System.out.println("Processing order for product: " + productId + ", Quantity: " + quantity);
    // 주문 처리 로직 수행
  }
}
