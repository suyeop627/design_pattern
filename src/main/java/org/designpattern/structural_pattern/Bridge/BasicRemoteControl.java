package org.designpattern.structural_pattern.Bridge;

// Refined Abstractions: 미세한 추상화 클래스
class BasicRemoteControl extends RemoteControl {
  public BasicRemoteControl(Device device) {
    super(device);
  }

  @Override
  void powerOn() {
    System.out.println("Basic Remote: Power ON");
    device.turnOn(); // Implementor의 메서드 호출
  }

  @Override
  void powerOff() {
    System.out.println("Basic Remote: Power OFF");
    device.turnOff(); // Implementor의 메서드 호출
  }
}