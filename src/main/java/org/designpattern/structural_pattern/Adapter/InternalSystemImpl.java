package org.designpattern.structural_pattern.Adapter;

//기존 시스템으로, 외부 시스템으로 대체될 클래스
public class InternalSystemImpl implements InternalSystem {
  @Override
  public void performInternalAction() {
    System.out.println("Performing action in the internal system.");
  }
}
