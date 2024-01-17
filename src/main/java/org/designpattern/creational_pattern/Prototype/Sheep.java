package org.designpattern.creational_pattern.Prototype;

public class Sheep implements Animal {
  public Sheep(){
    System.out.println("A sheep created");
  }

  @Override
  public Animal clone() {
    try{
      return (Sheep) super.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }

  @Override
  public void makeSound() {
    System.out.println("메~~~~~~");
  }
}
