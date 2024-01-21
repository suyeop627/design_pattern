### 전략 패턴
- 개념
    - 알고리즘군을 정의하고 각각을 캡슐화하여 교체 가능하게 만드는 패턴
    - 클라이언트에서 알고리즘을 독립적으로 선택할 수 있도록 해서, 알고리즘을 독립적으로 변경하고 확장할 수 있음
    - 객체 간의 결합도를 낮추고 재사용성을 높임
- **Strategy:**
    - 알고리즘을 나타내는 인터페이스 또는 추상 클래스로, 모든 전략 구현제에 대한 공용 인터페이스
    - 구체적인 알고리즘 클래스들은 이 인터페이스를 구현
- **Concrete Strategy**
    - 알고리즘, 행위, 동작을 객체로 정의한 구현체
    - 전략 인터페이스를 실제로 구현한 클래스들로, 각각의 클래스는 특정한 알고리즘을 나타냄
- **Context**
    - 전략을 사용하는 객체로서, 클라이언트와 상호 작용함
    - 알고리즘을 실행해야 할 때마다 해당 알고리즘과 연결된 전략 객체의 메서드를 호출.
- **Client**
    - 전략을 사용하는 주체
    - 컨텍스트 객체를 생성하고, 해당 객체에게 어떤 전략을 사용할지 지시함
    - 특정 전략 객체를 컨텍스트에 전달 함으로써 전략을 등록하거나 변경하여 전략 알고리즘을 실행한 결과를 반환받음

- 사용시기
    - **동적으로 알고리즘을 교체해야 할 때:**
        - 프로그램이 실행 중에 알고리즘을 변경해야 하는 경우
        - 콘텍스트가 다양한 알고리즘을 동적으로 선택할 필요가 있는 상황
    - **유사한 작업을 수행하는 여러 알고리즘이 존재할 때:**
        - 여러 알고리즘이 유사한 작업을 수행하지만 구체적인 구현이 다른 경우
        - 각 알고리즘을 독립적으로 관리
    - **알고리즘의 수정이나 확장이 빈번한 경우:**
        - 알고리즘의 변경이나 새로운 알고리즘의 추가가 빈번하게 발생하는 상황에서 활용 가능

- 장점
    - **유연성 및 확장성:**
        - 콘텍스트와 전략 간의 결합이 낮아져 유연성이 향상됨
        - 새로운 전략을 추가하거나 기존의 전략을 변경하여 확장하기 용이함
    - **코드 재사용:**
        - 각각의 전략은 독립적으로 구현되어 있으므로, 비슷한 기능을 하는 여러 알고리즘이 있을 때 코드를 재사용할 수 있음
    - **유지보수 용이성:**
        - 각 전략은 콘텍스트와 독립적으로 존재하므로 유지보수가 용이
        - 변경이 필요한 경우 해당 전략만 수정하면 됨
    - **알고리즘의 동적 변경:**
        - 런타임에서 알고리즘을 동적으로 변경할 수 있음
- 단점
    - **복잡성 증가:**
        - 전략 패턴을 도입하면 일반적으로 클래스의 수가 증가하고, 각 전략마다 별도의 클래스가 필요함
        - 이로 인해 클래스 간의 상호 작용이 복잡해질 수 있음
    - **클라이언트가 알고리즘을 명시적으로 선택:**
        - 클라이언트가 컨텍스트와 전략을 명확히 이해해야함

```java
//Strategy
public interface PricingStrategy {
  int calculateCost(double distance);
}

//**Concrete Strategy**
public class BusPricingStrategy implements PricingStrategy{
  private final int BASE_FARE = 1450;
  private final int ADDITIONAL_FARE_PER_5KM = 100;
  @Override
  public int calculateCost(double distance) {
    distance  = distance > 10 ? distance-10 : 0;
    return BASE_FARE + ((int) (distance)/5 * ADDITIONAL_FARE_PER_5KM);
  }
}

//**Concrete Strategy**
public class TaxiPricingStrategy implements PricingStrategy{
  private final int BASE_FARE = 4800;
  private final int ADDITIONAL_FARE_PER_131M = 100;

  @Override
  public int calculateCost(double distance) {
    return  BASE_FARE + ((int)(distance*1000/131 * ADDITIONAL_FARE_PER_131M));
  }
}

//**Concrete Strategy**
public class SharingCarPricingStretegy implements PricingStrategy{
  private final int FIXED_FARE = 10000;
  @Override
  public int calculateCost(double distance) {
    return FIXED_FARE;
  }
}

//**Context**
public class TransportationApp {
  private PricingStrategy pricingStrategy;
  public void setPricingStrategy(PricingStrategy pricingStrategy) {
    this.pricingStrategy = pricingStrategy;
  }
  public int calculatePrice(double distance){
    return pricingStrategy.calculateCost(distance);
  }
}

//**Client** 
public class Client {
  public static void main(String[] args) {
    TransportationApp transportationApp = new TransportationApp();
    double distance = 15;
    
    //bus pricing
    transportationApp.setPricingStrategy(new BusPricingStrategy());
    int fareOfTakingBus = transportationApp.calculatePrice(distance);
    System.out.printf("Price of selected transportation is %d won. %n", fareOfTakingBus);
    
    //taxi pricing
    transportationApp.setPricingStrategy(new TaxiPricingStrategy());
    int fareOfTakingTaxi = transportationApp.calculatePrice(distance);
    System.out.printf("Price of selected transportation is %d won. %n", fareOfTakingTaxi);
    
    //sharing car pricing
    transportationApp.setPricingStrategy(new SharingCarPricingStretegy());
    int fareOfSharingCar = transportationApp.calculatePrice(distance);
    System.out.printf("Price of selected transportation is %d won. %n", fareOfSharingCar);
  }
}
```