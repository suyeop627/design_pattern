package org.designpattern.behavioral_pattern.ChainOfResponsibility;
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
