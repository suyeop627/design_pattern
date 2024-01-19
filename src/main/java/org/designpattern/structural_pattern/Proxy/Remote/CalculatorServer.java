package org.designpattern.structural_pattern.Proxy.Remote;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
// CalculatorServer 클래스는 CalculatorImpl 객체를 RMI 서버로 등록하는 역할을 합니다.
public class CalculatorServer {
  public static void main(String[] args) {
    try {
      Calculator calculator = new CalculatorImpl();

      // RMI Registry를 포트 1099에서 생성
      LocateRegistry.createRegistry(1099);

      // RMI Registry에 CalculatorImpl 객체를 "CalculatorService"라는 이름으로 바인딩
      Naming.rebind("rmi://localhost/CalculatorService", calculator);

      System.out.println("Calculator server is running...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}