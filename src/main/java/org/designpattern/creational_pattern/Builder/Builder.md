### 빌더패턴
- 개념
    - 종류
        - 심플 빌더패턴(이펙티브 자바패턴) - 일반적으로 사용되는 빌더패턴임.
        - 디렉터 빌더패턴(GOF)
    - 복잡한 객체의 생성 과정을 추상화하여, 객체를 조립하는 방식을 제공하는 디자인 패턴
    - 주로 많은 설정 옵션을 가진 객체를 생성할 때 사용되며, 가독성이 높고 확장성이 좋은 코드를 작성할 수 있게 해줌
        - 다양한 설정을 가진 객체 생성 시, 생성자 오버로딩을 사용해야하는데, 설정값이 많으면 생성자의 수가 너무 많아짐
        - 그렇다고 setter를 사용하자니 객체 생성 후에 값을 수정할 수 있어서 객체의 불변함을 보장할 수 없고, 누락된 setter가 생길수도 있음
        - 빌더패턴은 이런 문제를 해결할 수 있음. 하나씩 값을 입력받아 최종적으로 build()로 객체를 생성함으로써 누락을 피하고 불변도 보장할 수 있음

- 장점(예제는 심플 빌더패턴)
    - 객체 생성 과정을 일관된 프로세스로 표현
    - 디폴트 매개변수 생략을 간접적으로 지원

        ```java
        class EmployeeBuilder{
            private int id;
            private String name;
            private String position = "Junior"; // 디폴트 매개변수 역할
            private String phoneNumber;
        
            ...
        }
        
        class Client{
        // 디폴트 필드인 grade를 제외하고 빌더 구성 및 인스턴스화
        Employee student1 = new EmployeeBuilder()
        				.id(1)
                .name("Kim")
                .phoneNumber("010-5555-5555")
                .build();
        }
        ```

    - 필수 멤버와 선택적 멤버 분리가능

        ```java
        class EmployeeBuilder{
        		//초기화 시 필수 입력값
            private int id;
            private String name;
        
        		//선택 입력값
            private String position = "Junior"; // 디폴트 매개변수 역할
            private String phoneNumber;
        
        		// 필수 멤버는 빌더의 생성자를 통해 설정
        		EmployeeBuilder(int id, String name){
        			this.id=id;
        			this.name = name;
        		}
            //...
        
        	public Employee build() {
        	    return new Employee (id, name, grade, phoneNumber);
            }
        }
        
        class client{
        	
        	// 필수값은 생성자로 해당 값 설정
        	Employee student1 = new EmployeeBuilder(1, "Kim")
                .phoneNumber("010-5555-5555")
                .build();
        	
        }
        ```

    - 생성 단계 지연 가능

        ```java
        class client{
        // 1. 빌더 클래스 전용 리스트 생성
        List<EmployeeBuilder> builders = new ArrayList<>();
        
        // 2. 객체를 최종 생성 하지말고 초깃값만 세팅한 빌더만 생성
        builders.add(
            new EmployeeBuilder(1)
            .name("홍길동")
        );
        
        builders.add(
            new EmployeeBuilder(2)
            .name("임꺽정")
            .position("senior")
        );
        
        builders.add(
            new EmployeeBuilder(3)
            .name("박혁거세")
            .grade("sophomore")
            .phoneNumber("010-5555-5555")
        );
        
        // 3. 나중에 빌더 리스트를 순회하여 최종 객체 생성을 주도
        for(EmployeeBuilder b : builders) {
            Employee employee= b.build();
            System.out.println(employee);
        	
        }
        ```

    - 초기화 검증을 멤버별로 분리
        - 각 멤버 입력 메서드에서 각 멤버별 유효성 검사를 진행할 수 있음

        ```java
        public EmployeeBuilder position(String position) {
                if (!position.equals("freshman") && !position.equals("sophomore") && !position.equals("junior") && !position.equals("senior")) {
                    throw new IllegalArgumentException(position);
                }
                this.position= position;
                return this;
            }
        ```

    - 멤버변수 변경 가능성을 최소화할 수 있음

