### 상태패턴
- 개념
    - 객체의 상태에 기반하여 객체가 특정 상태에 있을 때 어떤 행동을 수행할지를 정의하는 디자인 패턴
    - 객체의 행동을 여러 상태 객체에 위임하여 상태에 따라 수행되는 행동이 변하도록 함
    - 상태를 조건문으로 검사해서 상태에 따른 행위를 수행하는 게 아니라, 상태를 객체화 해서 상태가 행동을 할 수 있도록 위임하는 패턴
- 구성요소
    - **Context :**
        - 현재 상태를 가지고 있는 객체로, 상태에 따른 행동을 각 상태 객체에 위임
    - **State :**
        - 상태를 나타내는 인터페이스 또는 추상 클래스
        - 여러 상태에 대한 공통 메서드를 선언할 수 있음
    - **ConcreteState :**
        - State 인터페이스를 구현한 클래스
        - 각각의 상태에 대한 특정 동작을 정의
        - 상태에서 동작을 실행한 뒤 다른 상태로 변경하기도 함.
            - 예시) on 상태의 TV에 전원버튼을 누르는 동작 실행시, off 상태로 변경
    - **Client (클라이언트):**
        - Context를 사용하여 상태를 변경하고, 상태에 따라 다른 동작을 수행합니다.

- 사용 시기
    - **객체의 행동이 상태에 따라 다르게 변하는 경우:**
        - 객체의 행동이 상태에 의존적으로 변할 때 State 패턴을 사용하면 코드가 더 간결하고 유지보수가 쉬워짐
    - **상태 전이가 동적이고 복잡한 경우:**
        - 상태전이: 객체가 현재의 상태에서 다른 상태로 전환되는 것
        - 객체의 상태 전이가 프로그램 실행 중에 동적으로 결정되거나 상태 전이 로직이 복잡한 경우
    - **상태를 나타내는 클래스의 수가 많아질 때:**
        - 객체의 상태가 많은 경우, 각 상태를 별도의 클래스로 분리해서 관리할 수 있음
    - **Context의 인터페이스를 변경하고 싶지 않을 때:**
        - 객체의 인터페이스를 변경하지 않고도 특정 상태에 따른 행동을 변경하고 싶을 때
- 장점
    - **유연성과 확장성 향상:**
        - 새로운 상태를 추가하거나 기존 상태를 변경할 때 기존 코드를 수정하지 않고 확장 가능
    - **코드 가독성 향상:**
        - 상태와 관련된 코드가 각각의 상태 클래스에 캡슐화되기 때문에 코드의 가독성이 향상됨
    - **Context 클래스 단순화:**
        - Context 클래스의 코드가 각 상태 클래스로 분산되기 때문에 코드가 단순화되고 유지보수가 쉬워짐
