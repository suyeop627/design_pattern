package org.designpattern.structural_pattern.Bridge;

abstract class AdvancedRemoteControl extends RemoteControl{
  public AdvancedRemoteControl(Device device) {
    super(device);
  }

  abstract void extraFunction();
}
