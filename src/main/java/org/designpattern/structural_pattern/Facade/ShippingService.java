package org.designpattern.structural_pattern.Facade;

// 서브시스템 - 배송 처리
class ShippingService {
  public void shipOrder(String productId, String address) {
    System.out.println("Shipping product: " + productId + " to address: " + address);
    // 배송 처리 로직 수행
  }
}
