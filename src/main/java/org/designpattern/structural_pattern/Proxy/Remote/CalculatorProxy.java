package org.designpattern.structural_pattern.Proxy.Remote;

import java.rmi.Naming;
//Calculator 인터페이스의 원격 객체를 대행하는 프록시 역할을 합니다.
public class CalculatorProxy {
  private Calculator calculator;
  // 생성자에서 원격 객체에 대한 룩업을 수행합니다.
  public CalculatorProxy() {
    try {
      // RMI Registry에 등록된 CalculatorImpl 객체를 찾아서 사용합니다.
      // "//localhost/CalculatorService"는 서버에서 RMI Registry에 등록된 객체의 위치를 나타냅니다.
      //port를 명시하지 않으면 기본 포트인 1099 사용함
      this.calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
      System.out.println("Calculator obtained from Remote Method Invocation");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
//실제 계산은 원격 서버에서 이뤄짐
  public int add(int a, int b) {
    try {
      System.out.println("this = " + this);
      return calculator.add(a, b);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }

  public int subtract(int a, int b) {
    try {
      System.out.println("this = " + this);
      return calculator.subtract(a, b);
    } catch (Exception e) {
      e.printStackTrace();
      return 0;
    }
  }
}
