package org.designpattern.behavioral_pattern.Memento;

import java.util.ArrayList;
import java.util.List;
//Memento
public class CharacterMemento {
  private String name;
  private int health;
  private List<String> equipment;

  public CharacterMemento(String name, int health, List<String> equipment) {
    this.name = name;
    this.health = health;
    this.equipment = new ArrayList<>(equipment);
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public List<String> getEquipment() {
    return new ArrayList<>(equipment);
  }
}
