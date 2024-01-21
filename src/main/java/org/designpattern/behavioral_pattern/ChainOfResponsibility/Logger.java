package org.designpattern.behavioral_pattern.ChainOfResponsibility;
//Handler
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
