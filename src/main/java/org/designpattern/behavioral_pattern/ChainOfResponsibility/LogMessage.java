package org.designpattern.behavioral_pattern.ChainOfResponsibility;

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
