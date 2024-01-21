### 커맨드 패턴
- 개념
    - 요청을 객체의 형태로 캡슐화하여 매개변수화하고, 이를 큐에 저장하거나 로그에 기록하며, 나중에 취소할 수 있는 연산을 지원하는 패턴
    - 요청을 하는 객체와 그 요청을 처리할 객체를 분리시키는 역할을 수행함
    - 요청을 요청에 대한 모든 정보가 포함된 독립 실행형 객체로 변환하는 패턴

- 구성요소
    - **Command :**
        - 명령을 나타내는 인터페이스로, 실제 요청을 처리할 excute메서드를 선언함
    - **ConcreteCommand :**
        - Commend 인터페이스를 구현하는 구체적인 명령 클래스
        - 특정 작업을 실행하는 excute메서드를 구현함
        - **Receiver** 객체에서 메서드를 실행하는 데 필요한 매개 변수들은 **ConcreteCommand** 의 필드들로 선언할 수 있음
            - 생성자를 통해서만 이러한 필드들의 초기화를 허용함으로써, 커맨드 객체들을 불변으로 만들 수 있음
    - **Invoker :**
        - 명령 객체를 호출하는 역할을 하는 클래스
        - 명령의 실행을 요청할 때 사용
        - Invoker가 명령을 실행하는 과정에서, Invoker는 명령이 어떻게 구현되었는지에 대한 세부 내용을 모름
        - Invoker는 단순히 명령을 호출하여 실행하거나 큐에 추가하고 관리하는 역할
    - **Receiver :**
        - 실제로 작업을 수행하는 클래스
        - ConcreteCommand 객체가 Receiver를 포함하는 상태에서,
        - invoker가 excute()메서드를 호출해서 Receiver의 실제 작업을 수행
    - **Client :**
        - 명령을 생성하고 호출하는 객체

- 사용시기
    - **요청을 객체로 매개변수화하고 싶을 때:**
        - 특정 요청을 객체로 캡슐화하여 다양한 요청을 처리하고자 할 때
        - 요청을 매개변수화하면, 취소하거나 로깅하는 등의 추가적인 기능을 지원할 수 있음
    - **요청을 큐에 저장하거나 로깅할 때:**
        - 요청 객체를 큐에 저장하여 나중에 실행하거나 로깅하는 등의 기능을 구현할 수 있음
        - 이는 작업의 지연 실행 또는 순서대로 실행해야 하는 경우에 유용
    - **작업을 취소하거나 다시 실행할 수 있는 기능이 필요할 때:**
        - 커맨드 패턴은 실행된 작업을 취소하거나 다시 실행할 수 있는 기능을 제공함
        - 이는 'Undo' 기능을 구현하거나 작업의 히스토리를 유지하는 데에 유용
    - **작업을 분리하고자 할 때:**
        - 요청을 하는 객체와 실제 작업을 수행하는 객체를 분리하고, 각각의 책임을 분산시키고자 할 때

- 장점
    - **요청과 처리의 분리:**
        - 클라이언트 코드는 어떤 요청을 하는지 알지만, 실제로 그 요청을 처리하는 객체를 알 필요가 없어져서 클라이언트와 서버의 의존성이 감소함
    - **유연성:**
        - 명령 패턴은 명령을 실행하는 객체와 명령을 발생시키는 객체를 분리하므로, 유연성이 높아짐
        - 새로운 명령을 추가하거나 기존 명령을 수정할 때 다른 부분에 영향을 덜 미침
    - **커맨드 큐 및 로깅:**
        - 명령을 큐에 저장하거나 로그에 기록하여 나중에 실행하거나 취소할 수 있음
    - **코드 재사용:**
        - 명령 객체는 재사용이 가능하며, 여러 명령에서 동일한 실행 로직을 공유할 수 있음

