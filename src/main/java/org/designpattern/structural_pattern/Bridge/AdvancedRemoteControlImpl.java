package org.designpattern.structural_pattern.Bridge;

class AdvancedRemoteControlImpl extends AdvancedRemoteControl {
  public AdvancedRemoteControlImpl(Device device) {
    super(device);
  }

  @Override
  void powerOn() {
    System.out.println("Advanced Remote: Power ON");
    device.turnOn(); // Implementor의 메서드 호출
  }

  @Override
  void powerOff() {
    System.out.println("Advanced Remote: Power OFF");
    device.turnOff(); // Implementor의 메서드 호출
  }
  @Override
  void extraFunction() {
    System.out.println("Advanced Remote: Extra Function");
  }
}
