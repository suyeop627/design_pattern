### 브릿지패턴
- 개념
    - 하나의 큰 모듈을 추상화와 구현으로 분리하여 각각 독립적으로 변형할 수 있게 하는 패턴
    - 두 개의 계층으로 분리하여 각각을 독립적으로 확장하고 변경할 수 있도록 하는 디자인 패턴
- 구성요소
    - **Abstraction:**
        - 기능의 인터페이스를 정의하는 클래스로, 클라이언트는 이 인터페이스를 통해 기능을 사용
        - Abstraction은 구현 객체에 대한 참조를 가짐
    - **Refined Abstraction:**
        - Abstraction을 확장한 클래스로, 추가적인 기능을 제공하거나 기본 동작을 수정
    - **Implementor:**
        - 추상화에 대한 구현을 정의하는 인터페이스
        - Implementor는 구체적인 구현을 위한 인터페이스를 정의하며, 이를 구현하는 클래스들이 실제 동작을 구현함
    - **Concrete Implementor (구현자의 구현):**
        - Implementor의 실제 구현을 제공하는 클래스

- 사용시기
    - **추상화와 구현이 독립적으로 변해야 하는 경우:**
        - 추상화와 구현 사이의 강한 결합이 원하지 않을 때
        - 추상화와 구현이 변경되더라도 서로 영향을 미치지 않도록 해야 하는 경우
        - 추상화와 구현이 서로 다른 계층 구조를 가지고 있어야 하는 경우
    - **추상화와 구현의 변화 가능성이 높은 경우:**
        - 만약 시스템에서 추상화나 구현 중 하나가 변경될 가능성이 크다면, 브리지 패턴을 사용하여 두 부분을 독립적으로 확장하고 변경할 수 있음
    - **복잡한 계층 구조를 갖는 경우:**
        - 시스템이 복잡한 계층 구조를 갖고 있을 때, 브리지 패턴은 추상화와 구현 사이에 중간 계층을 둠으로써 시스템의 유지보수성과 확장성을 향상시킴
- 장점
    1. **분리된 계층 구조:**
        - 추상화와 구현이 독립적으로 확장 가능하므로 새로운 기능을 추가하거나 구현을 변경할 때 다른 층에 영향을 미치지 않음
    2. **유연성과 확장성:**
        - 새로운 추상화나 구현을 추가하는 것이 용이
        - 기존의 클래스에 영향을 미치지 않으면서 시스템 확장 가능

- 단점
    - **추가적인 클래스 및 인터페이스:**
        - 패턴이 추가적인 클래스와 인터페이스를 도입하기 때문에, 일부 경우에는 코드의 양이 증가할 수 있음
    - **설계 복잡성:**
        - 작은 규모의 프로젝트에서는 과도한 설계 복잡성을 초래할 수 있음

    ```java
    // Implementor
    interface Device {
      void turnOn();
      void turnOff();
    }
    
    // Concrete Implementor 1
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
    // Concrete Implementor 2
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
    
    // Abstraction: 리모컨의 추상화 클래스
    abstract class RemoteControl {
        protected Device device; // Implementor를 참조하는 멤버
    
        public RemoteControl(Device device) {
            this.device = device;
        }
    
        abstract void powerOn(); // Implementor의 메서드를 사용하는 추상 메서드
        abstract void powerOff(); // Implementor의 메서드를 사용하는 추상 메서드
    }
    
    // Refined Abstractions - 추상클래스를 구현해서 실제 기능제공
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
    
    // Refined Abstractions - 추상클래스를 상속받은 추상클래스로, 기능을 추가하여 구현하도록 함
    abstract class AdvancedRemoteControl extends RemoteControl{
      public AdvancedRemoteControl(Device device) {
        super(device);
      }
    
      abstract void extraFunction();
    }
    
    // Refined Abstractions - 추가된 기능을 함께 구현
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
        advancedRemoteForTV.extraFunction(); // Advanced Remote: Extra Function
    
        basicRemoteForAC.powerOn(); // Basic Remote: Power ON \n Air Conditioner is ON
        basicRemoteForAC.powerOff(); // Basic Remote: Power OFF \n Air Conditioner is OFF
    
        advancedRemoteForAC.powerOn(); // Advanced Remote: Power ON \n Air Conditioner is ON
        advancedRemoteForAC.powerOff(); // Advanced Remote: Power OFF \n Air Conditioner is OFF
        advancedRemoteForAC.extraFunction(); // Advanced Remote: Extra Function
      }
    }
    ```