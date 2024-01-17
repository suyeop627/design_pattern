package org.designpattern.creational_pattern.Singleton;

public class SingletonWithNullCheck {
  private static SingletonWithNullCheck instance;
  //private constructor로 외부에서 생성되는 것을 제한함
  private SingletonWithNullCheck(){
    System.out.println("SingletonWithNullCheck instance created.");
  }

  //외부에서 static 메서드 호출시 초기화 진행
  public static synchronized SingletonWithNullCheck getInstance() {
    if (instance == null) {
      instance = new SingletonWithNullCheck();
    }
    return instance;
  }

  public void connectToDatabase() {
    System.out.println("Connected to the database with SingletonWithNullCheck.");
  }
}
