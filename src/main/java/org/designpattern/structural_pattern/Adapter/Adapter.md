### 어댑터 패턴(Adapter Pattern)
- 개념
    - 소프트웨어 디자인 패턴 중 하나로, 호환되지 않는 인터페이스를 함께 동작할 수 있도록 변환하는 구조적 패턴입니다.
    - 이 패턴은 기존의 코드를 수정하지 않고, 이미 존재하는 클래스나 인터페이스를 다른 인터페이스로 감싸서 사용할 수 있게 해줍니다.
    - 어댑터가 Legacy 인터페이스를 감싸서 새로운 인터페이스로 변환하기 때문에 Wrapper 패턴이라고도 불리운다.
- 구성
1. **Target:**
    - 클라이언트가 기대하는 인터페이스
    - 클라이언트는 이 인터페이스를 통해 작업을 수행
    - Adaptee에 Adapter를 적용해서 Target으로 활용할 수 있게 하는게 목표
2. **Adaptee:**
    - 클라이언트와는 다른 인터페이스를 가진 기존의 클래스 또는 모듈
    - 이 클래스의 메서드를 사용하려면 어댑터를 통해 클라이언트에게 맞는 인터페이스로 변환해야 함
3. **Adapter:**
    - Target 인터페이스와 Adaptee 클래스를 연결하는 클래스
    - 클라이언트가 기대하는 Target 인터페이스를 구현하면서, 내부에서는 Adaptee의 메서드를 호출하여 작업을 수행하여 Target과 Adaptee의 불일치 해결
    - 어댑터 클래스는 클래스 어댑터와 객체 어댑터가 있음
        - 클래스 어댑터
            - 어댑터 클래스가 타겟 인터페이스를 구현하면서, 동시에 적응자(Adaptee) 클래스를 상속하는 방식
        - 객체 어댑터
            - 어댑터 클래스가 타겟 인터페이스를 구현하면서, 동시에 적응자(Adaptee) 클래스의 인스턴스를 갖는 방식
- 사용시기
    - **기존 클래스를 재사용할 때:**
        - 이미 존재하는 클래스가 필요한 인터페이스를 구현하지 않는 경우, 어댑터 패턴을 사용하여 해당 클래스를 클라이언트에 필요한 인터페이스로 제공할 수 있음
    - **클래스의 인터페이스가 변경되었을 때:**
        - 기존에 사용 중인 클래스의 인터페이스가 변경되었지만, 클라이언트 코드를 수정하지 않고 계속 사용하고 싶은 경우, 어댑터 패턴을 도입하여 새로운 인터페이스에 맞게 변경할 수 있음
    - **인터페이스의 일부 메서드만 사용할 때:**
        - 어떤 인터페이스에는 필요한 일부 메서드만 사용하고 싶을 때, 해당 인터페이스의 모든 메서드를 직접 구현하는 대신 어댑터를 이용하여 필요한 부분만 사용할 수 있음
        - 예시

        ```java
        // 기존 로깅 라이브러리의 인터페이스
        public interface LoggingLibrary {
            void logMessage(String message);
            void logError(String error);
            // 다양한 다른 메서드들...
        }
        
        // 새로운 프로젝트에서 사용할 인터페이스 (일부 메서드만 필요)
        public interface Logger {
            void log(String message);
        }
        
        // 어댑터 클래스: LoggingLibrary를 Logger 인터페이스에 맞게 적응
        public class LoggingLibraryAdapter implements Logger {
            private LoggingLibrary loggingLibrary;
        
            public LoggingLibraryAdapter(LoggingLibrary loggingLibrary) {
                this.loggingLibrary = loggingLibrary;
            }
        
            @Override
            public void log(String message) {
                // LoggingLibrary의 적절한 메서드 호출
                loggingLibrary.logMessage(message);
            }
        }
        
        public class Client {
            public static void main(String[] args) {
                // 기존 로깅 라이브러리 인스턴스 생성
                LoggingLibrary loggingLibrary = new ConcreteLoggingLibrary();
        
                // 어댑터를 통해 Logger 인터페이스를 사용
                Logger logger = new LoggingLibraryAdapter(loggingLibrary);
        
                // 클라이언트 코드는 Logger 인터페이스를 통해 로깅 작업 수행
                logger.log("Log this message");
            }
        }
        ```

    - **클래스와 인터페이스 간의 결합도를 낮추고자 할 때:**
        - 어댑터 패턴을 사용하면 기존 클래스와 새로운 인터페이스 간의 결합도를 낮출 수 있음
        - 클라이언트는 어댑터를 통해 인터페이스와 상관없이 기존 클래스를 사용할 수 있음
