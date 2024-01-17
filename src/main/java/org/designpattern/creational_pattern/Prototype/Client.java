package org.designpattern.creational_pattern.Prototype;

public class Client {
  public static void main(String[] args) {
    PrototypeManager prototypeManager = new PrototypeManager();

    Dog dog = new Dog();
    Sheep sheep = new Sheep();

    //원본을 넣고
    prototypeManager.addOrigin("dog", dog);
    prototypeManager.addOrigin("sheep", sheep);

    //복사본을 꺼냄
    Animal dogCloned = prototypeManager.getPrototype("dog");
    Animal sheepCloned = prototypeManager.getPrototype("sheep");
  }
}
