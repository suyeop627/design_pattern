### 추상 팩토리 패턴
- 개념
    - 관련성 있는 여러 개의 객체를 생성하기 위한 인터페이스를 제공하는 패턴
    - 이 패턴은 구체적인 클래스의 인스턴스화를 피하고, 관련된 객체들의 **집합**을 생성하는 인터페이스를 정의함.
    - 연관성이 있는 **객체 군**이 여러개 있을 경우 이들을 묶어 추상화하고, 어떤 구체적인 상황이 주어지면 팩토리 객체에서 집합으로 묶은 객체 군을 구현화 하는 생성 패턴
    - 클라이언트에서 특정 객체을 사용할때 팩토리 클래스만을 참조하여 특정 객체에 대한 구현부를 감추어 역할과 구현을 분리시킬 수 있음
    - 즉, 제품 '군' 집합을 타입 별로 찍어낼 수 있음.
- 구성
    1. **AbstractFactory :**
        - 관련된 객체의 집합을 생성하기 위한 인터페이스를 제공
        - 최상위 공장 클래스. 여러개의 제품들을 생성하는 여러 메소드들을 추상화함.
    2. **ConcreteFactory:**
        - AbstractFactory를 구현한 클래스로, 실제로 객체를 생성하는 책임을 가짐.
        - 서브 공장 클래스들은 타입에 맞는 제품 객체를 반환하도록 메소드들을 재정의하여, 담당하는 제품 객체를 생성함
    3. **AbstractProduct :**
        - 생성될 객체의 인터페이스
        - 각 타입의 제품들을 추상화한 인터페이스
    4. **ConcreteProduct :**
        - AbstractProduct를 구현한 클래스로, 실제로 생성되는 객체
        - 각 타입의 제품 구현체들로, 팩토리 객체로부터 생성됨.
    5. **Client (클라이언트):**
        - AbstractFactory와 AbstractProduct를 사용하여 객체를 생성

- 사용시기
    - **여러 제품군이 존재하는 경우:**
        - 시스템이 여러 제품군으로 나눠져 있고, 각 제품군은 함께 사용되어야 할 때 추상 팩토리 패턴을 고려할 수 있음
        - 예를 들어, GUI 컴포넌트를 생성하는데 Windows용과 macOS용 GUI 컴포넌트가 필요한 경우 추상 팩토리 패턴을 사용하여 각각의 집합별로 제품을 생성할 수 있음
    - **제품군이 확장 가능하고 제품이나 제품군의 추가 및 변경이 빈번한 경우:**
        - 새로운 팩토리를 추가하거나 기존의 팩토리를 수정하여 유연하게 대응할 수 있음
    - **단일 책임 원칙(Single Responsibility Principle)을 적용하고자 하는 경우:**
        - 각각의 팩토리는 특정한 제품군을 생성하므로, 단일 책임 원칙을 적용하여 각 팩토리가 하나의 역할에만 집중하도록 함
    - **클라이언트 코드가 제품 구체 클래스에 직접 의존하지 않아야 하는 경우:**
        - 클라이언트 코드가 특정 제품의 구체 클래스에 의존하지 않고, 추상화된 인터페이스를 통해 제품을 사용하고자 할 때 추상 팩토리 패턴을 적용할 수 있음

- 장점
    - 객체를 생성하는 코드를 분리하여 클라이언트 코드와 결합도를 낮출 수 있음
    - 제품 군을 쉽게 대체 또는 확장할 수 있음.
    - windows, macOS용만 사용하다가 리눅스용 만들려면, 각 제품군을 상속받은 리눅스용 제품과 리눅스 팩토리를 추가해서 사용하면 됨.
- 단점
    - 각 구현체마다 팩토리 객체들을 모두 구현해주어야 하기 때문에 객체가 늘어날때 마다 클래스가 증가하여 코드의 복잡성이 증가함. (팩토리 패턴의 공통적인 문제점)
    - 기존 추상 팩토리의 세부사항이 변경되면 모든 팩토리에 대한 수정이 필요해지며, 추상 팩토리와 모든 서브클래스의 수정을 야기함.

