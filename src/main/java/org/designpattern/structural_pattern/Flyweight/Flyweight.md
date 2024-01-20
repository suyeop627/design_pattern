### 경량패턴
- 개념
    - 객체의 내부 상태 중에서 여러 객체가 공유할 수 있는 부분을 외부로 분리하여 객체의 메모리 사용을 최적화하는 패턴
    - 재사용 가능한 객체 인스턴스를 공유시켜 메모리 사용량을 최소화하는 구조 패턴
    - 자주 변하는 속성(extrinsit)과 변하지 않는 속성(intrinsit)을 분리하고 변하지 않는 속성을 캐싱하여 재사용해 메모리 사용을 줄이는 방식
    - 동일하거나 유사한 객체들의 공통된 속성을 서로 공유하여 개별 객체를 가볍게 하므로, 경량 패턴임
- 구성요소
    - F**lyweight :**
        - 여러 객체가 공유할 인터페이스로, 객체의 공통 동작을 선언.
        - 경량 객체를 묶는 인터페이스.
    - **ConcreteFlyweight :**
        - Flyweight 인터페이스를 구현하는 실제 객체
        - 내부 상태를 가지고 있을 수 있지만, 외부 상태(클라이언트가 제공하는 정보)에 대한 참조는 갖지 않아야 함
        - 객체의 외부 상태를 제공하는 컨텍스트와 독립적이어야 함
    - UnsharedConcreteFlyweight :
        - Flyweight 인터페이스를 구현하지만, 외부 상태를 가질 수 있는 객체
        - 외부 상태를 가지므로 객체가 모두 개별적이라서 공유할 필요가 없는 객체
    - **Flyweight Factory :**
        - Flyweight 객체를 생성하고 관리하는 역할
        - 경량 객체를 만드는 공장 역할과 캐시 역할을 겸비하는 Flyweight 패턴의 객체 관리 클래스
        - 이 팩토리는 생성된 Flyweight 객체를 캐싱하여 재사용 가능하도록 함
        - 경량 객체를 만드는 공장 역할과 캐시 역할을 겸비하는 Flyweight 패턴의 객체 관리 클래스
        - GetFlyweight() 메서드는 팩토리 메서드 역할과 유사함
        - 만일 객체가 메모리에 존재하면 그대로 가져와 반환하고, 없다면 새로 생성해 반환
    1. **Client (클라이언트):** Flyweight 패턴을 사용하는 객체로, Flyweight 팩토리를 통해 Flyweight 객체를 요청하고 사용함

- intrinsicState
    - '고유한, 본질적인' 이라는 의미
    - 본질적인 상태란 인스턴스가 어떠한 상황에서도 변하지 않는 정보를 의미 → 공유 가능한 상태
    - 모든 인스턴스에서 동일하며, 객체를 공유하고 중앙에서 관리할 수 있습니다.
    - 주로 Flyweight 인터페이스 내부에 위치하며, 객체가 생성될 때 초기화되고 변경되지 않습니다.
        - ex) 지뢰찾기의 지뢰 이미지
- extrinsicState
    - '외적인, 비본질적인' 이라는 의미
    - 객체의 외부에서 따로 저장되거나 유지되어, 각 인스턴스에서 독립적으로 유지되는 상태
    - 객체가 사용되는 문맥에 따라 변할 수 있으며, 클라이언트에서 관리됨
    - 인스턴스를 두는 장소나 상황에 따라서 변화하는 정보를 의미함. → 상황에 따라 변하므로, 공유 불가능한 상태
        - ex) 지뢰의 좌표
- Flyweight 패턴은 intrinsic을 최대화, extrinsic을 최소화해서 개별 객체를 더 가볍게 만드는 것.


- 사용시기
    - **많은 수의 유사한 객체가 생성되어야 할 때:**
        - 객체가 가볍고 상태가 공유될 수 있다면, Flyweight 패턴을 사용하여 메모리 사용량을 줄일 수 있습니다.
        - 공통적인 인스턴스를 많이 생성하는 로직이 포함된 경우
    - **객체 생성 비용이 높은 경우:**
        - 객체 생성 및 초기화에 많은 비용이 발생하는 경우, 이를 최소화하기 위해 Flyweight 패턴을 고려
    - **메모리 사용 최적화가 필요한 경우:**
        - 많은 객체를 생성하는 것이 메모리 소모가 큰 경우, 객체를 공유하여 메모리 사용량을 줄이고 성능을 향상시킬 수 있습니다.
