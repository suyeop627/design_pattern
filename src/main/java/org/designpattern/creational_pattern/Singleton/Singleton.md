### 싱글톤 패턴
-개념
    - 어떤 클래스가 최대 한 번의 인스턴스만을 생성하도록 보장하고, 이에 대한 전역적인 접근점을 제공하는 디자인 패턴
    - 메모리 절약을 위해, 인스턴스가 필요할 때 똑같은 인스턴스를새로 만들지 않고 기존의 인스턴스를 가져와 활용하는 기법

- 기본 예시
    - 하기 싱글톤 패턴은 멀티 쓰레드 환경에서 유일한 객체를 보장하지 않음.
    - 두 쓰레드가 동시에 if문에 도달한 경우, 둘 다 현시점에서는 null이므로 인스턴스를 생성함

```java
class Singleton {
    // 싱글톤 클래스 객체를 담을 인스턴스 변수
    private static Singleton instance;

    // 생성자를 private로 선언 (외부에서 new 사용 X)
    private Singleton() {}
	
    // 외부에서 정적 메서드를 호출하면 그제서야 초기화 진행 (lazy)
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton(); // 오직 1개의 객체만 생성
        }
        return instance;
    }
}
```

- Enum 활용
    - enum은 애초에 멤버를 만들때 private로 만들고 한번만 초기화 하기 때문에 thread safe함. - JVM에서 싱글톤으로 관리됨
    - enum 내에서 상수 뿐만 아니라, 변수나 메서드를 선언해 사용이 가능하기 때문에, 이를 이용해 싱글톤 클래스 처럼 응용이 가능
    - 위의 Bill Pugh Solution 기법과 달리, 클라이언트에서 Reflection을 통한 공격에도 안전
    - 하지만 만일 싱글톤 클래스를 멀티톤(일반적인 클래스)로 마이그레이션 해야할때 처음부터 코드를 다시 짜야 되는 단점이 존재한다. (개발 스펙은 언제어디서 변경 될수 있기 때문에)
    - 클래스 상속이 필요할때, enum 외의 클래스 상속은 불가능하다.

    ```java
    enum SingletonEnum {
        INSTANCE;
    
        private final Client dbClient;
    	
        SingletonEnum() {
            dbClient = Database.getClient();
        }
    
        public static SingletonEnum getInstance() {
            return INSTANCE;
        }
    
        public Client getClient() {
            return dbClient;
        }
    }
    
    public class Main {
        public static void main(String[] args) {
            SingletonEnum singleton = SingletonEnum.getInstance();
            singleton.getClient();
        }
    }
    ```

- Bill Pugh Solution

    ```java
    class Singleton {
    
        private Singleton() {}
    
        // static 내부 클래스를 이용
        // Holder로 만들어, 클래스가 메모리에 로드되지 않고 getInstance 메서드가 호출되어야 로드됨
        private static class SingleInstanceHolder {
            private static final Singleton INSTANCE = new Singleton();
        }
    
        public static Singleton getInstance() {
            return SingleInstanceHolder.INSTANCE;
        }
    }
    ```

    - 멀티쓰레드 환경에서 안전하고 Lazy Loading(나중에 객체 생성) 도 가능한 완벽한 싱글톤 기법
    - 다만 클라이언트가 임의로 싱글톤을 파괴할 수 있다는 단점을 지님 (Reflection API, 직렬화/역직렬화를 통해)
        - 리플렉션을 사용하여 private 생성자를 우회하고 새로운 인스턴스를 생성할 수 있음
        - Serializable 인터페이스를 구현하고 있지 않습니다. 만약 싱글톤 인스턴스를 직렬화하고 다시 역직렬화할 때 객체의 일관성을 유지되지 않을 수 있음

    - **`getInstance`** 메서드가 호출되기 전까지는 **`SingleInstanceHolder`** 클래스가 초기화 되지 않아서 지연로딩 가능하며, 스레드 안전성도 보장됨.

