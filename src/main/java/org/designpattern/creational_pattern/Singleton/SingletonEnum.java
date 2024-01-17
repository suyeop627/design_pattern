package org.designpattern.creational_pattern.Singleton;


public enum SingletonEnum {
  INSTANCE;

  // Enum 생성자
  private SingletonEnum() {
    System.out.println("SingletonEnum instance created.");
  }

  public void connectToDatabase() {
    System.out.println("Connected to the database with SingletonEnum.");
  }
}
