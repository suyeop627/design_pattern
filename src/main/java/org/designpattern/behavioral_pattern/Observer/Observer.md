### 옵저버 패턴
- 개념
    - 객체 사이에 일 대 다 의존성을 정의하는 패턴으로, 한 객체의 상태가 변할 때, 그 객체에 의존하는 다수의 객체들에게 자동으로 알림을 전달하고, 대상은 그 알림을 받아 행위를 취함
    - 옵저버 패턴은 여타 다른 `디자인 패턴들과 다르게 일대다(one-to-many) 의존성을 가지는데, 주로 분산 이벤트 핸들링 시스템을 구현하는 데 사용
    - Pub/Sub(발행/구독) 모델로도 알려져있음
- 구성요소
    - **Subject (주체):**
        - 상태를 관리하고 옵저버들에게 변경 사항을 알리는 역할
        - 관찰 대상자를 정의하는 클래스
        - 하나 이상의 Observer 객체를 가지고 있으며, Observer에 대한 등록, 해제, 알림 등의 메서드를 제공함
    - **ConcreteSubject (구체적인 주체):**
        - Subject를 구현한 클래스
        - Observer에 대한 등록, 해제, 알림 등의 메서드를 제공
        - 상태를 저장하고, 상태 변경 시 옵저버들에게 전달함(notify)
        - 관찰 대상 / 발행자 / 게시자
        - Observer들을 List, Map, Set  등으로 모아 합성(compositoin)하여 가지고 있음
    - **Observer (옵저버):**
        - Subject의 상태 변경을 감지하고 그에 대응하여 업데이트를 수행하는 인터페이스
        - 구독자들을 묶는 인터페이스 (다형성)
    - **ConcreteObserver (구체적인 옵저버):**
        - Observer를 구현한 클래스로, Subject의 알림을 받아 상태 변경을 감지하고, 그에 따른 동작을 수행
        - 관찰자 / 구독자 / 알림 수신자.
- 사용시기
    - **상태 변화를 여러 객체에게 알려야 할 경우:**
        - 객체의 상태 변화에 따라 여러 객체들에게 알림을 주고, 갱신이 필요한 경우
            - 예를 들어, 주제 객체의 데이터가 변경될 때 그 정보를 여러 뷰에 자동으로 반영하는 경우
    - **이벤트 처리 구현 시:**
        - 이벤트 기반 시스템에서는 옵저버 패턴이 이벤트 핸들링을 위한 일반적인 패턴으로 사용됨
- 장점
    - **느슨한 결합(Loose Coupling):**
        - 주체와 옵저버 간의 관계가 느슨하기 때문에, 주체나 옵저버 중 하나를 수정해도 다른 쪽에 큰 영향을 주지 않아 유지보수가 쉬워짐
        - Observer 인터페이스를 구현한 클래스는 모두 등록 가능
    - **확장성:**
        - 새로운 옵저버를 추가하거나 기존 옵저버를 제거하는 것이 비교적 쉬움
        - 시스템에 유연성을 부여함
    - **이벤트 기반 프로그래밍:**
        - 이벤트 핸들링이 필요한 프로그램에서 효과적으로 사용됨
- 단점
    - **성능 문제:**
        - 많은 수의 옵저버가 있을 경우, 주체의 상태가 변경될 때마다 모든 옵저버에게 알림을 주는 과정이 오버헤드를 일으킬 수 있음
            - **오버헤드(overhead) :** 어떤 처리를 하기 위해 들어가는 간접적인 처리 시간 · 메모리 등
    - **순서의 의존성:**
        - 옵저버들 간에 순서가 중요한 경우, 정확한 순서를 유지하는 것이 어려울 수 있음
    - **불필요한 업데이트:**
        - 옵저버 패턴에서는 상태가 변경될 때마다 모든 옵저버에게 알림이 가기 때문에, 옵저버 중 일부는 실제로 해당 상태에 대한 관심이 없더라도 업데이트를 받을 수 있음

    ```java
    //Subject interface
    interface Subject {
      void addObserver(Observer observer);
      void removeObserver(Observer observer);
      void notifyObservers();
    }
    
    // ConcreteSubject
    class Store implements Subject, Runnable {
      private List<Observer> waitingList = new ArrayList<>();
      int stock =0;
      private final StockManager stockManager;
    
      public Store(StockManager stockManager) {
        this.stockManager = stockManager;
      }
      public synchronized void changeStock(int value){
        this.stock+=value;
      }
      @Override
      public void addObserver(Observer observer) {
        waitingList.add(observer);
      }
    
      @Override
      public void removeObserver(Observer observer) {
        waitingList.remove(observer);
      }
    
      @Override
      public void notifyObservers() {
        String message = "Message from store : Item have restocked. Remaining items : %d, Customers waiting items : %d".formatted(stock, waitingList.size());
        for (Observer customer : waitingList) {
          customer.update(message);
        }
      }
    
      public synchronized boolean sellItem(Customer customer) {
        if (stock > 0) {
          changeStock(-1);
          removeObserver(customer); // 손님 구독 해지
          System.out.println("Store : " + customer.getName() + " is removed from waiting list.");
          return true;
        } else {
          if(!waitingList.contains(customer)){
            addObserver(customer);
            System.out.println(customer.getName() + " is added to waiting list. Remaining items : "+stock);
          }else{
            System.out.println(customer.getName() + " is fail to buy item. Remaining items : "+stock);
          }
          return false;
        }
      }
    
      @Override
      public void run() {
        while (true) {
          try {
            Thread.sleep(5000);
            stockManager.restock(this);
            notifyObservers();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
    
    //Observer interface - 관찰자 역할
    interface Observer {
      void update(String message);
    }
    
    // ConcreteObserver - Subject의 알림을 받음
    public class Customer implements Observer, Runnable {
      private String name;
      private Store store;
      private boolean isBoughtItem = false;
    
      public Customer(String name, Store store) {
        this.name = name;
        this.store = store;
      }
    
      @Override
      public void update(String message) {
        System.out.println(message);
      }
    
      private void requestItem(){
        isBoughtItem = store.sellItem(this);
        if(isBoughtItem){
          System.out.println(this.name + " is bought item.");
        }
      }
    
      @Override
      public void run() {
        while (!isBoughtItem) {
          try {
            Thread.sleep(1000);
            requestItem();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    
      public String getName() {
        return name;
      }
    }
    
    public class StockManager{
      public void restock(Store store) {
        store.changeStock(3);
        System.out.println("Item restocked. Remaining items : " + store.stock);
      }
    }
    
    public class Client {
      public static void main(String[] args) {
        StockManager stockManager = new StockManager();
        Store store = new Store(stockManager);
        List<Customer> customers = new ArrayList<>();
    
        for (int i = 1; i <= 5; i++) {
          Customer customer = new Customer("손님" + i, store);
          customers.add(customer);
        }
    
        Thread stockManagerThread = new Thread(store);
        stockManagerThread.start();
    
        for (Customer customer : customers) {
          Thread customerThread = new Thread(customer);
          customerThread.start();
        }
      }
    }
    ```

- Observer / Observable
    - 자바에서 Observer 인터페이스와 Observable클래스를 지원해서 옵저버 패턴을 쉽게 구현할 수도 있음
    - 하지만 Observable이 클래스이며, setChanged()메서드가 protected(하위클래스에서만 호출 가능)기 때문에 꼭 상속받아서 구현해야한다는 단점이 존재함.

    ```jsx
    // 주제(Subject) 역할을 하는 클래스
    class NewsAgency extends Observable {
      private String news;
    
      public void setNews(String news) {
        this.news = news;
        setChanged(); // Observable 클래스의 메서드로, 주제(Subject)의 상태가 변경되었음을 나타내는 역할.
                       //setChaged() 메서드를 먼저 실행하고 notifyObservers()를 실행해야함.
        notifyObservers(news); // Observer에게 변경된 데이터를 전달
      }
    }
    
    // 관찰자(Observer) 역할을 하는 클래스
    class NewsChannel implements Observer {
      private String channelName;
    
      public NewsChannel(String channelName) {
        this.channelName = channelName;
      }
    
      // 주제에서 데이터를 전달받아 업데이트하는 메서드
      @Override
      public void update(Observable o, Object arg) {
        String news = (String) arg;
        System.out.println(channelName + " received news: " + news);
      }
    
    }
    
    public class Client {
      public static void main(String[] args) {
        // 주제 생성
        NewsAgency newsAgency = new NewsAgency();
    
        // 관찰자 생성
        NewsChannel channel1 = new NewsChannel("Channel 1");
        NewsChannel channel2 = new NewsChannel("Channel 2");
    
        // 주제에 관찰자 등록
        newsAgency.addObserver(channel1);
        newsAgency.addObserver(channel2);
    
        // 주제에서 뉴스 업데이트
        newsAgency.setNews("Breaking News: Important event!");
    
        // 주제에서 또 다른 뉴스 업데이트
        newsAgency.setNews("Weather Forecast: Sunny day ahead!");
      }
    }
    ```