- 단점
    - 코드 복잡성 증가(클래스마다 빌더 클래스 만들어야됨)
    - 생성자보다 성능이 떨어짐
    - 코드가 장황함.
        - 필드가 적은 경우 생성자나 정적 팩토리 메서드 패턴을 이용하는게 더 나을 수 있음
- 사용시기
    - **복잡한 객체 생성**:
        - 객체가 여러 옵션 또는 구성 단계를 거쳐야 하는 경우.
        - 예를 들어, 여러 속성이나 설정이 필요한 복잡한 객체를 만들어야 할 때.
    - **가독성 향상**:
        - 생성자에 많은 매개변수를 전달하는 것은 코드의 가독성을 할 수 있으므로, 빌더 패턴을 사용하면 메서드 체인을 통해 가독성을 향상
    - **선택적인 속성 설정**:
        - 객체 생성 시 일부 속성은 필수이지만 일부는 선택적일 때
        - 사용자는 필요한 속성만 설정하고 나머지는 기본값이나 사용자가 원하는 값으로 설정할 수 있음
    - **불변성 보장**:
        - 빌더 패턴은 불변 객체를 생성하는 데 도움이 됨.
        - 객체의 불변성은 다중 스레드 환경에서 안전하고 예측 가능한 동작을 제공
    - **객체 생성의 일관성 유지**:
        - 여러 객체가 비슷한 속성을 가지거나 특정한 규칙에 따라 생성되어야 하는 경우 빌더 패턴을 사용하여 일관성을 유지할 수 있음

- 구성
    - 심플 빌더패턴(Effective java)
        - **제품(Product):** 생성될 클래스로, 빌더 패턴을 통해 생성되며, 빌더를 통해서만 초기화됨.
        - **빌더(Builder):** 객체를 생성하는 단계적인 방법을 제공하는 인터페이스 또는 추상 클래스
        - **심플 빌더(SimpleBuilder):** 빌더의 구현 클래스로서, 제품의 생성과 초기화를 담당
        - **클라이언트(Client):** 빌더를 사용하여 객체를 생성하고 조립함.
        - 빌더클래스는 내부 클래스로 구현
            - 하나의 빌더 클래스는 하나의 대상 객체 생성만을 위해 사용되므로, 두 클래스를 물리적으로 그룹화 시켜서 두 클래스간의 관계에 대한 파악을 쉽게 할 수 있게함.
            - 대상 객체는 오로지 빌더 객체에 의해 초기화됨.
                - 즉, 생성자를 외부에 노출시키면 안되기 때문에 생성자를 private로 하고, 내부 빌더 클래스에서 private 생성자를 호출함으로써 오로지 빌더 객체에 의해 초기화 되도록 설계
            - static inner class 사용
                - 정적 내부 클래스는 외부 클래스의 인스턴스 없이도 생성가능.
                    - 만일 일반 내부 클래스로 구성한다면 내부 클래스를 생성하기도 전에 외부 클래스를 인스턴스화 해야 함.
                    - 빌더가 최종적으로 생성할 클래스의 인스턴스를 먼저 생성해야 한다면 모순이 발생.
                - 메모리 누수 문제 때문에 static으로 내부 클래스를 정의해주어야 한다.
                    - static이 아닌 inner class 는 outer class 의 인스턴스에 대한 암묵적인 참조를 가지게 됨.
                    - 내부 클래스가 외부 클래스의 인스턴스에 대한 참조를 가지면, 내부 클래스의 인스턴스가 계속해서 유지되는 한 외부 클래스의 인스턴스도 메모리에서 해제되지 않아 메모리 누수 발생함.

        ```java
        // 제품(Product)
        class SimpleComputer {
            private String cpu;
            private String memory;
            private String storage;
        
            // Getter methods...
        
            private SimpleComputer() {
                // 객체 생성은 빌더를 통해서만 허용
            }
        
            // 심플 빌더
            static class SimpleBuilder {
                private SimpleComputer computer;
        
                SimpleBuilder() {
                    this.computer = new SimpleComputer();
                }
        
                // 빌더 메서드들
                SimpleBuilder withCpu(String cpu) {
                    computer.cpu = cpu;
                    return this;
                }
        
                SimpleBuilder withMemory(String memory) {
                    computer.memory = memory;
                    return this;
                }
        
                SimpleBuilder withStorage(String storage) {
                    computer.storage = storage;
                    return this;
                }
        
                // 제품 반환
                SimpleComputer build() {
                    return computer;
                }
            }
        }
        
        // 클라이언트
        public class SimpleBuilderPatternExample {
            public static void main(String[] args) {
                // 빌더를 통해 객체 생성
                SimpleComputer computer = new SimpleComputer.SimpleBuilder()
                    .withCpu("Intel i5")
                    .withMemory("8GB RAM")
                    .withStorage("512GB SSD")
                    .build();
        
                // 생성된 객체 사용...
            }
        }
        ```

