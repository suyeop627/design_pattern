package org.designpattern.creational_pattern.FactoryMethod;

public interface ShipFactory {

  // java8에 추가된 default method
  //인터페이스에서 공통적으로 구현되야 하는 메서드가 있는 경우 추상클래스의 구현메서드 처럼 기본적인 구현을 가지게 할 수 있음.
  // 구현 클래스에서 overriding 하거나 안하거나, 선택적으로 사용 가능함.
  default Ship orderShip(String email) {
    validate(email);

    Ship ship = createShip(); // 선박 객체 생성

    sendEmailTo(email, ship);

    return ship;
  }

  // 팩토리 추상 메서드 - ConcreteCreator 에서 구현할 메서드
  Ship createShip();

  // java9에 추가된 private mothod
  //인터페이스 내에서만 사용가능한 메서드로, default 메서드나 sattic 메서드에 사용하기 위해 작성되는 메서드
  //인터페이스를 구현하는 클래스쪽에서 재정의하거나 사용할 수 없고, 디폴트나 정적메서드를 통해서만 사용 가능함
  private void validate(String email) {
    if (email == null) {
      throw new IllegalArgumentException("이메일을 남겨주세요");
    }
  }

  private void sendEmailTo(String email, Ship ship) {
    String[] className = this.getClass().getName().split("\\.");
    String factoryName =className[className.length-1];
    System.out.printf("%s : An email was sent to %s informing %s has been made.%n",factoryName, email, ship.name);
  }
}
