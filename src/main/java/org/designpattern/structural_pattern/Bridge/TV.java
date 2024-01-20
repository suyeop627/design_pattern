package org.designpattern.structural_pattern.Bridge;

// Concrete Implementors: 전자기기의 구현
class TV implements Device {
  @Override
  public void turnOn() {
    System.out.println("TV is ON");
  }

  @Override
  public void turnOff() {
    System.out.println("TV is OFF");
  }
}