- 장점
    - **메모리 사용 최적화:**
        - 비슷한 객체들 간에 공유되는 상태를 통해 메모리 사용을 최적화
    - **성능 향상:**
        - 객체를 공유함으로써 객체 생성 및 초기화에 따른 비용을 감소
        - new로 인스턴스화를 하면 데이터가 생성되고 메모리에 적재 되는 미량의 시간이 소요되는데, 이를 절감함
- 단점
    - **공유 상태의 변화에 대한 주의가 필요:**
        - 객체의 내부 상태가 공유되므로, 한 객체의 상태 변경이 다른 객체에 영향을 줄 수 있음
        - 따라서 공유되는 상태의 변경에 대한 주의가 필요합니다.
    - **복잡성 증가:**
        - 객체를 공유하고 상태를 분리하는 로직을 추가하면 코드의 복잡성이 증가

```java
//Flyweight interface
public interface Unit {
  void attack();
}

//ConcreteFlyweight
public class UnitType implements Unit{
  protected final String weaponType; //intrinsic state
  protected final String armorType; //intrinsic state

  public String getWeaponType() {
    return weaponType;
  }

  public String getArmorType() {
    return armorType;
  }

  public UnitType(String weaponType, String armorType) {
    this.weaponType = weaponType;
    this.armorType = armorType;
  }

  @Override
  public void attack() {
    System.out.printf("Attacked enemy with %s%n", weaponType);
  }
}

//UnsharedConcreteFlyweight
public class NormalUnit extends UnitType {
  private final String name; //extrinsic state

  public NormalUnit(String name, String weaponType, String armorType) {
    super(weaponType, armorType);
    this.name = name;
    System.out.printf("%s (%s, %s) has been created%n", name, weaponType, armorType);
  }

  @Override
  public void attack() {
    System.out.printf("%s(%s, %s) attacked enemy&n", name, weaponType, armorType);
  }
}

//Flyweight factory
public class UnitFactory {
  //intrinsic state를 캐싱하기위한 map
  Map<String, UnitType> unitCaches = new HashMap<>();

  public Unit createUnit(String name, String position) {

    UnitType unitType;

    if (unitCaches.containsKey(position)) {
      unitType = unitCaches.get(position);
    } else {
      unitType = createUnitType(position);
      unitCaches.put(position, unitType);

    }
    return new NormalUnit(name, unitType.getWeaponType(), unitType.getArmorType());
  }

  private UnitType createUnitType(String position) {
    UnitType unitType = null;
    try {
      Thread.sleep(1000);
      switch (position.toLowerCase()) {
        case "tanker" -> unitType = new UnitType("Shield and One-hand sword", "Plate armor");
        case "worrier" -> unitType = new UnitType("Two-hand sword", "Chain mail");
        case "archer" -> unitType = new UnitType("Bow", "Leather armor");
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return unitType;
  }
}

//Client - flyweight을 사용하며, extrinsic state를 제공함(context)
public class Client {
  public static void main(String[] args) {
    UnitFactory unitFactory = new UnitFactory();
    long startTime = System.currentTimeMillis();
    Unit normalTanker = unitFactory.createUnit("tanker1", "tanker");
    Unit normalArcher = unitFactory.createUnit("archer1", "archer");
    Unit normalWorrier = unitFactory.createUnit("worrier1", "worrier");

    System.out.println("=============Create unit task completed in " + (System.currentTimeMillis() - startTime) + " milliseconds.=============");
    //use cached object
    long startTime1 = System.currentTimeMillis();
    Unit normalTankerCached = unitFactory.createUnit("tanker2", "tanker");
    Unit normalArcherCached = unitFactory.createUnit("archer2", "archer");
    Unit normalWorrierCached = unitFactory.createUnit("worrier2", "worrier");
    System.out.println("=============Create unit using caching completed in " + (System.currentTimeMillis() - startTime1) + " milliseconds.=============");
  }
}
```