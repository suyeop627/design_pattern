package org.designpattern.creational_pattern.Prototype;

public class Dog implements Animal {
  public Dog(){
    System.out.println("A dog created");
  }
  
  @Override
  public Animal clone() {
    try{
      return (Dog) super.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }

  @Override
  public void makeSound() {
    System.out.println("멍!");
  }
}
