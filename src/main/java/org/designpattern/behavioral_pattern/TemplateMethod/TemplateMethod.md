### 템플릿 메서드 패턴
- 개념
    - 알고리즘의 구조를 메서드에 정의하고 하위 클래스에서 알고리즘의 구조 변경 없이 알고리즘의 일부 단계를 재정의할 수 있게 하는 패턴
    - 전체 알고리즘의 구조를 변경하지 않으면서 알고리즘의 특정 단계를 서브클래스에서 재정의할 수 있게 함
    - 여러 클래스에서 공통으로 사용하는 메서드를 템플릿화 하여 상위 클래스에서 정의하고, 하위 클래스마다 세부 동작 사항을 다르게 구현하는 패턴
    - 알고리즘의 뼈대를 맞추는 것에 초점을 둠

- 구성 요소
    - **AbstractClass (추상 클래스):**
        - 알고리즘의 구조를 정의하는 클래스로, 템플릿 메서드를 포함
        - 템플릿 메서드는 알고리즘의 골격을 정의하고, 여러 단계 중 일부를 추상 메서드로 선언하여 구현을 서브클래스에게 위임
    - **ConcreteClass (구상 클래스):**
        - 추상 클래스에서 정의한 템플릿 메서드의 일부 단계를 구체적으로 구현하는 클래스
        - 추상 클래스의 추상 메서드들을 구현하여 알고리즘을 완성시킴
- 사용시기
    - **알고리즘의 구조가 공통되지만 세부 단계가 서브클래스에 따라 다를 때:**
        - 여러 클래스에서 공통적인 알고리즘을 유지하면서 각 클래스에서 세부 단계를 재정의하려는 경우에 사용
    - **코드 재사용과 유지보수가 중요한 경우:**
        - 템플릿 메서드 패턴은 코드의 재사용성을 높이고, 유지보수를 용이하게 함
        - 공통된 부분을 부모 클래스에서 관리하므로, 수정이 필요한 경우 부모 클래스만 수정하면 됨
    - **알고리즘의 일부 단계를 변경하고자 할 때:**
        - 템플릿 메서드 패턴을 사용하면 알고리즘의 구조를 변경하지 않으면서 일부 단계를 하위 클래스에서 재정의할 수 있음
        - 특정부분만 재정의해서, 알고리즘의 다른 부분에 발생하는 변경 사항의 영향을 덜 받도록 한다.
- 장점
    - **코드 재사용과 중복 최소화:**
        - 공통된 알고리즘 구조를 부모 클래스에서 정의하므로, 코드 중복을 최소화하고 재사용성을 높임
    - **확장성:**
        - 새로운 알고리즘을 추가하거나 기존 알고리즘을 수정할 때, 부모 클래스를 변경하지 않고도 서브클래스에서 알고리즘의 일부를 수정할 수 있음
    - **유지보수 용이성:**
        - 공통된 부분이 한 곳에 모여 있어서 유지보수가 용이함
- 단점
    - **추상 메서드 구현 강제:**
        - 템플릿 메서드 패턴을 사용하면 추상 메서드를 하위 클래스에서 반드시 구현해야 해서 번거롭거나 불편할 수 있음
    - **단일 상속만 지원:**
        - 템플릿 메서드 패턴은 단일 상속만 지원하므로 이미 다른 클래스를 상속하고 있는 경우 사용이 어려움
        - 이러한 경우에는 인터페이스와 조합하여 사용해야함
    - **구조의 고정성:**
        - 템플릿 메서드 패턴을 사용하면 알고리즘의 구조가 고정됨
        - 부모 클래스에서 정의한 구조를 변경하려면 모든 서브클래스에서 변경이 필요하므로, 유연성이 제한될 수 있음
    - **복잡성 증가:**
        - 클래스 계층 구조가 복잡해짐
        - 특히 많은 수의 서브클래스가 있을 경우 유지보수가 어려워질 수도 있음
        - 상위 클래스를 수정할 때, 모든 서브 클래스의 수정이 필요 할 수도 있음

