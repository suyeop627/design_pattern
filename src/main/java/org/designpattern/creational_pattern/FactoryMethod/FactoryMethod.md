### 팩토리 메서드 패턴
- 개념
  - 팩토리 메소드 패턴은 객체 생성을 공장(Factory) 클래스로 캡슐화해서 대신 생성하게 하는 생성 디자인 패턴.
  - 팩토리 메소드 패턴은 객체 생성을 공장(Factory) 클래스로 캡슐화해서 대신 생성하게 하는 생성 디자인 패턴.
  - 클라이언트에서 직접 new 연산자를 통해 제품 객체를 생성하는 것이 아니라, 제품 객체들을 도맡아 생성하는 공장 클래스를 만들고, 이를 상속하는 서브 공장 클래스의 메서드에서 여러가지 제품 객체 생성을 각각 책임 지는 것.
  - 또한, 객체 생성에 필요한 과정을 템플릿 처럼 미리 구성해놓고, 객체 생성에 관한 전처리나 후처리를 통해 생성 과정을 다양하게 처리하여 객체를 유연하게 처리할 수 있음
- 구성
    - Creator : 최상위 공장 클래스로서, 팩토리 메서드를 추상화하여 서브 클래스로 하여금 구현하도로 함
        - AnOperation() : Creator에 객체 생성에 관한 메서들 추가해서 전처리, 후처리를 템플릿화 할 수 있음(interface 에 default(java 8) 나 private(java 9) 메서드 추가 가능)
        - FectoryMethod() : 서브 공장 클래스에서 재정의할 객체 생성 추상 메서드
    - ConcreteCreator : 각 서브 공장 클래스들은 이에 맞는 제품 객체를 반환하도록 생성 추상 메소드를 재정의해서, ConcreteCreator 하나당 ConcreteProduct의 생산을 담당함.
    - Product : 제품 구현체를 추상화
    - ConcreteProduct : 제품 구현체
- 사용시기
    - **클래스 인스턴스 생성 로직이 변경되는 경우:**
        - 객체 생성에 필요한 로직이나 알고리즘이 변경되는 경우, 해당 로직을 수정하는 부분을 팩토리 메서드로 캡슐화하여 코드 변경을 최소화할 수 있음.
    - **클래스의 인스턴스 생성이 복잡한 경우:**
        - 객체 생성 과정이 복잡하고 여러 단계를 거칠 때, 팩토리 패턴을 사용하여 생성 로직을 캡슐화하면 코드의 가독성을 높일 수 있음.
    - **객체 생성 로직이 여러 곳에서 중복 사용될 때:**
        - 여러 곳에서 동일한 객체를 생성하는데 중복된 코드가 발생할 때, 팩토리 패턴을 사용하여 중복을 제거하고 코드의 일관성을 유지할 수 있음
    - **클래스 간의 의존성을 최소화하고자 할 때:**
        - 객체를 생성하는 코드와 실제로 사용하는 코드를 분리함으로써, 클래스 간의 의존성을 최소화하고 느슨한 결합을 유지할 수 있음
    - **테스트 용이성을 고려할 때:**
        - 팩토리 패턴을 사용하면 객체 생성 부분을 모듈화하고 테스트하기 수월함. 특히, 테스트용 구현체를 제공하여 유닛 테스트를 수행하기 용이해짐.
- 장점
    - **유연성과 확장성:**
        - 팩토리 메서드 패턴을 사용하면 객체 생성 로직을 서브클래스로 분리할 수 있어서, 새로운 종류의 객체를 추가하거나 변경할 때, 기존 코드를 수정하지 않고도 새로운 클래스를 도입할 수 있음
    - **코드 중복 최소화:**
        - 공통적인 코드를 부모 클래스에 두고, 서브클래스에서는 변경이 필요한 부분만 구현함으로써 코드의 중복을 최소화.
    - **의존성 감소:**
        - 클라이언트 코드가 구체적인 클래스에 직접 의존하지 않고, 추상화된 인터페이스나 추상 클래스를 통해 객체를 생성할 수 있어서, 클라이언트 코드는 생성되는 객체의 구체적인 클래스를 알 필요가 없어짐
    - **템플릿 메서드 패턴과 결합:**
        - 팩토리 메서드 패턴은 종종 템플릿 메서드 패턴과 결합하여 사용되는데, 템플릿 메서드 패턴은 여러 단계로 이뤄진 알고리즘을 정의하고, 일부 단계를 서브클래스에서 구현할 수 있도록 합니다. 이때 팩토리 메서드 패턴을 사용하여 서브클래스에서 구현된 메서드에서 필요한 객체를 생성할 수 있음
    - **테스트 용이성:**
        - 특히 테스트에서는 객체를 대체할 수 있는 모의(Mock) 객체를 만들어서 사용하는 경우가 많은데, 팩토리 메서드 패턴을 통해 객체를 생성하는 부분을 추상화하면, 모의 객체를 주입하기 쉬워짐
    - **생성 로직 캡슐화:**
        - 객체의 생성 로직을 팩토리 메서드에 캡슐화함으로써, 객체 생성에 필요한 복잡한 로직을 감추고 클라이언트 코드에게는 단순한 인터페이스만을 제공할 수 있음.