- vs 팩토리 메서드 패턴
    - 공통점
        - 둘다 팩토리 객체를 통해 구체적인 타입을 감추고 객체 생성에 관여하는 패턴임
        - 또한 공장 클래스가 제품 클래스를 각각 나뉘어 느슨한 결합 구조를 구성하는 모습 역시 유사함
    - 차이점
        - 팩토리 메서드 패턴은 객체 생성 이후 해야 할 일의 공통점을 정의하는데 초점
        - 추상 팩토리 패턴은 생성해야 할 객체 집합 군의 공통점에 초점
    - 이 둘을 유사점과 차이점을 조합해서 복합 패턴을 구성하는 것도 가능함.
        - 추상 팩토리와 팩토리 메서드 패턴 둘을 조합하게 된다면, 여러 타입의 객체 군을 생성하면서 동시에 템플릿 메서드를 통해 전처리, 후처리 작업을 해주는 것이 가능


|                  | **팩토리 메서드 패턴**                                                                                                                                  | **추상 팩토리 패턴**                                          |
| ---------------- |-------------------------------------------------------------------------------------------------------------------------------------------------| ------------------------------------------------------------ |
| **공통점**       | - 객체 생성 과정을 추상화한 인터페이스를 제공.<br/> - 객체 생성을 캡슐화함으로써 구체적인 타입을 감추고 느슨한 결합 구조를 표방                                                                    |
| **차이점**       | - 구체적인 객체 생성과정을 하위 또는 구체적인 클래스로 옮기는 것이 목적<br>- 한 Factory당 한 종류의 객체 생성<br>- 메소드 레벨에서 포커스를 맞춤으로써, 클라이언트의 ConcreteProduct 인스턴스의 생성 및 구성에 대한 의존을 감소 | - 관련 있는 여러 객체를 구체적인 클래스에 의존하지 않고 만들 수 있게 해주는 것이 목적<br>- 지원한 Factory에서 서로 연관된 여러 종류의 객체 생성을 지원. (제품군 생성 지원)<br>- 클래스(Factory) 레벨에서 포커스를 맞춤으로써, 클라이언트의 ConcreteProduct 인스턴스 군의 생성 및 구성에 대한 의존을 감소 |



```jsx
// 제품군 1
interface Button {
    void click();
}

// 제품군 2
interface Checkbox {
    void check();
}

// 추상 팩토리 인터페이스
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 제품군 1의 구현체 - 윈도우 용
class WindowsButton implements Button {
    @Override
    public void click() {
        System.out.println("Windows button clicked");
    }
}

// 제품군 2의 구현체
class WindowsCheckbox implements Checkbox {
    @Override
    public void check() {
        System.out.println("Windows checkbox checked");
    }
}
// 다른 제품군 1의 구현체 - 맥 용
class MacOSButton implements Button {
    @Override
    public void click() {
        System.out.println("MacOS button clicked");
    }
}

// 다른 제품군 2의 구현체
class MacOSCheckbox implements Checkbox {
    @Override
    public void check() {
        System.out.println("MacOS checkbox checked");
    }
}

// 추상 팩토리의 구현체 - 윈도우 용
class WindowsGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// 다른 추상 팩토리의 구현체 - 맥 용
class MacOSGUIFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}

public class Client {
    public static void main(String[] args) {
        // Windows 플랫폼에 맞는 팩토리 선택
        GUIFactory windowsFactory = new WindowsGUIFactory();

        // Windows 팩토리를 사용하여 버튼과 체크박스 생성
        Button windowsButton = windowsFactory.createButton();
        Checkbox windowsCheckbox = windowsFactory.createCheckbox();

        // 생성된 객체 사용
        windowsButton.click();
        windowsCheckbox.check();

        System.out.println();

        // MacOS 플랫폼에 맞는 팩토리 선택
        GUIFactory macOSFactory = new MacOSGUIFactory();

        // MacOS 팩토리를 사용하여 버튼과 체크박스 생성
        Button macOSButton = macOSFactory.createButton();
        Checkbox macOSCheckbox = macOSFactory.createCheckbox();

        // 생성된 객체 사용
        macOSButton.click();
        macOSCheckbox.check();
    }
}
```