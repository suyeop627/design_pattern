package org.designpattern.behavioral_pattern.ChainOfResponsibility;

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