- 단점
    - **클래스 및 복잡성 증가:**
        - 각각의 명령마다 별도의 클래스를 생성해야 하므로, 클래스의 수가 증가

    ```java
    //**Command** 
    public interface TextCommand {
      void execute();
      void undo();
    }
    
    //ConcreteCommand
    public class InsertCommand implements TextCommand {
      private TextCommandReceiver textCommandReceiver;
      private String insertedText;
      private int position;
    
      public InsertCommand(TextCommandReceiver textCommandReceiver, int position, String insertedText) {
        this.textCommandReceiver = textCommandReceiver;
        this.position = position;
        this.insertedText = insertedText;
      }
    
      @Override
      public void execute() {
        textCommandReceiver.insert(position, insertedText);
      }
    
      @Override
      public void undo() {
        int endIndex = position + insertedText.length();
        textCommandReceiver.delete(position, endIndex);
      }
    }
    
    //ConcreteCommand
    public class DeleteCommand implements TextCommand {
      private TextCommandReceiver textCommandReceiver;
      private int position;
      private String deletedText;
    
      public DeleteCommand(TextCommandReceiver textCommandReceiver, int position, int length) {
        this.textCommandReceiver = textCommandReceiver;
        this.position = position;
        this.deletedText = textCommandReceiver.substring(position, position + length);
      }
    
      @Override
      public void execute() {
        int endIndex = position + deletedText.length();
        textCommandReceiver.delete(position, endIndex);
      }
    
      @Override
      public void undo() {
        textCommandReceiver.insert(position, deletedText);
      }
    }
    
    //Invoker - 명령을 실행하고, 필요에 따라 관리하는 객체
    public class TextCommandInvoker {
    
      //실행한 커멘드를 stack 에 저장 -> 실행 취소 시, 가장 최근 커멘드 취소
      private final Stack<TextCommand> commandHistory = new Stack<>();
    
      //실행 취소한 커멘드를 stack에 저장 -> 다시실행 시, 가장 최근 커멘드 실행
      private final Stack<TextCommand> redoHistory = new Stack<>();
    
      public void executeCommand(TextCommand command) {
        command.execute();
        commandHistory.push(command);
        redoHistory.clear();
      }
    
      //실행취소
      public void undo() {
        if (!commandHistory.isEmpty()) {
          TextCommand command = commandHistory.pop();
          command.undo();
          redoHistory.push(command);
        }
      }
    
      //다시 실행
      public void redo() {
        if (!redoHistory.isEmpty()) {
          TextCommand command = redoHistory.pop();
          command.execute();
          commandHistory.push(command);
        }
      }
    }
    
    //Receiver - 실제 작업을 처리하는 객체
    public class TextCommandReceiver {
      private StringBuilder text = new StringBuilder();
      public String substring(int start, int end) {
        return text.substring(start,end);
      }
    
      public void delete(int start, int end) {
        text.delete(start, end);
        System.out.println("Current Text: "+text);
      }
    
      public void insert(int offset, String textToInsert) {
        text.insert(offset, textToInsert);
        System.out.println("Current Text: "+text);
      }
    }
    
    //Client
    public class Client {
      public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    
        TextCommand insertCommand1 = new InsertCommand(textEditor.getText(), 0, "insert text1 ");
        textEditor.executeCommand(insertCommand1);//Current Text: insert text1
    
        TextCommand insertCommand2 = new InsertCommand(textEditor.getText(), 7, "123456 ");
        textEditor.executeCommand(insertCommand2);//Current Text: insert 123456 text1
    
        textEditor.undo();//Current Text: insert text1
        textEditor.redo();//Current Text: insert 123456 text1
    
        TextCommand deleteCommand1 = new DeleteCommand(textEditor.getText(), 0, 7);
        textEditor.executeCommand(deleteCommand1);//Current Text: 123456 text1
    
        TextCommand deleteCommand2 = new DeleteCommand(textEditor.getText(), 0, 3);
        textEditor.executeCommand(deleteCommand2);//Current Text: Current Text: 456 text1
    
        textEditor.undo();//Current Text: 123456 text1
        textEditor.redo();//Current Text: 456 text1
    
      }
    }
    ```