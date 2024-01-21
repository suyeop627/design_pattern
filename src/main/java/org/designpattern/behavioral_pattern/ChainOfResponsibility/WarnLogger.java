package org.designpattern.behavioral_pattern.ChainOfResponsibility;
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