- 디렉터 빌더패턴(GOF)

    - 구성요소
        1. **Builder (빌더):**
            - 객체 생성의 각 단계를 정의하는 인터페이스 또는 추상 클래스
            - 각 단계에 해당하는 메서드들을 선언함
        2. **ConcreteBuilder (구체적인 빌더):**
            - Builder를 구현한 클래스
            - 실제로 객체를 생성하고 조립하는 역할
            - 필요에 따라 객체를 초기화하고 설정하는 메서드들을 구현
        3. **Director (감독자):**
            - Builder를 사용하여 객체를 생성하는 클래스
            - 객체를 조립하는 순서를 정의하고, Builder를 통해 객체를 생성
        4. **Product (생성될 객체):**
            - 최종적으로 생성되는 객체
        - 클라이언트는 디렉터에 적절한 빌더를 전달하여, 해당 빌더에 의해 생성된 객체를 받을 수 있음.

    ```java
    // Product
    class Computer {
        private String cpu;
        private String memory;
        private String storage;
    
        // Getter methods...
    
        private Computer() {
            // private constructor to enforce the use of Builder
        }
    
        // Builder Interface
        interface Builder {
            Builder setCpu(String cpu);
            Builder setMemory(String memory);
            Builder setStorage(String storage);
            Computer build();
        }
    
        // Concrete Builder
        static class ConcreteBuilder implements Builder {
            private Computer computer;
    
            ConcreteBuilder() {
                this.computer = new Computer();
            }
    
            @Override
            public Builder setCpu(String cpu) {
                computer.cpu = cpu;
                return this;
            }
    
            @Override
            public Builder setMemory(String memory) {
                computer.memory = memory;
                return this;
            }
    
            @Override
            public Builder setStorage(String storage) {
                computer.storage = storage;
                return this;
            }
    
            @Override
            public Computer build() {
                return computer;
            }
        }
    
        // Director
        static class ComputerDirector {
            Computer buildGamingComputer(Builder builder) {
                return builder
                    .setCpu("Intel i7")
                    .setMemory("16GB RAM")
                    .setStorage("1TB SSD")
                    .build();
            }
    
            Computer buildOfficeComputer(Builder builder) {
                return builder
                    .setCpu("Intel i5")
                    .setMemory("8GB RAM")
                    .setStorage("512GB SSD")
                    .build();
            }
        }
    }
    
    // Client
    public class DirectorBuilderPatternExample {
        public static void main(String[] args) {
            Computer.Builder builder = new Computer.ConcreteBuilder();
            ComputerDirector director = new Computer.ComputerDirector();
    
            // Gaming Computer 생성
            Computer gamingComputer = director.buildGamingComputer(builder);
            System.out.println("Gaming Computer: " + gamingComputer);
    
            // Office Computer 생성
            Computer officeComputer = director.buildOfficeComputer(builder);
            System.out.println("Office Computer: " + officeComputer);
        }
    }
    ```