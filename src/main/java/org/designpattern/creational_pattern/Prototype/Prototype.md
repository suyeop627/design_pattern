### 프로토 타입 패턴
- 개념
    - 객체의 클래스를 지정하지 않고, 기존 객체를 복제해서 생성할 수 있게 하는 패턴
    - 기존 인스턴스를 복제하여 새로운 인스턴스를 생성하는 방법
    - 객체 생성 비용이 높은 경우, 기존 객체를 복제하여 새로운 객체를 생성하는 패턴
    - 객체를 생성하는 과정을 피하고, 기존 객체를 복제하여 새로운 객체를 만들어내기 때문에 성능 향상을 기대할 수 있음
    - 매번 DB나 api호출해서 객체 생성하는게 아니라 기존 객체를 복사해서 활용할 수 있도록 할 수 있음
- 구성요소
    - **Prototype (프로토타입):** 복제를 위한 인터페이스를 정의
    - **ConcretePrototype (구체적인 프로토타입):** Prototype 인터페이스를 구현하며, 자신을 복제하는 메서드를 제공
    - **Client (클라이언트):** 프로토타입 패턴을 사용하는 클라이언트는 기존 객체를 복제하여 새로운 객체를 생성
- 장점
    - **객체 생성 비용 감소**:
        - 새로운 객체를 생성하는 대신 기존 객체를 복제하여 사용하기 때문에 객체 생성 비용이 감소
        - 특히 복잡한 객체의 생성이나 초기화가 많은 경우에 이점
    - **런타임에 동적인 객체 생성**:
        - 객체의 클래스가 런타임에 동적으로 변경
        - 객체를 복제하는 방식으로 새로운 객체를 생성하고, 변경된 클래스의 인스턴스를 얻을 수 있음
    - **복잡한 객체 생성을 쉽게 처리**:
        - 복잡한 객체를 복제하여 생성할 수 있기 때문에 객체의 생성 과정이 복잡한 경우에도 비교적 간단하게 처리
- 단점
    - **깊은 복사 어려움**:
        - 프로토타입 패턴은 객체를 복제하지만, 객체 내부에 참조 타입이 있는 경우 깊은 복사(deep copy)를 수행하기 어려움.
            - 객체를 복제해서 새로운 메모리에 할당하고, 기본자료형도 복제해서 새로 만듦
            - 하지만 참조변수가 필드에 있을 경우, 원본 객체의 참조변수의 메모리 주소를 복제된 객체의 참조변수도 참조함.(같은 주소를 가리킴)
        - 이 경우 참조된 객체들도 복제되어야 하는데, 이는 추가적인 구현 필요
    - **Clone 메서드 구현 필요**:
        - 복제를 위해 **`clone`** 메서드를 구현해야 함.
        - 이를 위해서는 **`Cloneable`** 인터페이스를 구현하고, **`clone`** 메서드를 정의해야 하는데, 복잡성을 증가시킬 수 있음
    - **객체 상태 변화에 대한 주의 필요**:
        - 복제된 객체는 원본 객체의 상태를 공유하기 때문에, 한 객체의 상태가 변경되면 다른 객체의 상태도 영향을 받을 수 있음

- Cloneable
    - Java 언어에서는 인스턴스의 복사를 실행하는 도구로 clone 메소드가 있음
    - clone 메소드를 실행할 경우에는 복사 대상이 되는 클래스는 java.lang.Cloneable 인터페이스를 구현해야함
        - clone 메서드가 선언된 곳이 Clneable이 아닌 Object이며 protected 제어자를 가짐.
        - Cloneable 인터페이스는 단지  'clone 에 의해 복사할 수 있다' 라는 표시로서  marker interface 로 사용됨.
        - 따라서 Cloneable을 구현하는 것만으로 clone 메서드를 호출할 수 없음
    - Cloneable 인터페이스를 구현한 클래스의 인스턴스는 clone 메소드를 오버라이딩해서 호출하면 복사됨
    - 만약 Cloneable 인터페이스를 구현하지 않는 클래스가 clone 메소드를 호출하면 예외 CloneNotSupportedException 이 발생
    - clone()메서드는 기본적으로 얕은복사를 실행하므로, 깊은복사를 할 경우에는 clone 메서드를 오버라이드해서 깊은복사를 정의해야함.