- 장점
    - **재사용성 증가:**
        - 기존의 클래스를 새로운 인터페이스에 적응시킬 수 있으므로, 재사용성이 증가함
    - **유연성 향상:**
        - 어댑터 패턴을 사용하면 클라이언트는 기존 클래스의 변경 없이도 새로운 인터페이스를 활용할 수 있음
    - **결합도 감소:**
        - 어댑터를 통해 인터페이스와 클래스 간의 결합도를 낮출 수 있음
- 단점
    1. **복잡성 추가:**
        - 어댑터 패턴을 도입하면 코드의 복잡성이 증가할 수 있음
        - 특히 클래스 어댑터에서는 다중 상속을 사용해야 하므로 언어에 따라 제약사항이 발생할 수도 있음
            - 자바는 다중상속이 불가능해서, 어댑터에 다른 클래스를 상속하는 게 불가능함 → 객체 어댑터 사용하면 됨.
    2. **런타임 비용:**
        - 어댑터가 추가된 경우, 런타임에 어댑터를 통한 변환 비용이 추가될 수 있음

- 객체 어댑터

    - 어댑터 클래스가 기존 클래스를 포함(컴포지션)하는 방식으로, 요청에 대한 처리를 멤버에게 위임하는 방식
    - 이 패턴에서는 어댑터 클래스가 대상(Target) 인터페이스를 구현하면서, 실제로는 어댑터 내부에 기존 클래스의 인스턴스를 가고 있음
    - **Composition 방식이라서 런타임중에 유연하게 Adaptee를 결정할 수 있음**
    - Adaptee 객체를 필드 변수로 저장해야 되기 때문에 공간 차지 비용이 발생함

- 클래스 어댑터

    - 어댑터 클래스가 대상(Target) 인터페이스를 구현하면서, 기존 클래스를 **상속하는 방식**
    - 이 패턴에서는 어댑터 클래스가 대상 인터페이스의 메서드를 구현하면서, 동시에 기존 클래스의 기능을 사용할 수 있음
    - Adaptee를 상속했기 때문에 따로 객체 구현없이 바로 코드 재사용이 가능함
    - 자바에서는 다중 상속 불가 문제 때문에 전반적으로 객체 어댑터가 선호됨

    ```java
    // Target 인터페이스
    public interface InternalSystem {
      void performInternalAction();
      default void sayHello(){
        System.out.println("Hello from Internal System");
      }
    }
    
    //기존 시스템으로, 외부 시스템으로 대체될 클래스
    public class InternalSystemImpl implements InternalSystem {
      @Override
      public void performInternalAction() {
        System.out.println("Performing action in the internal system.");
      }
    }
    
    //Adaptee 클래스
    public class ExternalSystem {
      public void performExternalAction() {
        System.out.println("Performing action in the external system.");
      }
    }
    
    // Class Adapter 
    public class ClassAdapter extends ExternalSystem implements InternalSystem{
      @Override
      public void performInternalAction() {
        performExternalAction();
      }
    }
    
    //Object Adapter
    public class ObjectAdapter implements InternalSystem{
      private final ExternalSystem externalSystem;
    
      public ObjectAdapter(ExternalSystem externalSystem) {
        this.externalSystem = externalSystem;
      }
    
      @Override
      public void performInternalAction() {
        externalSystem.performExternalAction();
      }
    }
    
    //클라이언트
    public class Client {
      public static void main(String[] args) {
        //클래스 어댑터
        InternalSystem internalSystemAdapter = new ClassAdapter();
        internalSystemAdapter.performInternalAction();
        internalSystemAdapter.sayHello();
    
        //객체 어댑터
        internalSystemAdapter = new ObjectAdapter(new ExternalSystem());
        internalSystemAdapter.performInternalAction();
        internalSystemAdapter.sayHello();
      }
    }
    ```