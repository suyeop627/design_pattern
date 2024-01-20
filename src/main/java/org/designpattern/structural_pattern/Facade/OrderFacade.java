package org.designpattern.structural_pattern.Facade;

// 퍼사드 클래스
class OrderFacade {
  private final OrderProcessing orderProcessing;
  private final PaymentProcessor paymentProcessor;
  private final ShippingService shippingService;

  public OrderFacade() {
    this.orderProcessing = new OrderProcessing();
    this.paymentProcessor = new PaymentProcessor();
    this.shippingService = new ShippingService();
  }

  // 주문 처리를 단순화한 인터페이스 메서드
  public void placeOrder(String productId, int quantity, String paymentMethod, double amount, String address) {
    orderProcessing.processOrder(productId, quantity);
    paymentProcessor.processPayment(paymentMethod, amount);
    shippingService.shipOrder(productId, address);
    System.out.println("Order placed successfully.");
  }
}
