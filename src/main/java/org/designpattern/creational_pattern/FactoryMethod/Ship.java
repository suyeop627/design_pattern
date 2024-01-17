package org.designpattern.creational_pattern.FactoryMethod;

public class Ship {
  String name, color, capacity;

  @Override
  public String toString() {
    return String.format("Ship { name: '%s', color: '%s', capacity: '%s' }%n", name, color, capacity);
  }
}
