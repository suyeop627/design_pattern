### 데코레이터 패턴
- 개념
    - 기존 클래스의 동작을 수정하지 않고 확장하고자 할 때 사용
    - 이 패턴은 객체에 추가적인 기능을 동적으로 첨가할 수 있도록 함.
    - 데코레이터 패턴을 이용하면 필요한 추가 기능의 조합을 런타임에서 동적으로 생성 가능
    - 구성 요소
        - Component (Interface) : 원본 객체와 장식된 객체 모두를 묶는 역할
        - ConcreteComponent : 원본 객체 (데코레이팅 할 객체)
        - Decorator : 추상화된 장식자 클래스. 원본 객체를 합성(composition)한 wrappee 필드와 인터페이스의 구현 메소드를 가짐.
        - ConcreteDecorator : 구체적인 장식자 클래스. 부모 클래스가 감싸고 있는 하나의 Component를 호출하면서 호출 전/후로 부가적인 로직을 추가할 수 있음
    - 장점
        - **유연성과 확장성:**
            - 기존 코드를 변경하지 않고도 객체의 기능을 확장하거나 수정 가능함
        - **단일 책임 원칙:**
            - 데코레이터 패턴을 사용하면 각 데코레이터가 특정 기능에 대한 책임을 가지며, 단일 책임 원칙(Single Responsibility Principle)을 준수함
        - **구성(composition)을 통한 복합적인 기능 추가:**
            - 데코레이터 패턴은 상속보다는 구성을 통해 기능을 추가하므로, 런타임에 여러 데코레이터를 조합하여 복합적인 기능을 동적으로 구성 가능함
        - **클래스 계층 구조의 평면화:**
            - 데코레이터 패턴을 사용하면 상속을 통한 다양한 서브클래스 생성을 방지하고, 대신 데코레이터를 통해 동적으로 기능을 조립할 수 있어 클래스 계층 구조가 더 평평해짐
    - 단점
        - **복잡성:**
            - 데코레이터 패턴은 많은 수의 작은 클래스들이 생성되어야 할 수 있으며, 이로 인해 코드의 복잡성이 증가
        - **순서에 따른 영향:**
            - 데코레이터들은 특정한 순서로 적용되어야 함.
            - 데코레이터들을 잘못된 순서로 조합하면 예상치 못한 결과가 발생할 수 있음(옷입힐때, 겉옷 입히고 속옷 입힐 수 있음)
        - **객체 수 증가:**
            - 많은 수의 데코레이터가 적용되면 객체 수가 급격히 증가할 수 있고, 이는 곧 메모리 사용량 증가와 성능 저하로 이어질 수도 있음.
        - **인터페이스 구현의 어려움:**
            - 기본 컴포넌트와 데코레이터들이 동일한 인터페이스를 공유하도록 설계해야 하므로, 설계가 복잡해질 수 있음

    - 사용시기
        - **클래스에 새로운 기능을 추가하거나 변경이 필요한 경우:**
            - 상속을 통해 새로운 기능을 추가하려면 서브클래스를 만들어야 하는데,  데코레이터 패턴을 사용하면 실행 중에 객체에 새로운 책임을 동적으로 추가할 수 있음
        - **클래스의 변경이 불가능하거나 비효율적인 경우:**
            - 기존 클래스를 수정하면 기존 코드에 영향을 미칠 수 있음.
            - 특히, 소스 코드가 제공되지 않는 외부 라이브러리나 서드파티 클래스에 새로운 기능을 추가해야 하는 경우에 데코레이터 패턴이 활용됨
        - **여러 개의 조합 가능한 기능이 필요한 경우:**
            - 데코레이터 패턴은 여러 개의 데코레이터를 조합하여 다양한 기능을 동적으로 확장할 수 있음
            - 상속을 통해 만들어진 하나의 서브클래스로는 달성하기 어려운 유연성을 제공
        - **기능의 조합이 런타임에 결정될 경우:**
            - 런타임에서 어떤 기능을 추가할지 결정해야 하는 경우에 적합
            - 정적인 상속 구조로는 어려운 동적인 확장을 가능하게 함.

    - 순서에 맞게 ConcreteDecorator 를 사용할 수 있지만, ConcreteDecorator 가 null이면 다른 동작을 취하게 함으로써 순서에 의존성을 줄일 수 있음
        - 예시
            - 빈 생성자 추가(추상 데코레이터 클래스에도 추가해야됨)
            - 데코레이터 생성 시, 기본 구현 클래스가 없을 경우의 분기로 로직을 구현하여, 기본 구현클래스없이 독립적으로 활용 가능하도록 구현할 수도 있음

            ```java
            // ConcreteDecorator
            class CocoaDecorator extends CoffeeDecorator {
            
            		public CocoaDecorator(){}
            
                public CocoaDecorator (Coffee decoratedCoffee) {
                    super(decoratedCoffee);
                }
            
                @Override
                public String getDescription() {
            				String description = decoratedCoffee==null ? "water" : super.getDescription(); //SimpleCoffe 필드가 비어있는 경우 구분해서 처리
            
                    return description  + ", with Cocoa";
                }
            
                @Override
                public double cost() {
            				double cost = decoratedCoffee==null ? 0.5 : super.cost();  //SimpleCoffe 필드가 비어있는 경우 구분해서 처리
            
                    return cost  + 1.2; // 코코아 추가 비용
                }
            }
            ```


    ```java
    // Component: 기본 인터페이스나 추상 클래스
    public interface Pizza {
      String getDescription();
    
      double getCost();
    }
    
    // ConcreteComponent: 기본 구현 클래스
    public class PlainPizza implements Pizza{
      @Override
      public String getDescription() {
        return "Pizza";
      }
      @Override
      public double getCost() {
        return 12_000;
      }
    }
    
    // Decorator: 데코레이터의 기반 클래스
    public abstract class PizzaToppingDecorator implements Pizza{
      protected Pizza decoratedPizza;
      public PizzaToppingDecorator(Pizza pizza){
        this.decoratedPizza = pizza;
      }
      @Override
      public String getDescription() {
        return decoratedPizza.getDescription();
      }
    
      @Override
      public double getCost() {
        return decoratedPizza.getCost();
      }
    }
    
    // ConcreteDecorator1: 실제 데코레이터 클래스 1
    public class MushroomDecorator extends PizzaToppingDecorator{
      public MushroomDecorator(Pizza pizza) {
        super(pizza);
      }
      @Override
      public String getDescription() {
        return super.getDescription() + ", with Mushrooms";
      }
      @Override
      public double getCost() {
        return super.getCost()+3_000;
      }
    }
    
    // ConcreteDecorator2: 실제 데코레이터 클래스 2
    public class PepperoniDecorator extends PizzaToppingDecorator{
      public PepperoniDecorator(Pizza pizza) {
        super(pizza);
      }
    
      @Override
      public String getDescription() {
        return super.getDescription() + ", with Pepperoni";
      }
    
      @Override
      public double getCost() {
        return super.getCost()+2_000;
      }
    }
    // ConcreteDecorator3: 실제 데코레이터 클래스 3
    public class ShrimpDecorator extends PizzaToppingDecorator{
      public ShrimpDecorator(Pizza pizza) {
        super(pizza);
      }
      @Override
      public String getDescription() {
        return super.getDescription() + ", with Shrimp";
      }
    
      @Override
      public double getCost() {
        return super.getCost()+5_000;
      }
    }
    
    //클라이언트 코드
    public class Client {
      public static void main(String[] args) {
        Pizza pizza = new PlainPizza();
        printOrder(pizza);
        //페퍼로니
        Pizza pepperoniPizza = new PepperoniDecorator(new PlainPizza());
        printOrder(pepperoniPizza);
    
        //페퍼로니 + 버섯
        Pizza pepperoniMushroomPizza = new PepperoniDecorator(new MushroomDecorator(new PlainPizza()));
        printOrder(pepperoniMushroomPizza);
    
        //페퍼로니 + 버섯 + 새우
        Pizza allToppingPizza = new ShrimpDecorator(new PepperoniDecorator(new MushroomDecorator(new PlainPizza())));
        printOrder(allToppingPizza);
    
      }
    
      private static void printOrder(Pizza pizza){
        System.out.printf("You ordered %s and it's %s won%n", pizza.getDescription(), pizza.getCost());
      }
    }
    ```