- 로딩 관련 추가
    - 지연로딩 : 사용시점까지 **초기화 또는 로드**되는 것을 늦추는 전략.
    - 로딩 : 클래스의 바이너리코드가 메모리에 올라가서 사용할 수 있는 상태가 되는거
    - 링킹 : 클래스 로딩 후, 클래스의 바이트 코드에 대한 검증을 하거나 정적 변수들을 위한 메모리 공간을 할당하고 기본값으로 초기화
    - 초기화 : 클래스의 초기화 코드가 실행(static 블록이 실행되거나 클래스 변수들이 초기화됨)
    - 클래스의 로딩시점
        1. **클래스가 처음으로 참조될 때(사용될 때):** 클래스가 처음으로 참조되는 순간에 로딩됩니다. 예를 들어, 클래스의 인스턴스를 생성하거나, 클래스의 정적 필드 또는 정적 메서드에 접근할 때 발생합니다.
        2. **클래스의 인스턴스를 생성할 때:** **`new`** 키워드를 사용하여 클래스의 인스턴스를 생성할 때 클래스가 로딩됩니다.
        3. **클래스의 정적 메서드나 정적 필드에 접근할 때:** 클래스의 정적 멤버(메서드 또는 필드)에 접근할 때도 클래스가 로딩됩니다.

- 직렬화
    - 직렬화(Serialization)는 객체를 바이트 스트림으로 변환하는 프로세스를 의미합니다. Java에서는 **`Serializable`** 인터페이스를 통해 객체를 직렬화할 수 있습니다. **`Serializable`**은 마킹 인터페이스(marking interface)로, 어떤 클래스가 이 인터페이스를 구현하면 해당 클래스의 인스턴스들은 직렬화될 수 있음
    - **`Serializable`**을 구현한 클래스의 인스턴스는 객체의 상태와 구조를 유지한 채로 바이트 스트림으로 변환될 수 있음.
    - 이 바이트 스트림은 파일에 저장하거나 네트워크를 통해 전송하고, 나중에 역직렬화를 통해 객체로 복원 가능
    - **Serializable 인터페이스:**
        - **`java.io.Serializable`** 인터페이스는 아무런 메서드를 가지지 않는 마킹 인터페이스로, 클래스가 **`Serializable`**을 구현하면 해당 클래스의 인스턴스는 직렬화가 가능해짐
    1. **직렬화:**
        - 직렬화는 객체를 바이트 스트림으로 변환하는 과정으로, **`ObjectOutputStream`** 클래스를 사용하여 객체를 직렬화할 수 있음

        ```java
        // 객체를 직렬화하여 파일에 저장하는 예제
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.ser"))) {
            MyClass obj = new MyClass();
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ```

    2. **역직렬화:**
        - 역직렬화는 직렬화된 바이트 스트림을 다시 객체로 변환하는 과정으로, **`ObjectInputStream`** 클래스를 사용하여 역직렬화 가능

        ```java
        // 파일에서 직렬화된 객체를 읽어와 역직렬화하는 예제
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.ser"))) {
            MyClass obj = (MyClass) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ```

    3. **serialVersionUID:**
        - **`serialVersionUID`**는 직렬화된 클래스의 버전을 식별하는 데 사용되는 ID로, 클래스가 변경될 때마다 **`serialVersionUID`**를 업데이트하여 버전 관리를 수행할 수 있음

        ```java
        private static final long serialVersionUID = 1L;
        ```

    4. **Transient 키워드:**
        - 객체 내의 특정 필드를 직렬화에서 제외하고 싶을 때, 해당 필드에 **`transient`** 키워드를 사용해서 처리할 수 있음

        ```java
        private transient int sensitiveData;
        ```


    직렬화를 사용하면 객체를 다양한 환경에서 전송하거나 저장할 수 있으며, Java에서는 네트워크 통신, 데이터베이스 저장, 객체의 상태를 영속적으로 저장하는 등 다양한 상황에서 활용됨