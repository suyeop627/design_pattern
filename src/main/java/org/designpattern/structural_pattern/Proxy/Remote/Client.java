package org.designpattern.structural_pattern.Proxy.Remote;

public class Client {
  public static void main(String[] args) {
    try {
      // 원격 서버에서 CalculatorImpl 클래스가 실행중이어야 함.
      CalculatorProxy proxy = new CalculatorProxy();

      // 프록시를 통한 원격 객체의 메서드 호출
      int resultAdd = proxy.add(10, 5);
      System.out.println("Result of addition: " + resultAdd);

      int resultMultiply = proxy.subtract(3, 4);
      System.out.println("Result of subtraction: " + resultMultiply);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}