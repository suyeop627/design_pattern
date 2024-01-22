### 비지터 패턴
- 개념
    - 객체 구조를 변경하지 않고도 새로운 동작을 추가할 수 있게 하는 패턴
        - 객체 구조 내의 각 요소를 방문하고 해당 요소에 대한 특정 작업을 수행
    - 주로 데이터 구조와 처리 연산을 분리하여 확장성을 향상시키기 위해 사용
    - 알고리즘들을 그들이 작동하는 객체들로부터 분리할 수 있음

- 구성요소
    - **Visitor(방문자):**
        - 새로운 동작을 정의하는 인터페이스 또는 추상 클래스
        - 비지터 인터페이스는 객체 구조의 각 구성 요소에 대한 visit 메서드를 정의
    - **ConcreteVisitor(구체적인 방문자):**
        - Visitor 인터페이스를 구현하는 클래스로, 실제로 수행할 작업을 정의
    - **Element(요소):**
        - 방문자가 방문할 객체 구조의 인터페이스 또는 추상 클래스
        - 객체 구조의 각 구성 요소는 accept 메서드를 통해 방문자를 받아들임
    - **ConcreteElement(구체적인 요소):**
        - Element를 구현하는 클래스로, 실제 객체 구조의 구성 요소를 나타냄
        - accept 메서드에서 자신을 방문자에게 전달
    - **ObjectStructure(객체 구조):**
        - Element의 집합을 유지하고, 각 요소에 대한 방문자의 호출을 관리하는 클래스

- 사용시기
    - **객체 구조와 알고리즘을 분리해야 할 때:**
        - 객체 구조는 고정되어 있고, 구조를 변경하지 않고도 여러 알고리즘을 추가하거나 수정해야 할 때.
    - **캡슐화를 통해 코드 중복을 방지해야 할 때:**
        - 관련된 알고리즘을 각 객체에서 분리함으로써 중복 코드를 피하고 각 알고리즘을 별도로 관리할 수 있게 해줌
    - **새로운 기능이 추가되거나 변경될 가능성이 높을 때:**
        - 객체 구조에 새로운 요소를 추가하거나 기존 요소를 변경하는 경우에
        - 새로운 비지터를 추가하면 새로운 기능을 손쉽게 추가할 수 있음
            - 객체 구조의 변화가 잦은 경우에는 비지터 클래스를 모두 수정해야하므로 주의해야함.
    - **다양한 객체 타입에 대해 일관된 방식으로 알고리즘을 적용해야 할 때:**
        - 다양한 객체 타입이 있고, 각 객체에 대해 일관된 방식으로 알고리즘을 적용해야 하는 경우에 사용
        - 복잡한 객체 구조(예: 객체 트리)의 모든 요소에 대해 작업을 수행해야 할 때
    - **클래스 계층구조의 일부 클래스들에서만 의미가 있을 때:**
        - 행동을 별도의 비지터 클래스로 추출한 후, 관련 클래스들의 객체들을 수락하는 비지터 메서드들만 구현하고 나머지는 비워두어 처리하지 않도록 할 수 있음
- 장점
    - **알고리즘의 분리:**
        - 알고리즘을 객체 구조에서 분리함으로써 객체 구조를 변경하지 않고도 새로운 알고리즘을 추가하거나 기존 알고리즘을 수정할 수 있음
    - **코드 확장성:**
        - 새로운 비지터를 추가하는 것만으로도 객체 구조의 확장이 가능하며, 이는 코드의 확장성을 높여줌
    - **유지보수 용이:**
        - 연관된 기능들이 하나의 Visitor에 모여 있어 유지보수가 용이함
