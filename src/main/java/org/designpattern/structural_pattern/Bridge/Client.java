package org.designpattern.structural_pattern.Bridge;

// 클라이언트 코드
public class Client {
  public static void main(String[] args) {
    // 전자기기 생성
    Device tv = new TV();
    Device airConditioner = new AirConditioner();

    // 리모컨 생성
    RemoteControl basicRemoteForTV = new BasicRemoteControl(tv);
    AdvancedRemoteControl advancedRemoteForTV = new AdvancedRemoteControlImpl(tv);

    RemoteControl basicRemoteForAC = new BasicRemoteControl(airConditioner);
    AdvancedRemoteControl advancedRemoteForAC = new AdvancedRemoteControlImpl(airConditioner);

    // 리모컨을 사용하여 전자기기 조작
    basicRemoteForTV.powerOn(); // Basic Remote: Power ON \n TV is ON
    basicRemoteForTV.powerOff(); // Basic Remote: Power OFF \n TV is OFF

    advancedRemoteForTV.powerOn(); // Advanced Remote: Power ON \n TV is ON
    advancedRemoteForTV.powerOff(); // Advanced Remote: Power OFF \n TV is OFF
    advancedRemoteForTV.extraFunction(); // Advanced Remote: Additional Function

    basicRemoteForAC.powerOn(); // Basic Remote: Power ON \n Air Conditioner is ON
    basicRemoteForAC.powerOff(); // Basic Remote: Power OFF \n Air Conditioner is OFF

    advancedRemoteForAC.powerOn(); // Advanced Remote: Power ON \n Air Conditioner is ON
    advancedRemoteForAC.powerOff(); // Advanced Remote: Power OFF \n Air Conditioner is OFF
    advancedRemoteForAC.extraFunction(); // Advanced Remote: Additional Function
  }
}