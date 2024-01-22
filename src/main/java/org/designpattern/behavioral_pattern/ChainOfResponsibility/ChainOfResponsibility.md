### 책임 연쇄패턴
- 개념
    - 하나의 요청에 대해 여러 객체가 처리할 수 있도록 체인 형태로 연결되어 있는 패턴
    - 요청이 전달되고 처리될 때까지 각 Handler 객체가 순차적으로 책임을 맡는 패턴
    - 요청을 하는 객체와 요청을 처리하는 객체들 간의 결합을 느슨하게 만들어 유연성을 제공하며, 각 객체가 요청을 처리할지 말지를 결정할 수 있음
    - 요청을 받으면 각 핸들러는 요청을 처리할 수 있는지, 처리할 수 없으면 체인의 다음 핸들러로 처리에 대한 책임을 전가
- 구성요소
    - **Handler :**
        - 요청을 처리하는 인터페이스나 추상클래스
        - 다음 처리자(Handler)에 대한 참조를 유지하며, 처리할 수 있는 메서드를 선언
    - **ConcreteHandler :**
        - 실제로 요청을 처리하는 클래스
        - 핸들러에 대한 필드를 내부에 가지고 있으며 메서드를 통해 다음 핸들러를 체인시킴.
        - 자신이 처리할 수 없는 요청이라면 다음 처리자에게 요청 전달
    - **Client (클라이언트):**
        - 요청을 초기화하고 Handler 체인을 생성
        - 요청을 첫 번째 Handler에게 전달하고, 체인을 따라가며 처리를 요청함

- 사용시기
    - **여러 객체가 요청을 처리할 수 있는 경우:**
        - 여러 객체가 각각 요청을 처리할 수 있지만, 어떤 객체가 실제로 처리할지는 런타임에 결정되어야 하는 경우에 유용
    - **요청 처리의 순서가 중요한 경우:**
        - 여러 객체에게 순차적으로 책임을 할당하고, 각 객체는 자신이 처리할 수 없는 경우에만 다음 객체에게 책임을 전달하도록 할 때 사용
    - **요청 처리자를 동적으로 변경하고 추가해야 하는 경우:**
        - 객체 간의 관계를 동적으로 변경해야 하며, 새로운 객체를 추가하거나 기존 객체를 제거해야 할 때 유용
    - **정확한 요청유형과 순서를 미리 알 수 없는 경우:**
        - 다양한 방식과 종류의 요청을 예상되지만 정확한 요청 유형과 순서를 미리 알 수 없는 경우 체인을 따라 적합한 Handler를 찾아 처리할 수 있음
- 장점
    - **유연성 및 확장성 향상:**
        - 요청 처리의 순서를 변경하거나 새로운 처리자를 추가할 수 있어 유연성과 확장성 제공
    - **단일 책임 원칙 준수:**
        - 각 처리자는 자신이 책임져야 하는 부분에만 집중하고, 다음 처리자에게 책임을 전달하므로 단일 책임 원칙을 준수함
    - **클라이언트와 처리자 간의 결합도 감소:**
        - 클라이언트는 첫 번째 처리자에만 의존하기 때문에, 처리자 간의 구조를 변경해도 클라이언트에는 영향을 주지 않음
        - 클라이언트는 처리 객체의 체인 집합 내부의 구조를 알 필요가 없음
    - **요청의 호출자(invoker)와 수신자(receiver)의 분리:**
        - 요청을 하는 쪽과 요청을 처리하는 쪽을 디커플링 시켜 결합도를 낮츰
        - 요청을 처리하는 방법이 바뀌더라도 호출자 코드는 변경되지 않음
- 단점
    - **요청이 처리되지 않을 수 있음:**
        - 처리자 연결이 끊어져 있거나 모든 처리자가 요청을 처리하지 않는 경우 요청이 처리되지 않을 수도 있음
    - **처리 지연 문제**
        - 요청과 처리에 대한 관계가 고정적이고 속도가 중요하면 책임 연쇄 패턴 사용을 유의
    - **책임 전파에 따른 성능 영향:**
        - 요청이 처리되지 않고 계속해서 책임을 전파하는 경우 성능에 영향을 미칠 수 있음
        - 충분한 디버깅을 거치지 않았을 경우 집합 내부에서 무한 사이클이 발생할 수도 있음
    - **디버깅이 어려울 수 있음:**
        - 실행 시에 코드의 흐름이 많아져서 과정을 살펴보거나 디버깅 및 테스트가 쉽지 않다.
        - 어떤 처리자에서 문제가 발생했는지 파악하기 어려울 수 있습니다.


