### 프록시패턴

- 개념
    - 어떤 다른 객체에 대한 인터페이스를 제공하여, 해당 객체에 대한 접근을 제어하거나 대리할 수 있도록 하는 디자인 패턴
    - 객체의 대리자(대리 객체)를 통해 다른 객체에 대한 접근을 제어하는 디자인 패턴


- 구성요소
    - Subject : Proxy와 RealSubject를 하나로 묶는 인터페이스 (다형성)
        - 대상 객체와 프록시 역할을 동일하게 하는 추상 메소드 operation() 를 정의함
        - 인터페이스가 있기 때문에 클라이언트는 Proxy 역할과 RealSubject 역할의 차이를 의식할 필요 없이 사용가능함.
    - RealSubject : 원본 대상 객체
    - Proxy : 대상 객체(RealSubject)를 중계할 대리자 역할
        - 프록시는 대상 객체를 합성(composition)함
        - 프록시는 대상 객체와 같은 이름의 메서드를 호출하며, 별도의 로직을 수행 할 수도 있음
        - 프록시는 흐름제어만 할 뿐 결과값을 조작하거나 변경시키면 안 됨.
    - Client : Subject 인터페이스를 이용하여 프록시 객체를 생성해서 이용함
        - 클라이언트는 프록시를 중간에 두고 프록시를 통해서 RealSubject와 데이터를 주고 받을 수 있음

- 장점
    - **접근 제어 및 보안 강화:**
        - 프록시는 실제 객체에 대한 접근을 제어할 수 있어서, 특정 작업이나 기능을 필요로 하는 클라이언트에 대한 접근을 제한하거나 보안을 강화할 수 있음
    - **리소스 관리:**
        - 프록시는 실제 객체의 생성 및 초기화를 지연시킬 수 있음
        - 이를 통해 시스템 부하를 줄이고, 필요한 시점에 리소스를 할당할 수 있게함
    - **캐싱:**
        - 프록시는 결과를 캐싱하여 동일한 요청에 대한 중복 계산을 피하고 성능을 향상시킴
    - **원격 프록시:**
        - 원격 프록시는 분산 시스템에서 객체의 로컬이 아닌 원격 위치에서 객체에 접근할 수 있도록 할 수 있음
    - **로그 및 감사:**
        - 프록시를 사용하여 객체에 대한 작업을 로깅하거나 감사하는 데 활용할 수도 있음
- 단점
    - **복잡성 증가:**
        - 프록시 패턴을 적용하면 중간에 대리 객체가 추가되므로, 전반적인 시스템 구조가 더 복잡해짐
    - **성능 저하:**
        - 프록시를 통해 객체에 접근하는 과정에서 성능 저하가 발생할 수 있음
        - 특히 원격 프록시를 사용할 때는 통신 비용이 추가로 발생할 수도 있음
    - **잘못 사용시, 보안 문제 발생:**
        - 프록시 패턴을 잘못 구현하면, 프록시를 우회하여 직접 실제 객체에 접근하는 것을 막지 못할 수도 있음

- 사용시기
    - **원격 객체 접근:**
        - 객체가 원격 서버에 있고, 클라이언트가 이 객체에 접근해야 할 때, 원격 프록시를 사용하여 네트워크 통신을 통해 원격 객체에 접근이 가능함
    - **보안 제어:**
        - 특정 객체에 대한 접근을 제어하고 싶을 때, 프록시를 사용하여 인증 및 권한 검사 등의 보안 기능을 추가할 수 있음.
    - **캐싱:**
        - 계산 비용이 큰 연산의 결과를 캐싱하여 동일한 요청에 대한 중복 계산을 피하고 성능을 향상시킬 수 있음
    - **지연 로딩 (Lazy Loading):**
        - 객체의 생성 및 초기화가 비용이 많이 들거나, 특정 시점에 객체가 실제로 필요한 경우에 프록시를 사용하여 필요한 시점에 객체를 초기화할 수 있음.
    - **트랜잭션 관리:**
        - 데이터베이스 트랜잭션과 관련하여 특정 작업이 수행되기 전이나 후에 추가 작업이 필요한 경우에 프록시를 사용하여 트랜잭션 관리를 구현할 수도 있음

