package org.designpattern.structural_pattern.Bridge;

class AirConditioner implements Device {
  @Override
  public void turnOn() {
    System.out.println("Air Conditioner is ON");
  }

  @Override
  public void turnOff() {
    System.out.println("Air Conditioner is OFF");
  }
}