package org.designpattern.structural_pattern.Proxy.Protection;

public class RealSubject implements Subject {
  @Override
  public void response() {
    System.out.println("response from RealSubject");
  }
}
