package org.designpattern.structural_pattern.Flyweight;

import java.util.HashMap;
import java.util.Map;
//Flyweight factory
public class UnitFactory {
  //intrinsic state를 캐싱하기위한 map
  Map<String, UnitType> unitCaches = new HashMap<>();

  public Unit createUnit(String name, String position) {

    UnitType unitType;

    if (unitCaches.containsKey(position)) {
      unitType = unitCaches.get(position);
    } else {
      unitType = createUnitType(position);
      unitCaches.put(position, unitType);

    }
    return new NormalUnit(name, unitType.getWeaponType(), unitType.getArmorType());
  }

  private UnitType createUnitType(String position) {
    UnitType unitType = null;
    try {
      Thread.sleep(1000);
      switch (position.toLowerCase()) {
        case "tanker" -> unitType = new UnitType("Shield and One-hand sword", "Plate armor");
        case "worrier" -> unitType = new UnitType("Two-hand sword", "Chain mail");
        case "archer" -> unitType = new UnitType("Bow", "Leather armor");
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return unitType;
  }
}
