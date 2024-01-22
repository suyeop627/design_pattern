package org.designpattern.behavioral_pattern.Memento;

import java.util.ArrayList;
import java.util.List;

//Originator
public class Character {
  private String name;
  private int health;
  private List<String> equipment;

  public Character(String name, int health) {
    this.name = name;
    this.health = health;
    this.equipment = new ArrayList<>();
  }

  public void equipItem(String item) {
    equipment.add(item);
  }

  public void takeDamage(int damage) {
    health -= damage;
  }

  public void printCharacterInfo() {
    System.out.println("Current character state:");
    System.out.println("Name: " + name);
    System.out.println("Health: " + health);
    System.out.println("Equipment: " + equipment+"\n");
  }

  public CharacterMemento createMemento() {
    return new CharacterMemento(name, health, equipment);
  }

  public void restoreFromMemento(CharacterMemento memento) {
    this.name = memento.getName();
    this.health = memento.getHealth();
    this.equipment = memento.getEquipment();
  }
}
