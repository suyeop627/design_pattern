package org.designpattern.creational_pattern.Prototype;

public interface Animal extends Cloneable{
  Animal clone();
  void makeSound();
}
