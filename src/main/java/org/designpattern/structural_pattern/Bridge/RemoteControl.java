package org.designpattern.structural_pattern.Bridge;

// Abstraction: 리모컨의 추상화 클래스
abstract class RemoteControl {
  protected Device device; // Implementor를 참조하는 멤버

  public RemoteControl(Device device) {
    this.device = device;
  }

  abstract void powerOn(); // Implementor의 메서드를 사용하는 추상 메서드
  abstract void powerOff(); // Implementor의 메서드를 사용하는 추상 메서드
}