- Hook 메서드
    - 추상 클래스에서 선언되지만 기본 구현이 제공되어 있어 하위 클래스에서 선택적으로 오버라이딩할 수 있는 메서드(추상 메서드만 강제로 구현해야함)
        - 자식 클래스에서 선택적으로 오버라이딩을 할 수 있게 함
    - 훅(hook) 메소드는 부모의 템플릿 메서드의 영향이나 순서를 제어하고 싶을때 사용되기도 함
    - 위 도식에서, step2() 메서드의 참, 거짓에 따라 분기를 지정할 수 있음
        - step2()를 기본적으로 true를 반환하게해서 step3()을 실행시키고, 특정 알고리즘에서는 조건에따라 false로 해서 step3() 대신 step4()를 실행시킬 수 있음
    - 자식 클래스에서 좀더 유연하게 템플릿 메서드의 알고리즘 로직을 다양화 할 수 있음

    ```java
    
    // AbstractClass: 주문 처리 알고리즘의 구조를 정의하는 추상 클래스
    abstract class OrderProcessor {
      // Template Method: 주문 처리 알고리즘의 전체 구조를 정의
      public final void processOrder(List<Item> items, OrderType orderType) {
        validateOrder();
        int totalPrice = calculateTotal(items);
        applyDiscount(totalPrice);
        if (isExpressDelivery(orderType)) {
          performExpressDelivery();
        }
        placeOrder(orderType);
      }
    
      protected abstract void validateOrder();
    
      protected int calculateTotal(List<Item> items) {
        int totalPrice = items.stream()
            .map(Item::getPrice)
            .mapToInt(Integer::intValue)
            .sum();
        System.out.printf("Total amount calculated for in-store order: %d%n", totalPrice);
        return totalPrice;
      }
      // Hook Method: 서브클래스에서 선택적으로 재정의 가능한 메서드
      protected void applyDiscount(int totalPrice) {
        System.out.println("No discount applied");
      }
      // Hook Method: 서브클래스에서 선택적으로 재정의 가능한 메서드로, 구현을 통해 분기점을 생성할 수 있음
      protected boolean isExpressDelivery(OrderType orderType) {
        return false;
      }
    
      protected void performExpressDelivery() {
        System.out.println("Express delivery selected");
      }
    
      protected abstract void placeOrder(OrderType orderType);
    }
    
    // ConcreteClass: Online 주문 처리를 특화시킨 클래스
    public class OnlineOrderProcessor extends OrderProcessor{
      @Override
      protected void validateOrder() {
        System.out.println("Validating online order");
      }
      // Hook Method를 재정의하여 Online 주문에 맞는 할인 적용
      @Override
      protected void applyDiscount(int totalPrice) {
        totalPrice *=0.95;
        System.out.printf("Discount applied for online order. Total amount after discount: %d%n", totalPrice);
      }
      // Hook Method를 재정의하여 Online Express 주문 여부 확인
      @Override
      protected boolean isExpressDelivery(OrderType orderType) {
        return orderType==OrderType.ONLINE_EXPRESS;
      }
    
      @Override
      protected void placeOrder(OrderType orderType) {
        System.out.println("Place online order");
      }
    }
    
    // ConcreteClass: In-store 주문 처리를 특화시킨 클래스
    public class InStoreOrderProcessor extends OrderProcessor{
      @Override
      protected void validateOrder() {
        System.out.println("Validating in-store order");
      }
    
      @Override
      protected void placeOrder(OrderType orderType) {
        System.out.println("Placing in-store order");
      }
    }
    
    public enum Item {
      BASIC_PIZZA(12_000), CHEESE_PIZZA(15_000);
      private final int price;
      Item(int price) {
        this.price = price;
      }
      public int getPrice(){
        return price;
      }
    }
    
    public enum OrderType {
      ONLINE, IN_STORE, ONLINE_EXPRESS
    }
    
    public class Client {
        public static void main(String[] args) {
    
          System.out.println("------------------online-order ");
          List<Item> onlineOrderList = List.of(Item.BASIC_PIZZA, Item.BASIC_PIZZA);
          OrderProcessor onlineOrderProcessor = new OnlineOrderProcessor();
          onlineOrderProcessor.processOrder(onlineOrderList, OrderType.ONLINE);
    
          System.out.println("------------------online-order with express delivery");
          List<Item> onlineExpressOrderList = List.of(Item.CHEESE_PIZZA, Item.CHEESE_PIZZA, Item.BASIC_PIZZA);
          onlineOrderProcessor.processOrder(onlineExpressOrderList, OrderType.ONLINE_EXPRESS);
    
          System.out.println("------------------in-store order");
          List<Item> inStoreOrderList = List.of(Item.BASIC_PIZZA, Item.BASIC_PIZZA, Item.BASIC_PIZZA);
          OrderProcessor inStoreOrderProcessor = new InStoreOrderProcessor();
          inStoreOrderProcessor.processOrder(inStoreOrderList, OrderType.IN_STORE);
        }
    }
    ```

- vs 전략패턴
    - 전략패턴
        - 알고리즘을 정의하고 각각의 알고리즘을 캡슐화하여 서로 교환 가능하게 만드는 패턴
        - 런타임에 알고리즘을 변경하고자 할 때 사용
        - 객체 간의 의존성을 줄이고, 코드의 재사용성을 높임
    - 템플릿 메서드 패턴
        - 알고리즘의 구조를 정의하고 일부 단계를 서브클래스로 미루는 패턴
        - 서브클래스에서 알고리즘의 일부를 변경하고자 할 때 사용
        - 상위 클래스에서 알고리즘의 구조를 정의하고, 일부 단계를 서브클래스에게 위임
        - 상속을 통해 코드를 재사용하며, 서브클래스에서 일부 단계를 변경
    - 비교
        - 공통점
            - 전략 패턴과 템플릿 메서드 패턴은 알고리즘을 때에 따라 적용한다는 컨셉으로써, 둘이 유사한 점이 있음
            - 전략 및 템플릿 메서드 패턴은 OCP를 충족하고 코드를 변경하지 않고 쉽게 확장할 수 있도록 하는 데 사용가능 함
        - 차이점
            - 전략 패턴은 합성(composition)을, 템플릿 메서드 패턴은 상속(inheritance)을 통해 문제를 해결함
            - 전략 패턴은 클라이언트와 객체 간의 결합이 느슨하지만,  템플릿 메서드 패턴에서는 두 모듈이 더 밀접하게 결합(결합도가 높으면 안좋음)
            - 전략 패턴에서는 대부분 인터페이스를 사용하지만, 템플릿 메서드 패턴에서는 주로 추상 클래스나 구체적인 클래스를 사용
            - 전략 패턴에서는 전체 전략 알고리즘을 변경할 수 있지만, 템플릿 메서드 패턴에서는 알고리즘의 일부만 변경되고 나머지는 변경되지 않은 상태로 유지(템플릿에 종속됨