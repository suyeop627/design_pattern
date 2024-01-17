package org.designpattern.creational_pattern.Singleton;
//Initialization-on-demand holder idiom
public class SingletonInnerClass {
  private SingletonInnerClass(){
    System.out.println("SingletonWithInnerClass instance created.");
  }
  //getInstance 메서드가 호출될때 Singleton 클래스가 초기화 됨.
  private static class SingletonInstanceHolder{
    private static final SingletonInnerClass INSTANCE = new SingletonInnerClass();
  }
  public static SingletonInnerClass getInstance(){
    return SingletonInstanceHolder.INSTANCE;
  }
  public void connectToDatabase() {
    System.out.println("Connected to the database. with SingletonInnerClass");
  }
}