- 단점
    - **클래스 수의 증가:**
        - 팩토리 메서드 패턴을 사용하면 각각의 구체적인 클래스에 대해 별도의 팩토리 메서드를 제공해야 해서 클래스의 수가 증가할 수 있음.
    - **복잡성 증가:**
        - 팩토리 메서드 패턴은 객체 생성과 관련된 복잡성을 캡슐화하지만, 팩토리 클래스들이 늘어남에 따라 전반적인 시스템의 복잡성이 증가할 수 있음.
    - **추상화의 필요성:**
        - 팩토리 메서드 패턴은 추상화를 통해 유연성을 제공하는데, 이 추상화를 지나치게 사용하면 코드가 불필요하게 복잡해질 수 있음
    - **팩토리 메서드 매개변수의 복잡성:**
        - 객체 생성에 필요한 여러 매개변수가 있는 경우, 이를 처리하는 복잡성이 증가할 수 있습니다.
    - **클라이언트가 구체적인 클래스에 의존:**
        - 팩토리 메서드 패턴을 사용하더라도 클라이언트 코드가 여전히 구체적인 팩토리 클래스에 의존할 수도 있음. 이 경우에는 의존성 주입 등의 기술을 이용하여 이를 완화할 필요가 있음
            - Creator = new ConcreteCreator() 로 의존성을 주입하거나
            - 싱글톤으로 전역에서 하나만 만들거나 하는 방식으로 개선 가능

예) Calendar의 getInstance() : getInstance()를 호출할 때마다 새로운 Calendar 객체가 생성

- Calendar는 Gregorian 형식 Julian 형식이 있는데, 이 두가지 경우를 모두 커버하기 위해 팩토리 메소드 패턴으로 디자인 되었다.


```java
class Ship {
    String name, color, capacity;

    @Override
    public String toString() {
        return String.format("Ship { name: '%s', color: '%s', logo: '%s' }\n", name, color, capacity);
    }
}

class ContainerShip extends Ship {
    ContainerShip(String name, String capacity, String color) {
        this.name = name;
        this.capacity = capacity;
        this.color = color;
    }
}

class OilTankerShip extends Ship {
    OilTankerShip(String name, String capacity, String color) {
        this.name = name;
        this.capacity = capacity;
        this.color = color;
    }
}

interface ShipFactory {

    // java8에 추가된 디폴트 메서드
			//구현코드가 없는 인터페이스에서 공통적으로 구현되야 하는 메서드가 있는 경우 추상클래스의 구현메서드 처럼 기본적인 구현을 가지는 메서드입니다. 
			//기본 구현을 가지고 있다고 해도 실제 구현하는 클래스에서 재정의 할 수 있습니다.
    default Ship orderShip(String email) {
        validate(email);

        Ship ship = createShip(); // 선박 객체 생성

        sendEmailTo(email, ship);

        return ship;
    }

    // 팩토리 추상 메서드
    Ship createShip();
	
    // java9에 추가된 private 메서드
			//인터페이스 내에서만 사용가능한 메서드이고 디폴트 메서드나 정적메서드에 사용하기 위해 작성되는 메서드 입니다. 
			//인터페이스를 구현하는 클래스쪽에서 재정의하거나 사용할 수 없고 디폴트나 정적메서드를 통해서만 사용 가능합니다.
    private void validate(String email) {
        if (email == null) {
            throw new IllegalArgumentException("이메일을 남겨주세요");
        }
    }

    private void sendEmailTo(String email, Ship ship) {
        System.out.println(ship.name + " 다 만들었다고 " + email + "로 메일을 보냈습니다.");
    }
}

class ContainerShipFactory extends ShipFactory {

    // Thread-Safe 한 싱글톤 객체화 - 객체를 생성하는 공장 객체는 여러개 있을 필요성이 없다.
    private ContainerShipFactory() {}
    private static class SingleInstanceHolder {
        private static final ContainerShipFactory INSTANCE = new ContainerShipFactory();
    }
    public static ContainerShipFactory getInstance() {
        return SingleInstanceHolder.INSTANCE;
    }

    @Override
    protected Ship createShip() {
        return new ContainerShip("ContainerShip", "20t", "green");
    }
}

class OilTankerShipFactory extends ShipFactory {

    // Thread-Safe 한 싱글톤 객체화
    private OilTankerShipFactory() {}
    private static class SingleInstanceHolder {
        private static final OilTankerShipFactory INSTANCE = new OilTankerShipFactory();
    }
    public static OilTankerShipFactory getInstance() {
        return OilTankerShipFactory.SingleInstanceHolder.INSTANCE;
    }

    @Override
    protected Ship createShip() {
        return new OilTankerShip("OilTankerShip", "15t", "blue");
    }
}

class Client {
    public static void main(String[] args) {
        // 전용 선박 생산 공장 객체를 통해 선박을 생성
        Ship containerShip = ContainerShipFactory.getInstance().orderShip("test1@test.com");
        System.out.println(containerShip);

        Ship oilTankerShip = OilTankerShipFactory.getInstance().orderShip("test1@test.com");
        System.out.println(oilTankerShip);

			//새로운 선박을 추가하려면 ShipFactory를 구현한 공장 클래스와 선박 클래스만 새로 정의하면 됨.
    }
}
```