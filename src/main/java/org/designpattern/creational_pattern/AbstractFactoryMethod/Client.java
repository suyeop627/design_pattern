package org.designpattern.creational_pattern.AbstractFactoryMethod;

public class Client {
  public static void main(String[] args) {
    // Windows 플랫폼에 맞는 팩토리 선택
    GUIFactory windowsGUIFactory = WindowsGUIFactory.getInstance();

    // Windows 팩토리를 사용하여 버튼과 체크박스 생성
    Button windowsButton = windowsGUIFactory.createButton();
    Checkbox windowsCheckbox = windowsGUIFactory.createCheckbox();

    // 생성된 객체 사용
    windowsButton.click();
    windowsCheckbox.check();


    // MacOS 플랫폼에 맞는 팩토리 선택
    GUIFactory macOSFactory = MacOSGUIFactory.getInstance();

    // MacOS 팩토리를 사용하여 버튼과 체크박스 생성
    Button macOSButton = macOSFactory.createButton();
    Checkbox macOSCheckbox = macOSFactory.createCheckbox();

    // 생성된 객체 사용
    macOSButton.click();
    macOSCheckbox.check();
  }
}