- 단점
    - **클래스 증가:**
        - 상태마다 별도의 클래스가 필요하기 때문에 클래스의 수가 증가함
        - 작은 규모의 프로젝트에서는 부담이 될 수 있음
    - **상태 전이의 복잡성:**
        - 만약 상태 전이 로직이 복잡하고 상태 간의 의존성이 높다면, 이를 관리하는 것이 어려울 수 있음

    ```java
    //상태패턴 미적용
    public class OrderProcessor {
        private String orderStatus;
    
        public OrderProcessor() {
            this.orderStatus = "Created";
        }
    
        public void processOrder() {
            if (orderStatus.equals("Created")) {
                // 주문 처리 로직
                System.out.println("주문이 생성되었습니다. 주문을 처리 중입니다.");
            } else if (orderStatus.equals("Processing")) {
                // 처리 중 로직
                System.out.println("주문이 처리 중입니다. 상품을 준비합니다.");
            } else if (orderStatus.equals("Shipping")) {
                // 발송 중 로직
                System.out.println("상품이 발송 중입니다. 배송 정보를 업데이트합니다.");
            } else if (orderStatus.equals("Completed")) {
                // 완료 로직
                System.out.println("주문이 완료되었습니다. 고객에게 안내 메일을 전송합니다.");
            }
        }
    }
    
    //상태패턴 적용
    public class StatePatternOrderProcessorExample {
        public static void main(String[] args) {
            OrderProcessor orderProcessor = new OrderProcessor();
    
            // 주문이 생성된 상태에서 처리
            orderProcessor.processOrder();  
            
            // 주문 처리 중인 상태로 전환
            orderProcessor.setOrderState(new OrderProcessingState());
            orderProcessor.processOrder();  
    
            // 상품 발송 중인 상태로 전환
            orderProcessor.setOrderState(new OrderShippingState());
            orderProcessor.processOrder();  
    
            // 주문이 완료된 상태로 전환
            orderProcessor.setOrderState(new OrderCompletedState());
            orderProcessor.processOrder();  
        }
    }
    ```

    ```java
    //State interface
    public interface State {
      void selectProduct(VendingMachine vendingMachine, Product product);
    
      void insertCoin(VendingMachine vendingMachine, int amount);
    
      void dispenseProduct(VendingMachine vendingMachine);
    
      void cancelTransaction(VendingMachine vendingMachine);
    }
    
    //Context
    public class VendingMachine {
    
      State currentState;
      private Product selectedProduct;
      private int userBalance;
      public VendingMachine() {
        userBalance = 0;
        currentState = new IdleState();
      }
      public void setCurrentState(State currentState) {
        this.currentState = currentState;
      }
      
      public Product getSelectedProduct() {
        return selectedProduct;
      }
    
      public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
      }
    
      public int getUserBalance() {
        return userBalance;
      }
    
      public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
      }
    
      public void selectProduct(Product product) {
        currentState.selectProduct(this, product);
      }
    
      public void insertCoin(int amount) {
        currentState.insertCoin(this, amount);
      }
    
      public void dispenseProduct() {
        currentState.dispenseProduct(this);
      }
      public void cancelTransaction(){
        currentState.cancelTransaction(this);
      }
    }
    
    //ConcreteState
    public class IdleState implements State{
      @Override
      public void selectProduct(VendingMachine vendingMachine, Product product) {
        vendingMachine.setSelectedProduct(product);
        vendingMachine.setCurrentState(new PaymentPendingState());
        System.out.printf("Product %s (%s) is selected. Please insert coins. Balance : %d%n", product.name(), product.getPrice()+" won", vendingMachine.getUserBalance());
      }
    
      @Override
      public void insertCoin(VendingMachine vendingMachine, int amount) {
        System.out.println("Please select a product first.");
      }
    
      @Override
      public void dispenseProduct(VendingMachine vendingMachine) {
        System.out.println("Please select a product and insert coins.");
      }
    
      @Override
      public void cancelTransaction(VendingMachine vendingMachine) {
        if(vendingMachine.getUserBalance()>0){
          System.out.println("The transaction canceled. Return the balance : " + vendingMachine.getUserBalance());
          vendingMachine.setUserBalance(0);
          vendingMachine.setSelectedProduct(null);
        }else{
          System.out.println("No transaction to be canceled.");
        }
      }
    }
    
    //ConcreteState
    public class PaymentPendingState implements State {
      @Override
      public void selectProduct(VendingMachine vendingMachine, Product product) {
        System.out.println("Payment pending. Please complete the current transaction.");
      }
    
      @Override
      public void insertCoin(VendingMachine vendingMachine, int amount) {
        int totalAmount = vendingMachine.getUserBalance() + amount;
        vendingMachine.setUserBalance(totalAmount);
    
        if (totalAmount >= vendingMachine.getSelectedProduct().getPrice()) {
          vendingMachine.setCurrentState(new DispensingState());
          System.out.printf("Payment completed. Dispensing product. Inserted coins : %d%n", amount);
        } else {
          System.out.printf("Payment pending. Please insert more coins. Balance : %d%n", vendingMachine.getUserBalance());
        }
      }
    
      @Override
      public void dispenseProduct(VendingMachine vendingMachine) {
        System.out.println("Payment pending. Please insert coins.");
      }
    
      @Override
      public void cancelTransaction(VendingMachine vendingMachine) {
        System.out.println("The transaction canceled. Go back to Idle State.");
        vendingMachine.setCurrentState(new IdleState());
      }
    }
    
    //ConcreteState
    public class DispensingState implements State {
      @Override
      public void selectProduct(VendingMachine vendingMachine, Product product) {
        System.out.println("Dispensing in progress. Please wait.");
      }
    
      @Override
      public void insertCoin(VendingMachine vendingMachine, int amount) {
        System.out.println("Transaction in progress. Please wait for product dispensing.");
      }
    
      @Override
      public void dispenseProduct(VendingMachine vendingMachine) {
        Product selectedProduct = vendingMachine.getSelectedProduct();
        int productStock = selectedProduct.getStock();
        if (productStock > 0) {
          selectedProduct.setStock(selectedProduct.getStock()-1);
          vendingMachine.setUserBalance(vendingMachine.getUserBalance() - vendingMachine.getSelectedProduct().getPrice());
          System.out.printf("Product dispensed. Thank you for your purchase! Balance : %d%n", vendingMachine.getUserBalance());
          vendingMachine.setCurrentState(new IdleState());
        } else {
          System.out.println("Out of stock. Please make another selection.");
          vendingMachine.setCurrentState(new IdleState());
        }
      }
    
      @Override
      public void cancelTransaction(VendingMachine vendingMachine) {
        System.out.println("Can not cancel the transaction. The dispensing is in progress.");
      }
    }
    
    public enum Product {
      COFFEE(500,1), COKE(1500,2), JUICE(1200,1);
    
      private final int price;
      private int stock;
      
      public int getStock() {
        return stock;
      }
    
      public void setStock(int stock) {
        this.stock = stock;
      }
    
      Product(int price, int stock) {
        this.price = price;
        this.stock = stock;
      }
    
      public int getPrice() {
        return price;
      }
    
    }
    
    //Client
    public class Client {
      public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();
    
        vendingMachine.selectProduct(Product.COKE);
        vendingMachine.insertCoin(1000);
        vendingMachine.insertCoin(600);
        vendingMachine.dispenseProduct();
        System.out.println();
        vendingMachine.selectProduct(Product.COFFEE);
        vendingMachine.insertCoin(400);
        vendingMachine.dispenseProduct();
        System.out.println();
        vendingMachine.selectProduct(Product.COFFEE);
        vendingMachine.insertCoin(700);
        vendingMachine.dispenseProduct();
        System.out.println();
        vendingMachine.setSelectedProduct(Product.COFFEE);
        vendingMachine.insertCoin(1000);
        vendingMachine.cancelTransaction();
        vendingMachine.cancelTransaction();
      }
    }
    ```

- vs 전략패턴
    - 전략 패턴은 알고리즘을 객체화
      상태 패턴은 객체의 상태를 객체화를 한다.
    - 전략 객체는 그 전략만의 알고리즘 동작을 정의 및 수행
      상태 객체는 상태가 적용되는 대상 객체가 할수있는 일련의 모든 행동들을 정의 및 수행
    - 전략 패턴의 전략 객체는 입력값에 따라 전략 형태가 다양하게 될 수 있으니 인스턴스로 구성
      상태 패턴의 상태 객체는 정의된 상태를 서로 스위칭 하기에 메모리 절약을 위해 싱글톤으로 구성할 수 있음