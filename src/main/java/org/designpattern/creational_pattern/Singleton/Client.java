package org.designpattern.creational_pattern.Singleton;

public class Client {
  public static void main(String[] args) {
    SingletonWithNullCheck singletonWithNullCheck = SingletonWithNullCheck.getInstance();
    SingletonInnerClass singletonInnerClass = SingletonInnerClass.getInstance();
    SingletonEnum singletonEnum = SingletonEnum.INSTANCE;

    singletonWithNullCheck.connectToDatabase();
    singletonInnerClass.connectToDatabase();
    singletonEnum.connectToDatabase();

    SingletonWithNullCheck singletonWithNullCheck2 = SingletonWithNullCheck.getInstance();
    SingletonInnerClass singletonInnerClass2 = SingletonInnerClass.getInstance();
    SingletonEnum singletonEnum2 = SingletonEnum.INSTANCE;

    singletonWithNullCheck2.connectToDatabase();
    singletonInnerClass2.connectToDatabase();
    singletonEnum2.connectToDatabase();


    System.out.println("singletonEnum = " + singletonEnum.hashCode());
    System.out.println("singletonEnum2 = " + singletonEnum2.hashCode());

    System.out.println("singletonWithNullCheck = " + singletonWithNullCheck);
    System.out.println("singletonWithNullCheck2 = " + singletonWithNullCheck2);

    System.out.println("singletonInnerClass = " + singletonInnerClass);
    System.out.println("singletonInnerClass2 = " + singletonInnerClass2);
  }

}
