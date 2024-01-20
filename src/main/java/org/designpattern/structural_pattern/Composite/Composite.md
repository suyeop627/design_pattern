### 복합체 패턴
- 개념
    - 객체들을 트리 구조로 구성하여 개별 객체와 복합 객체(객체들의 집합)를 동일한 방식으로 다루는 구조적 디자인 패턴
    - 이 패턴은 전체-부분 계층 구조를 표현하고 클라이언트가 개별 객체 및 복합 객체를 구별하지 않고 사용할 수 있도록 함
    - 복합 객체(Composite)와 단일 객체(Leaf)를 동일한 컴포넌트로 취급하여, 클라이언트에게 이 둘을 구분하지 않고 동일한 인터페이스를 사용하도록 하는 구조 패턴
    - 예시) 윈도우나 리눅스의 파일 시스템 구조
        - 폴더안에 파일 또는 폴더를 저장할 수 있음(Composite 객체)
        - 파일은 파일안에 새로운 파일이나 폴더를 저장할 수 없음(Leaf 객체)

- 구성요소
    - Component : Leaf와 Compsite 를 묶는 공통적인 상위 인터페이스
    - Composite : 복합 객체로서, Leaf 역할이나 Composite 역할을 넣어 관리하는 역할을 한다.
    - Component 구현체들을 내부 리스트로 관리한다
        - add 와 remove 메소드는 내부 리스트에 단일 / 복합 객체를 저장하거나 삭제함
        - Component 인터페이스의 구현 메서드인 operation은 복합 객체에서 호출되면 재귀 하여, 단일 객체를 저장한 하위 복합 객체까지 순회함
    - Leaf: 단일 객체로서, 단순하게 내용물을 표시하는 역할
        - Component 인터페이스의 구현 메서드인 operation은 단일 객체에서 호출되면 적절한 값만 반환
    - Client : 클라이언트는 Component를 참조하여 단일 / 복합 객체를 하나의 객체로서 다룬다.

- 사용 시기
    - **계층 구조가 필요한 경우:**
        - 객체들이 트리 구조로 구성되어 있고, 이러한 계층 구조를 표현해야 하는 경우
    - **단일 객체와 복합 객체를 통일적으로 처리해야 하는 경우:**
        - 클라이언트가 단일 객체와 복합 객체를 동일한 방식으로 처리해야 하는 상황
    - **유연성이 필요한 경우:**
        - 객체의 추가나 삭제가 빈번하게 발생하며, 이러한 변동에 대응하기 위해 유연한 설계가 필요한 경우
        - 수평 및 수직방향 확장이 수월함
    - **클라이언트가 객체 구조를 모르는 경우:**
        - 클라이언트가 자세한 객체 구조를 알 필요 없이 단일 인터페이스를 통해 객체들을 다루어야 할 때
- 장점
    - **일관성 있는 인터페이스:**
        - 개별 객체와 복합 객체를 동일한 인터페이스로 다룰 수 있어, 클라이언트 코드에서 일관성 있는 방식으로 객체들을 다룰 수 있음
    - **재귀적 구조:**
        - 다형성 재귀를 통해 복잡한 트리 구조를 보다 편리하게 구성함
    - **유연성과 확장성:**
        - 새로운 개별 객체나 복합 객체를 추가하거나 제거하기가 쉬움
        - 수평적, 수직적 모든 방향으로 객체를 확장 가능하여, 이는 시스템의 유연성과 확장성을 향상시킴
- 단점
    - **복잡성:**
        - 대규모의 계층 구조를 가진 경우 복잡성이 증가하여,  코드를 이해하거나 유지보수하기 어려울 수 있음
        - 재귀 호출 특징 상 트리의 깊이(depth)가 깊어질 수록 디버깅이 어려움
    - **일부 제약사항 지정 어려움:**
        - 일부 복합 객체에서는 특정한 제약사항이 필요할 경우 제약 특정 객체에서만 제약사항을 갖기 어려움
            - 설계가 지나치게 범용성을 갖기 때문에 새로운 요소를 추가할 때 복합 객체에서 구성 요소에 제약을 갖기 어려움
            - 예를들어, 계층형 구조에서 leaf 객체와 composite 객체들을 모두 동일한 인터페이스로 다루어야하는데, 이 공통 인터페이스 설계가 까다로울 수 있음
                - 복합 객체가 가지는 부분 객체의 종류를 제한할 필요가 있을 때
                - 수평적 방향으로만 확장이 가능하도록 Leaf를 제한하는 Composite를 만들때

    ```java
    // Component
    public interface Shape {
      void draw();
    }
    
    // Leaf
    public class Circle implements Shape{
      private String color;
      public Circle(String color){
        this.color = color;
      }
      @Override
      public void draw() {
        System.out.printf("Drawing %s Circle%n", color);
      }
    }
    
    // Leaf
    public class Square implements Shape{
      private final String color;
      public Square(String color){
        this.color = color;
      }
      @Override
      public void draw() {
        System.out.printf("Drawing %s Square%n", color);
      }
    }
    
    // Composite
    public class ShapeGroup implements Shape{
      private final List<Shape> shapeList = new ArrayList<>();
    
      public void addShape(Shape shape){
        shapeList.add(shape);
      }
      @Override
      public void draw() {
        System.out.println("-----Drawing group------");
        shapeList.forEach(Shape::draw);
    
      }
    }
    
    // Client
    public class CompositePatternExample {
        public static void main(String[] args) {
            // 개별 도형
            Shape circle = new Circle();
            Shape square = new Square();
    
            // 그룹화된 도형
            Group group1 = new Group();
            group1.addShape(circle);
            group1.addShape(square);
    
            // 더 큰 그룹
            Shape triangle = new Square();
            Group group2 = new Group();
            group2.addShape(group1);
            group2.addShape(triangle);
    
            // 클라이언트는 개별 도형과 그룹을 동일한 방식으로 다룰 수 있음
            circle.draw();
            square.draw();
            group1.draw();
            group2.draw();
        }
    }
    
    public class Client {
      public static void main(String[] args) {
        Shape redCircle = new Circle("red");
        Shape redSquare = new Square("red");
    
        ShapeGroup redShapes = new ShapeGroup();
        redShapes.addShape(redCircle);
        redShapes.addShape(redSquare);
        redShapes.draw();
        Shape blueCircle = new Circle("blue");
        Shape blueSquare = new Square("blue");
    
        ShapeGroup blueShapes = new ShapeGroup();
        blueShapes.addShape(blueCircle);
        blueShapes.addShape(blueSquare);
        blueShapes.draw();
    
        ShapeGroup allShapes = new ShapeGroup();
        allShapes.addShape(redShapes);
        allShapes.addShape(blueShapes);
        allShapes.draw();
    
      }
    }
    ```