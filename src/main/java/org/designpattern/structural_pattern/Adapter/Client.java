package org.designpattern.structural_pattern.Adapter;

public class Client {
  public static void main(String[] args) {
    //클래스 어댑터
    InternalSystem internalSystemAdapter = new ClassAdapter();
    internalSystemAdapter.performInternalAction();
    internalSystemAdapter.sayHello();

    //객체 어댑터
    internalSystemAdapter = new ObjectAdapter(new ExternalSystem());
    internalSystemAdapter.performInternalAction();
    internalSystemAdapter.sayHello();


  }
}
