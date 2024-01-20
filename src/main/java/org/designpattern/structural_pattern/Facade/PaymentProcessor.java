package org.designpattern.structural_pattern.Facade;

// 서브시스템 - 결제 처리
class PaymentProcessor {
  public void processPayment(String paymentMethod, double amount) {
    System.out.println("Processing payment of " + amount + " via " + paymentMethod);
    // 결제 처리 로직 수행
  }
}