```java
//Handler 추상클래스
public abstract class Logger {

  private Logger nextLogger;

  public Logger setNextLogger(Logger nextLogger) {
    this.nextLogger = nextLogger;
    return this;
  }

  public void handle(LogMessage logMessage) {
    if (shouldHandle(logMessage.getLogLevel())) {
      handleLogMessage(logMessage);
    }

    if (nextLogger != null) {
      nextLogger.handle(logMessage);
    }
  }

  protected abstract boolean shouldHandle(LogLevel level);

  protected abstract void handleLogMessage(LogMessage logMessage);
}

public enum LogLevel {
  INFO, WARN, ERROR
}

//처리할 요청
public class LogMessage {
  private final String message;
  private final LogLevel logLevel;

  public LogMessage(String message, LogLevel logLevel) {
    this.message = message;
    this.logLevel = logLevel;
  }

  public String getMessage() {
    return message;
  }

  public LogLevel getLogLevel() {
    return logLevel;
  }
}

//Concrete Handler
public class InfoLogger extends Logger{
  @Override
  protected boolean shouldHandle(LogLevel level) {
    return level==LogLevel.INFO;
  }

  @Override
  protected void handleLogMessage(LogMessage logMessage) {
    System.out.printf("INFO : %s%n", logMessage.getMessage());
  }
}

//Concrete Handler
public class WarnLogger extends Logger{
  @Override
  protected boolean shouldHandle(LogLevel level) {
    return level==LogLevel.WARN;
  }

  @Override
  protected void handleLogMessage(LogMessage logMessage) {
    System.out.printf("WARN : %s%n", logMessage.getMessage());
  }
}

//Concrete Handler
public class ErrorLogger extends Logger{
  @Override
  protected boolean shouldHandle(LogLevel level) {
    return level == LogLevel.ERROR;
  }

  @Override
  protected void handleLogMessage(LogMessage logMessage) {
    System.out.printf("ERROR : %s%n", logMessage.getMessage());

  }
}

//Concrete Handler
class FileLogger extends Logger {
  private String filePath = "C:/Users/window/Desktop/logger/error_log.txt";

  @Override
  protected boolean shouldHandle(LogLevel level) {
    return level == LogLevel.ERROR;
  }

  @Override
  protected void handleLogMessage(LogMessage logMessage) {
    // ERROR 레벨의 로그를 파일에 저장
    if(isAvailableDirectory(filePath)){
      //PrintWriter를 사용하여 파일에 쓰기를 수행
      //FileWriter는 파일에 문자를 쓰기 위한 클래스이며, true를 전달하여 파일을 이어쓰기 모드 지정
      //PrintWriter는 보다 편리한 형식으로 출력할 수 있도록 도와주는 클래스
      try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
        // 로그를 파일에 추가
        writer.println("ERROR: " + logMessage.getMessage());
      } catch (IOException e) {
        e.printStackTrace();
      }
      System.out.println("Error log massage is written on "+filePath);
    }else{
      System.out.println("Failed to logging on file(path: "+filePath+")");
    }

  }

  // 디렉토리가 존재하는지 확인하고, 없으면 생성
  private boolean isAvailableDirectory(String filePath) {
    File file = new File(filePath);
    File parentDir = file.getParentFile();
    //파일의 상위 디렉토리가 없는데 생성에 실패하면 종료
    if (!parentDir.exists() && !parentDir.mkdirs()) {
      System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
      return false;
    }
    return true;
  }
}

//Client
public class Client {
  public static void main(String[] args) {
    Logger logger =
        new InfoLogger().setNextLogger(
            new WarnLogger().setNextLogger(
                new ErrorLogger().setNextLogger(
                    new FileLogger()
                )
            )
        );

    LogMessage info = new LogMessage("info log message", LogLevel.INFO);
    LogMessage warn = new LogMessage("warn log message", LogLevel.WARN);
    LogMessage error = new LogMessage("error log message", LogLevel.ERROR);

    logger.handle(info);
    logger.handle(warn);
    logger.handle(error);
  }
}
```