- 프록시 패턴 종류
    - 가상 프록시
        - 실제 객체의 생성 또는 초기화에 비용이 많이 드는 경우, 프록시는 객체를 생성하지 않고 가벼운 대리 객체로 대체하여 필요할 때에만 실제 객체를 생성
        - 지연 초기화 방식
            - 서비스가 시작될 때 객체를 생성하는 대신에 객체 초기화가 실제로 필요한 시점에 초기화될수 있도록 지연할 수 있음
        - 가끔 필요하지만 항상 메모리에 적재되어 있는 무거운 서비스 객체가 있는 경우
        - 이 구현은 실제 객체의 생성에 많은 자원이 소모 되지만 사용 빈도는 낮을 때 쓰는 방식

    ```java
    // 공통 인터페이스
    // 가상 프록시와 실제 객체가 구현하는 인터페이스
    public interface Video {
      //썸네일은 프록시에서 처리 가능하도록 함
      void showThumbnailImage();
      //영상 재생(무거운작업)이 필요할 때, 실제 비디오 객체 생성함.
      void playVideo();
    }
    
    // 실제 객체
    // 실제로 무거운 작업을 수행하는 클래스
    public class RealVideo implements Video {
      private final String videoPath;
      private final String thumbnailPath;
    
      public RealVideo(String videoPath, String thumbnailPath) {
        this.videoPath = videoPath;
        this.thumbnailPath = thumbnailPath;
        loadVideoFromDisk();
      }
    
      //무거운 작업을 가정
      private void loadVideoFromDisk() {
        try{
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        System.out.println("Loading video from disk: " + videoPath);
      }
    
      @Override
      public void showThumbnailImage() {
        System.out.println("Showing Thumbnail Image : "+thumbnailPath);
      }
    
      @Override
      public void playVideo() {
        System.out.println("Playing video: " + videoPath);
      }
    }
    
    // 가상 프록시
    public class VirtualProxyVedio implements Video {
      private final String videoPath;
      private final String thumbnailPath;
      private RealVideo realVideo;
    
      public VirtualProxyVedio(String videoPath, String thumbnailPath){
        this.videoPath = videoPath;
        this.thumbnailPath = thumbnailPath;
      }
    
      @Override
      public void showThumbnailImage() {
        System.out.println("Showing Thumbnail Image : "+thumbnailPath);
      }
    
      @Override
      public void playVideo() {
        if(realVideo==null){
          realVideo = new RealVideo(videoPath, thumbnailPath);
          System.out.println("RealVideo is created");
        }
        realVideo.playVideo();
      }
    }
    
    // 클라이언트 측 코드
    public class Client {
      public static void main(String[] args) {
          // 가상 프록시를 통해 Video 객체를 사용함
          Video video = new VirtualProxyVedio("example.mp4","thumbnail.jpg");
          
          video.showThumbnailImage();
    
          video.showThumbnailImage();
    
        // 영상을 재생하는 시점에 실제 객체가 생성되고, 무거운 작업이 수행됨
          video.playVideo();
        }
    }
    ```

    - 보호 프록시
        - 보호 프록시 패턴은 객체에 대한 접근을 제어하여 특정 작업을 수행하거나 허용되지 않은 작업을 방지하는 데 사용
        - 프록시가 대상 객체에 대한 자원으로의 엑세스 제어(접근 권한)
        - 특정 클라이언트만 서비스 객체를 사용할 수 있도록 하는 경우
        - 프록시 객체를 통해 클라이언트의 자격 증명이 기준과 일치하는 경우에만 서비스 객체에 요청을 전달할 수 있게 함.

        ```java
        // 공통 인터페이스
        public interface Subject {
          void response();
        }
        // 실제 객체
        public class RealSubject implements Subject {
          @Override
          public void response() {
            System.out.println("response from RealSubject");
          }
        }
        // 보호 프록시
        public class ProtectionProxy implements Subject {
          RealSubject realSubject = new RealSubject();
          private final String password;
        
          public ProtectionProxy(String password){
            this.password = password;
          }
        
          @Override
          public void response() {
            if(authenticate()){
              System.out.println("ProtectionProxy : do something before request");
              realSubject.response();
              System.out.println("ProtectionProxy : do something after request");
            }else{
              System.out.println("ProtectionProxy : forbidden");
            }
          }
        
          private boolean authenticate() {
            return "password".equals(password);
          }
        }
        // 클라이언트 측 코드
        public class Client {
          public static void main(String[] args) {
            //보호 프록시
            Subject protectionProxy = new ProtectionProxy("password");
            protectionProxy.response();
        
            Subject protectionProxy2 = new ProtectionProxy("wrong_password");
            protectionProxy2.response();
        
          }
        }
        ```

        - 원격 프록시
            - 객체가 다른 주소 공간에 존재하는 경우, 해당 객체에 대한 접근을 제어하고 대리자 역할을 하는 프록시 패턴
            - 이는 주로 네트워크 상에서 객체에 접근해야 하는 경우나 분산 시스템에서 사용
            - 프록시 클래스는 로컬에 있고, 대상 객체는 원격 서버에 존재하는 경우
            - 프록시 객체는 네트워크를 통해 클라이언트의 요청을 전달하여 네트워크와 관련된 작업들을 처리하고 결과값만 반환
            - 클라이언트 입장에선 프록시를 통해 객체를 이용하는 것이니 원격이든 로컬이든 신경 쓸 필요가 없으며, 프록시는 진짜 객체와 통신을 대리함
            - RMI(Remote Method Invocation)
                - 원격 객체 간 통신을 지원하기 위한 Java의 기술로, 로컬 프록시 객체를 통해 원격 서버에 위치한 객체의 메서드를 호출할 수 있음
                - 구성요소
                    - **원격 객체 (Remote Object):**
                        - RMI는 분산 시스템에서 객체 간 통신을 지원하기 위해, 원격 객체를 정의합니다. 이 객체는 클라이언트가 로컬 객체처럼 메서드를 호출할 수 있도록 함
                    - **원격 인터페이스 (Remote Interface):**
                        - 원격 객체가 구현하는 인터페이스로, 이 인터페이스는 **`java.rmi.Remote`** 인터페이스를 상속하며, 원격으로 호출될 수 있는 메서드를 정의함.
                    - **RMI 레지스트리 (RMI Registry):**
                        - RMI 레지스트리는 원격 서버에서 실행되며, 원격 객체의 등록과 검색을 담당
                        - 클라이언트는 RMI 레지스트리를 통해 원격 객체를 찾아와서 사용
                    - **스텁 (Stub) 및 스켈레톤 (Skeleton):**
                        - RMI는 스텁과 스켈레톤을 내부적으로 사용하여 클라이언트와 서버 간의 통신을 관리
                        - 스텁은 클라이언트 측에서, 스켈레톤은 서버 측에서 동작
                            - 객체 간의 통신을 중개하고 원격 호출을 처리
                - 진행 과정
                    1. **클라이언트 측:**
                        - 클라이언트는 **`Naming.lookup`** 메서드를 사용하여 RMI Registry에서 원격 객체에 대한 참조를 얻음
                        - 이때 실제로는 RMI 라이브러리가 자동으로 `Stub`을 생성하고, 이 스텁을 클라이언트에게 반환
                    2. **스텁(Stub) 측:**
                        - 클라이언트가 스텁을 통해 원격 객체의 메서드를 호출하면, 스텁은 해당 메서드 호출을 래핑하고, 네트워크를 통해 서버로 전송
                        - 스텁은 호출된 메서드에 대한 정보와 매개변수를 직렬화하여 서버로 전송함.
                    3. **네트워크를 통한 전송:**
                        - 클라이언트의 스텁이 원격 서버의 `Skeleton`에게 메시지를 전송함
                        - 이때 원격 서버의 주소와 스켈레톤에 대한 정보가 포함된 메시지가 전송됨
                    4. **서버 측:**
                        - 서버는 메시지를 수신하고, 스켈레톤은 메시지를 해석하여 실제 원격 객체의 메서드를 호출
                        - 스켈레톤은 호출된 메서드의 결과를 클라이언트로 다시 반환
                    5. **스텁을 통한 결과 반환:**
                        - 서버에서 반환된 결과는 스켈레톤을 통해 스텁으로 전송
                        - 스텁은 결과를 받아서 클라이언트에게 반환하고, 클라이언트는 마치 로컬 메서드를 호출한 것처럼 결과를 받아 사용
                    - 이 과정에서 스텁과 스켈레톤은 RMI 라이브러리에 의해 자동으로 생성되고 관리됨.

                -

        ```java
        
        //-------------------------remote server---------------------------------
        // 공통 인터페이스
        //Remote : 이 인터페이스를 구현하는 클래스는 RMI를 통해 원격으로 호출될 수 있는 메서드를 제공해야 함.
         public interface Calculator extends Remote {
         // RemoteException은 RMI에서 발생할 수 있는 예외를 처리하기 위한 선언으로, 구현 메서드에서 해당 예외 처리를 강제하도록, interface에서 정의함
          int add(int a, int b) throws RemoteException;
          int subtract(int a, int b) throws RemoteException;
        }
        
        // 실제 객체 (원격 서버에 존재)
        public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
          //UnicastRemoteObject
          //UnicastRemoteObject 클래스를 상속받은 클래스의 객체는 RMI에서 원격으로 사용 가능한 객체로 만들어짐.
          // 이를 통해 해당 객체의 메서드를 원격에서 호출할 수 있게 됩니다.
          //원격객체는 RMI Registry에 등록되어, 클라이언트에서 찾게됨. 클라이언트는 RMI Registry를 통해 서버에서 실행 중인 원격 객체를 검색하여 호출할 수 있음.
          protected CalculatorImpl() throws RemoteException {
            super();
          }
        
          @Override
          public int add(int a, int b) throws RemoteException {
            return a + b;
          }
        
          @Override
          public int subtract(int a, int b) throws RemoteException {
            return a - b;
          }
        }
        
        //-------------------------local server---------------------------------
        //Calculator 인터페이스의 원격 객체를 대행하는 프록시 역할 수행
        public class CalculatorProxy {
          private Calculator calculator;
          // 생성자에서 원격 객체에 대한 룩업을 수행
          public CalculatorProxy() {
            try {
              // RMI Registry에 등록된 CalculatorImpl 객체를 찾아서 사용
              // "//localhost/CalculatorService"는 서버에서 RMI Registry에 등록된 객체의 위치
              //port를 명시하지 않으면 기본 포트인 1099 사용함
              this.calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
              System.out.println("Calculator obtained from Remote Method Invocation");
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        //원격 객체의 메서드를 실행할때마다 새로운 요청이 생성되고, 실제 계산은 원격 서버에서 이뤄진 다음에 결과를 반환받아서 사용함
          public int add(int a, int b) {
            try {
              return calculator.add(a, b);
            } catch (Exception e) {
              e.printStackTrace();
              return 0;
            }
          }
        
          public int multiply(int a, int b) {
            try {
              return calculator.subtract(a, b);
            } catch (Exception e) {
              e.printStackTrace();
              return 0;
            }
          }
        }
        
        // 클라이언트 측 코드
        public class Client {
          public static void main(String[] args) {
            try {
              // 원격 서버에서 CalculatorImpl 클래스가 실행중이어야 함.
              CalculatorProxy proxy = new CalculatorProxy();
        
              // 프록시를 통한 원격 객체의 메서드 호출
              int resultAdd = proxy.add(10, 5);
              System.out.println("Result of addition: " + resultAdd);
        
              int resultMultiply = proxy.multiply(3, 4);
              System.out.println("Result of multiplication: " + resultMultiply);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }
        ```