- 단점
    - **객체 구조에 대한 변경 어려움:**
        - 객체 구조를 변경하는 경우 모든 비지터 클래스를 수정해야 하므로 유지보수가 어려워짐
    - **복잡성:**
        - ConcreteElement가 추가될 때마다 모든 Visitor 클래스에 새로운 메서드를 추가해야 하므로 코드가 복잡해짐

    ```java
    // Element interface
    public interface Animal {
      String accept(AnimalVisitor visitor);
      String getArea();
      String getName();
    }
    
    // Visitor interface
    public interface AnimalVisitor {
      String visit(Giraffe giraffe);
      String visit(Lion lion);
      String visit(Shark shark);
    }
    
    // Concrete Element
    public class Giraffe implements Animal {
      private final String name;
      private final  String area;
    
      public Giraffe(String name, String area) {
        this.name = name;
        this.area = area;
      }
    
      @Override
      public String accept(AnimalVisitor visitor) {
        return visitor.visit(this);
      }
    
      @Override
      public String getArea() {
        return area;
      }
      @Override
      public String getName() {
        return name;
      }
    }
    
    // Concrete Element
    public class Lion implements Animal {
      private final String name;
      private final  String area;
    
      public Lion(String name, String area) {
        this.name = name;
        this.area = area;
      }
    
      @Override
      public String accept(AnimalVisitor visitor) {
        return visitor.visit(this);
      }
    
      @Override
      public String getArea() {
        return area;
      }
    
      @Override
      public String getName() {
        return name;
      }
    }
    
    // Concrete Element
    public class Shark implements Animal {
      private final String name;
      private final  String area;
    
      public Shark(String name, String area) {
        this.name = name;
        this.area = area;
      }
    
      @Override
      public String accept(AnimalVisitor visitor) {
        return visitor.visit(this);
      }
    
      @Override
      public String getArea() {
        return area;
      }
    
      @Override
      public String getName() {
        return name;
      }
    }
    
    // Concrete Visitor
    public class DataCollectVisitor implements AnimalVisitor{
    
      @Override
      public String visit(Giraffe giraffe) {
        return String.format("  %s(Giraffe)%n", giraffe.getName());
      }
    
      @Override
      public String visit(Lion lion) {
        return String.format("  %s(Lion)%n", lion.getName());
      }
    
      @Override
      public String visit(Shark shark) {
        return String.format("  %s(Shark)%n", shark.getName());
      }
    }
    
    // Concrete Visitor
    public class FeedingVisitor implements AnimalVisitor{
      @Override
      public String visit(Lion lion) {
        return "Feeding meet to lion";
      }
    
      @Override
      public String visit(Giraffe giraffe) {
        return "Feeding leaves and fruits to giraffe";
      }
    
      @Override
      public String visit(Shark shark) {
        return "Feeding fishes and fruits to sharks";
      }
    }
    
    // Object Structure
    public class ZooManager {
      private List<Animal> animals = new ArrayList<>();
    
      public void addAnimal(Animal animal){
        animals.add(animal);
      }
      public void perform(AnimalVisitor visitor){
        animals.forEach(animal -> System.out.println(animal.accept(visitor)));
      }
    
      public void collectData(AnimalVisitor visitor){
    
        Map<String, StringBuilder> areaDataMap = new HashMap<>();
    
        animals.forEach(animal -> {
          String area = animal.getArea();
          //computeIfAbsent(key, function) 
          // - 키에 해당하는 값이 있으면 해당 값을 반환하고,
          //- 키에 해당하는 값이 없으면 새로 지정(key를 인자로 받는 함수로 람다식 작성)
          areaDataMap.computeIfAbsent(area, key -> new StringBuilder().append(key).append("\n"))
              .append(animal.accept(visitor));
        });
        StringBuilder result = new StringBuilder("Animals in Zoo\n");
        areaDataMap.values().forEach(result::append);
    
        System.out.println(result);
      }
    }
    
    // Client Code
    public class Client {
      public static void main(String[] args) {
        ZooManager zooManager = new ZooManager();
        Lion lion = new Lion("lion1", "area1");
        Giraffe giraffe = new Giraffe("giraffe1", "area1");
        Shark shark = new Shark("shark1", "area2");
        zooManager.addAnimal(lion);
        zooManager.addAnimal(giraffe);
        zooManager.addAnimal(shark);
    
        AnimalVisitor feedingVisitor = new FeedingVisitor();
        zooManager.perform(feedingVisitor);
    
        AnimalVisitor dataCollectVisitor = new DataCollectVisitor();
        zooManager.collectData(dataCollectVisitor);
    
      }
    }
    ```