- Object.clone()
    - 인스턴스 객체의 복제를 위한 메소드로, 해당 인스턴스를 복제하여 새로운 인스턴스를 생성해 그 참조값을 반환함
    - 이러한 clone() 메소드를 사용하기 위해서는 오버라이딩을 해야 되는데, 이때 데이터의 보호를 이유로 Cloneable 인터페이스를 구현한 클래스의 인스턴스만이 사용할 수 있음
    - Object 클래스의clone() 메소드는 기본으로 protected 접근 권한을 갖고 있기 때문에, 상속하여 메소드를 public 접근제어자로 재정의하여 어디서나 복제가 가능하도록 해야 함

    ```java
    // 객체 복사 메소드를 사용하기 위해서는 Cloneable 인터페이스를 구현해서 clone을 재정의 해야함
    class Person implements Cloneable {
        // ...
    	
        // clone 메서드를 오버라이딩
        public Object clone() throws CloneNotSupportedException { // CloneNotSupportedException는 checked exception 이라 반드시 예외처리
    		// 기본적으로 부모의 clone을 그대로 불러와 반환
    		// 만약 참조변수를 멤버로 가진 객체일 경우, 해당 참조변수는 얕은 복사로 처리됨
    
            return super.clone(); 
    
        }
    }
    //복사한 객체는 다른 주소값을 갖지만,
    //var1 = CopyTest$Var1@22f71333
    //var1_copied = CopyTest$Var1@6aaa5eb0
    
    //각 객체의 참조변수인 멤버는 같은 주소를 참조함.
    //var1.refVar = CopyTest$RefVar@13969fbe
    //var1_copied.refVar = CopyTest$RefVar@13969fbe
    ```

    - 만약 모든 참조변수 멤버에 대해 깊은복사를 해야할 경우, 재귀적으로 각 멤버를 복사해야함.

    ```java
    public class YourObject implements Cloneable {
        private int primitiveField;
        private AnotherObject referenceField;
    
        // constructor, getters, setters 등 생략
    
        @Override
        public Object clone() {
            try {
                YourObject clonedObject = (YourObject) super.clone();
                // 기본 타입 필드는 얕은 복사
                clonedObject.primitiveField = this.primitiveField;
    
                // 참조 타입 필드는 재귀적으로 깊은 복사
                clonedObject.referenceField = (AnotherObject) this.referenceField.clone();
    
                return clonedObject;
            } catch (CloneNotSupportedException e) {
                // 예외 처리
                return null;
            }
        }
    }
    ```


```java
import java.util.HashMap;
import java.util.Map;

// 프로토타입 인터페이스
interface Animal extends Cloneable {
    Animal clone();
    void makeSound();
}

// 구체적인 프로토타입 클래스들
class Sheep implements Animal {
    public Sheep() {
        System.out.println("양이 만들어졌습니다.");
    }

    @Override
    public Animal clone() {
        try {
            return (Sheep) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public void makeSound() {
        System.out.println("메에 메에");
    }
}

class Dog implements Animal {
    public Dog() {
        System.out.println("개가 만들어졌습니다.");
    }

    @Override
    public Animal clone() {
        try {
            return (Dog) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public void makeSound() {
        System.out.println("멍멍");
    }
}

// 프로토타입 매니저
class AnimalPrototypeManager {
    private Map<String, Animal> prototypes;

    public AnimalPrototypeManager() {
        prototypes = new HashMap<>();
    }

    public void addPrototype(String key, Animal prototype) {
        prototypes.put(key, prototype);
    }

    public Animal getPrototype(String key) {
        return prototypes.get(key).clone();
    }
}

// 클라이언트 코드
public class PrototypePatternExample {
    public static void main(String[] args) {
        AnimalPrototypeManager manager = new AnimalPrototypeManager();

        Sheep sheep = new Sheep();
        Dog dog = new Dog();

        manager.addPrototype("양", sheep);
        manager.addPrototype("개", dog);

        Animal clonedSheep = manager.getPrototype("양");
        Animal clonedDog = manager.getPrototype("개");

        clonedSheep.makeSound();
        clonedDog.makeSound();
    }
}
```