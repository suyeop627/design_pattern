package org.designpattern.behavioral_pattern.ChainOfResponsibility;
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
