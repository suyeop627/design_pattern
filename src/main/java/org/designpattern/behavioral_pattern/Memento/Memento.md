### 메멘토 패턴
- 개념
    - memento: 명사 (사람·장소를 기억하기 위한) 기념품 (=souvenir)
    - 객체의 구현 세부 사항을 공개하지 않으면서 해당 객체의 이전 상태를 저장하고 복원할 수 있게 해주는 행동 디자인 패턴
    - 객체의 상태를 저장하고 복원하기 위한 패턴
    - 객체의 현재 상태를 외부로부터 분리하여 저장하고, 나중에 이 상태를 복원할 수 있게 함
    - 주로 객체의 상태를 일련의 스냅샷으로 관리하거나, 특정 시점의 상태를 쉽게 복원하기 위해 사용
- 구성요소
    - **Originator :**
        - 상태를 저장하고 복원해야하는 원본 클래스
        - 현재 상태를 메멘토 객체에 저장하고, 메멘토 객체를 사용하여 상태를 복원
    - **Memento :**
        - Originator의 상태를 저장하는 역할을 하는 클래스
        - 메멘토 객체는 Originator에 의해 생성되며, Originator의 상태를 저장함
    - **Caretaker :**
        - 메멘토 객체를 관리하는 역할을 하는 클래스
        - 주로 메멘토 객체를 저장하고 복원하는데 사용
        - Originator의 상태를 저장하거나 복원하려는 시점에서 메멘토 객체를 Originator 에게 전달

- 사용시기
    - **객체의 상태를 저장하고 복원해야 할 때:**
        - 상태를 저장하고 나중에 되돌리거나 특정 시점으로 되돌아가야 하는 상황
    - **캡슐화를 유지하고 싶을 때:**
        - 객체의 상태를 외부에서 직접 접근하지 않고 캡슐화하고자 할 때 사용
        - 객체의 내부 상태를 외부에서 직접 조작하지 않고 메멘토를 통해 간접적으로 상태를 저장하고 복원할 수 있음
        - 상태를 저장하는 로직과 복원하는 로직을 객체 외부로 분리하고 싶을 때
    - **상태의 히스토리를 관리할 때:**
        - 상태의 스냅샷을 여러 개 저장하고 싶을 때
        - 객체의 상태를 일련의 스냅샷으로 저장하여 나중에 히스토리를 조회하거나 되돌릴 수 있는 상황에서 사용

- **장점:**
- 장점
    - **상태의 캡슐화:**
        - 객체의 상태를 외부에서 감추고, 캡슐화하여 안전하게 저장하고 복원할 수 있음
        - 객체의 상태를 캡슐화하고, 상태를 저장하고 복원하는 과정을 캡슐화하여 코드의 유지보수성을 향상
    - **불변성 유지:**
        - 객체의 불변성을 유지하면서 상태를 관리할 수 있음
        - Originator의 내부 상태에 직접 접근할 필요가 없어짐
    - **유연성:**
        - 객체의 상태를 저장하고 복원하는 과정이 캡슐화되어 있어, 상태를 조작하는 방식을 변경해도 클라이언트 코드에 영향을 미치지 않음
- 단점
- **메모리 사용량 증가:**
    - 모든 상태의 스냅샷을 저장해야 하므로 메모리 사용량이 증가
    - 특히, 많은 스냅샷을 저장하는 경우에 영향이 큼
- **복잡성 증가:**
    - 패턴을 구현하려면 Caretaker 클래스와 Memento 클래스가 추가로 필요
    - 특히, 스냅샷 관리 및 복원 로직이 추가됨
        - Caretaker 들은 더 이상 쓸모없는 메멘토들을 파괴할 수 있도록 오리지네이터의 수명주기를 추적해야 함

```java
//Originator
public class Character {
  private String name;
  private int health;
  private List<String> equipment;

  public Character(String name, int health) {
    this.name = name;
    this.health = health;
    this.equipment = new ArrayList<>();
  }

  public void equipItem(String item) {
    equipment.add(item);
  }

  public void takeDamage(int damage) {
    health -= damage;
  }

  public void printCharacterInfo() {
    System.out.println("Current character state:");
    System.out.println("Name: " + name);
    System.out.println("Health: " + health);
    System.out.println("Equipment: " + equipment);
  }

  public CharacterMemento createMemento() {
    return new CharacterMemento(name, health, equipment);
  }

  public void restoreFromMemento(CharacterMemento memento) {
    this.name = memento.getName();
    this.health = memento.getHealth();
    this.equipment = memento.getEquipment();
  }
}

//Memento
public class CharacterMemento {
  private String name;
  private int health;
  private List<String> equipment;

  public CharacterMemento(String name, int health, List<String> equipment) {
    this.name = name;
    this.health = health;
    this.equipment = new ArrayList<>(equipment);
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public List<String> getEquipment() {
    return new ArrayList<>(equipment);
  }
}

//Caretaker
public class CharacterCaretaker {
  private Character character;
  private Stack<CharacterMemento> history;

  public CharacterCaretaker(Character character) {
    this.character = character;
    this.history = new Stack<>();
  }

  public void save() {
    history.push(character.createMemento());
  }

  public void undo() {
    if (!history.isEmpty()) {
      CharacterMemento previousState = history.pop();
      character.restoreFromMemento(previousState);
    }
  }
}

//Client
public class Client {
  public static void main(String[] args) {

    Character playerCharacter = new Character("Hero", 100);
    CharacterCaretaker caretaker = new CharacterCaretaker(playerCharacter);

    playerCharacter.equipItem("Sword");
    playerCharacter.equipItem("Shield");

    caretaker.save();
    playerCharacter.printCharacterInfo();

    playerCharacter.takeDamage(20);
    caretaker.save();
    playerCharacter.printCharacterInfo();

    playerCharacter.takeDamage(20);
    playerCharacter.printCharacterInfo();

    caretaker.undo();
    playerCharacter.printCharacterInfo();

    caretaker.undo();
    playerCharacter.printCharacterInfo();

  }
}
```