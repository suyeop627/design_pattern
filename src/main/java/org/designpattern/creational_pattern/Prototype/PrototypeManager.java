package org.designpattern.creational_pattern.Prototype;

import java.util.HashMap;
import java.util.Map;

public class PrototypeManager {
  private Map<String, Animal> prototypes;

  public PrototypeManager(){
    prototypes = new HashMap<>();
  }

  public void addOrigin(String key, Animal animal){
    prototypes.put(key, animal);
  }
  public Animal getPrototype(String key){
    System.out.println("origin animal = " + prototypes.get(key));
    System.out.println("cloned animal = " + prototypes.get(key).clone());
    return prototypes.get(key).clone();
  }
}
