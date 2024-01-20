### 퍼사드
- facade
    1. 명사 (건물의) 정면[앞면]
    2. 명사 (실제와는 다른) 표면, 허울
- 개념
    - 사용하기 복잡한 클래스 라이브러리에 대해 사용하기 편하게 간편한 인터페이스(API)를 구성하기 위한 구조 패턴
    - 라이브러리나 복잡한 서브시스템을 단순화된 인터페이스로 제공하는 패턴
    - 퍼사드는 클라이언트와 서브시스템 간의 인터페이스 역할을 하며, 클라이언트가 복잡한 서브시스템을 직접 다루지 않도록 도와줌
    - 예를들어 라이브러리의 각 클래스와 메서드들이 어떤 목적의 동작인지 이해하기 어려워 바로 가져다 쓰기에는 난이도가 높을때, 이에 대한 적절한 네이밍과 정리를 통해 사용자로 하여금 쉽게 라이브러리를 다룰수 있도록 인터페이스를 만드는 것임.
- 구성
    1. **Facade:**
        - 클라이언트와 서브시스템 간의 중재자 역할을 하는 클래스
        - 클라이언트에게 단일화된 간단한 인터페이스를 제공하며, 내부적으로 서브시스템의 복잡한 로직을 처리
    2. **Additional Facade:**
        - 추가 퍼사드 클래스를 만드는 것은 단일 퍼사드에 관련 없는 기능들이 섞이는 것을 방지하고 복잡한 구조를 피하기 위한 방법
        - 단일 퍼사드 클래스가 지나치게 복잡해지거나 관련 없는 기능들이 추가되기 시작하면, 특정 기능 집합에 중점을 둔 추가 퍼사드를 만드는 것이 유용함
        - 다른 퍼사드에서 사용할 수도 있고 클라이언트에서 직접 접근할 수도 있음
    3. **Subsystems:**
        - 퍼사드가 감싸고 있는 복잡한 로직이 있는 클래스나 그룹
        - 서브시스템은 퍼사드가 제공하는 간단한 인터페이스를 통해서 사용되며, 직접 사용되지는 않음
    4. **Client:**
        - 서브시스템을 사용하는 객체로, 퍼사드를 통해 간접적으로 서브시스템과 상호 작용
       

- 사용시기
    - 시스템이 너무 복잡할때
    - 간단한 인터페이스를 통해 복잡한 시스템을 접근하도록 하고 싶을때
    - 시스템을 사용하고 있는 외부와 결합도가 너무 높을 때 의존성 낮추기 위한 경우
- 패턴 장점
    - **단순화된 클라이언트 코드:**
        - 퍼사드는 복잡한 서브시스템을 감춤으로써 클라이언트 코드를 단순화함
        - 클라이언트는 단일 인터페이스를 통해 퍼사드에 접근하므로 서브시스템의 복잡성을 알 필요가 없음
    - **모듈성과 재사용성:**
        - 퍼사드 패턴은 서브시스템의 구현을 캡슐화하므로, 이 구현을 다른 클라이언트에서 쉽게 재사용할 수 있음
    - **유지보수 용이성:**
        - 서브시스템의 변경이나 업그레이드가 필요한 경우 퍼사드를 통해 해당 변경을 처리하기 쉬워짐
        - 클라이언트 코드를 수정할 필요 없이 퍼사드 내부에서 변경 사항을 처리
    - **시스템 확장성:**
        - 새로운 서브시스템을 도입하거나 기존 서브시스템을 변경할 때 퍼사드를 수정하면 되므로 시스템에 쉽게 확장성을 부여할 수 있음
    - 의존성 감소
        - 중간에 매개체 역할을 해주는 퍼사드 객체가 있기 때문에 실제 내부 로직이 어떻게 변경이 되더라도 상관이 없어지므로 의존성이 감소됨
- 패턴 단점
    - **유연성 감소:**
        - 퍼사드는 특정 서브시스템에 대한 단일 인터페이스를 제공하므로, 서브시스템의 개별적인 기능에 접근이 제한될 수 있음
    - **퍼사드 클래스의 의존성:**
        - 오사용 시, 퍼사드가 앱의 모든 클래스에 결합된 God 객체가 될 수 있다
        - 퍼사드 클래스 자체가 서브시스템에 대한 의존성을 가지게 되어 의존성을 완전히는 피할 수는 없음.
    - **클래스 수 증가:**
        - 클래스 수의 증가로, 시스템의 복잡성이 증가할 수 있음

```java
// 서브시스템 - 주문 처리
class OrderProcessing {
  public void processOrder(String productId, int quantity) {
		// 주문 처리 로직 수행
    System.out.println("Processing order for product: " + productId + ", Quantity: " + quantity);
  }
}

// 서브시스템 - 결제 처리
class PaymentProcessor {
  public void processPayment(String paymentMethod, double amount) {
	  // 결제 처리 로직 수행
    System.out.println("Processing payment of " + amount + " via " + paymentMethod);
  }
}

// 서브시스템 - 배송 처리
class ShippingService {
  public void shipOrder(String productId, String address) {
    // 배송 처리 로직 수행
    System.out.println("Shipping product: " + productId + " to address: " + address);

  }
}

// 퍼사드 클래스
class OrderFacade {
  private final OrderProcessing orderProcessing;
  private final PaymentProcessor paymentProcessor;
  private final ShippingService shippingService;

  public OrderFacade() {
    this.orderProcessing = new OrderProcessing();
    this.paymentProcessor = new PaymentProcessor();
    this.shippingService = new ShippingService();
  }

  // 주문 처리를 단순화한 인터페이스 메서드
  public void placeOrder(String productId, int quantity, String paymentMethod, double amount, String address) {
    orderProcessing.processOrder(productId, quantity);
    paymentProcessor.processPayment(paymentMethod, amount);
    shippingService.shipOrder(productId, address);
    System.out.println("Order placed successfully.");
  }
}

public class Client {
  public static void main(String[] args) {
    // 퍼사드를 통해 주문을 처리
    OrderFacade orderFacade = new OrderFacade();
    orderFacade.placeOrder("12345", 2, "CreditCard", 150.0, "경기도 시흥시");
  }
}
```