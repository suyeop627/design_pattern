### 중재자 패턴
- 개념
    - 객체 간의 상호작용을 중재하는 객체를 도입하여 결합도를 낮추고, 객체 간의 직접적인 통신을 방지하여 중재자 객체를 통해서만 협력하도록 하는 디자인 패턴
    - 중재자 패턴은 객체 간의 상호작용을 캡슐화하여 객체들 간의 직접적인 통신을 방지하고, 중재자를 통해 상호작용하도록 하는 행동 패턴
    - 객체들 간의 복잡한 상호작용을 중앙화된 중재자에 위임하여 각 객체가 서로 독립적으로 변경될 수 있도록 함
- 구성요소
    - **Mediator:**
        - 객체 간의 상호작용을 총괄하고 캡슐화하는 인터페이스 또는 클래스
        - 동료 객체들이 상호 작용할 때 필요한 메소드를 정의
    - **ConcreteMediator:**
        - Mediator 인터페이스를 구현한 클래스
        - 동료 객체들 간의 통신을 조정하고 중재하여, 객체 간의 상호작용을 조정함
    - **Colleague:**
        - Mediator와 통신하기 위해 Mediator에 등록되는 객체들
        - 동료 객체들은 중재자에게 통신을 요청하거나 중재자를 통해 다른 동료 객체들과 상호 작용함
    - **ConcreteColleague(구체적인 협력자):**
        - Colleague 인터페이스를 구현하는 클래스
        - 중재자에게 메시지를 보내거나 중재자로부터 메시지를 수신할 수 있음

- 사용시기
    - **복잡한 상호 작용이 있는 시스템:**
        - 객체 간의 직접적인 통신을 방지하고 중재자를 통해 상호 작용을 캡슐화함으로써 시스템을 더 잘 구조화할 수 있음
    - **객체 간 결합도를 낮춰야 할 때:**
        - 객체 간의 결합도를 최소화하고 유연성을 증가시켜야 하는 경우
        - 객체 간의 직접적인 통신을 피하고자 할 때
        - 복잡한 객체 간 상호작용을 캡슐화하고자 할 때
    - **동적인 확장이나 변경이 필요한 경우:**
        - 시스템에 새로운 동료 객체를 도입하거나 기존 동료 객체를 변경하면서 유연성을 유지하고 싶을 때 활용

- 장점
    - **객체 간의 결합도 감소:**
        - 객체들이 중재자를 통해 통신하므로, 객체 간 직접적인 의존성이 감소
    - **유연성 향상:**
        - 중재자를 통해 객체 간의 관계를 동적으로 변경할 수 있어 유연성이 향상됨
    - **중앙 집중적인 관리:**
        - 상호작용 로직이 중재자에 중앙 집중화되어 있어 관리가 용이
- 단점
    - **중재자의 복잡성:**
        - 중재자가 많은 책임을 가지게 되면, 중재자의 복잡성이 증가할 수 있음
        - 모든 통신 로직이 중재자에 집중되기 때문에 중재자가 복잡해질 수 있음
    - **집중화로 인한 단일 지점 장애의 위험도 증가:**
        - 중재자에 문제가 발생하면 시스템 전체에 영향을 미칠 수 있음
    - **통신의 지연 가능성:**
        - 중재자를 통해 간접적으로 통신하므로 어떤 경우에는 직접 통신보다 지연이 발생할 수도 있음

```java
//Mediator
public interface AirTrafficControl {
  boolean requestLanding(Aircraft aircraft);

  void acknowledgeLanding(Aircraft aircraft);

  void clearRunway();
}

//Concrete Mediator
public class AirportControl implements AirTrafficControl{

  private final List<Aircraft> aircraftList = new ArrayList<>();
  private boolean runwayAvailable = true;

  @Override
  public synchronized boolean requestLanding(Aircraft aircraft) {
    System.out.println(aircraft.getFlightNumber() + " requests landing clearance.");
    if(runwayAvailable){
      acknowledgeLanding(aircraft);
      aircraftList.remove(aircraft);
      return true;
    }else{
      System.out.println("Runway is occupied. " + aircraft.getFlightNumber() + " must wait.");
      return false;
    }
  }

  @Override
  public synchronized void acknowledgeLanding(Aircraft aircraft) {
    for(Aircraft a : aircraftList){
      System.out.println(a.getFlightNumber() + " received message : Landing clearance granted for " + aircraft.getFlightNumber() + ".");
    }
    runwayAvailable = false;
  }

  @Override
  public void clearRunway() {
    runwayAvailable = true;
  }

  public void addAircraft(Aircraft aircraft) {
    aircraftList.add(aircraft);
  }
}

//Colleague interface
public abstract class Aircraft implements Runnable{
  protected AirTrafficControl airTrafficControl;
  protected String flightNumber;

  public String getFlightNumber() {
    return flightNumber;
  }

  public Aircraft(AirTrafficControl airTrafficControl, String flightNumber){
    this.airTrafficControl = airTrafficControl;
    this.flightNumber = flightNumber;
  }

  public abstract void run();
  protected boolean requestLanding(){
     return airTrafficControl.requestLanding(this);
  }

  protected void landing(){
    System.out.println(flightNumber + " landed.");
    airTrafficControl.clearRunway();
  }
}

//Concrete Colleague
public class Airplane extends Aircraft {
  boolean isLanded = false;

  public Airplane(AirTrafficControl airTrafficControl, String flightNumber) {
    super(airTrafficControl, flightNumber);
  }

  @Override
  public void run() {
    while (!isLanded) {
      if (requestLanding()) {
        stopOneSecond();
        landing();
        isLanded = true;
      } else {
        System.out.println(flightNumber + "is waiting for ATC getting available.");
        stopOneSecond();
      }

    }
  }

  private void stopOneSecond() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}

public class Client {
  public static void main(String[] args) {
    AirportControl airportControl = new AirportControl();
    Aircraft aircraft1 = new Airplane(airportControl, "Flight 1");
    Aircraft aircraft2 = new Airplane(airportControl, "Flight 2");
    Aircraft aircraft3 = new Airplane(airportControl, "Flight 3");

    airportControl.addAircraft(aircraft1);
    airportControl.addAircraft(aircraft2);
    airportControl.addAircraft(aircraft3);

    Thread aircraftThread1 = new Thread(aircraft1);
    Thread aircraftThread2 = new Thread(aircraft2);
    Thread aircraftThread3 = new Thread(aircraft3);
    aircraftThread1.start();
    aircraftThread2.start();
    aircraftThread3.start();

  }
}
```