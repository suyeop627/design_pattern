package org.designpattern.structural_pattern.Adapter;

// Target 인터페이스
public interface InternalSystem {
  void performInternalAction();
  default void sayHello(){
    System.out.println("Hello from Internal System");
  }
}
