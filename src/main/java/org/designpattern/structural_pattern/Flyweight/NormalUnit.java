package org.designpattern.structural_pattern.Flyweight;
//UnsharedConcreteFlyweight
public class NormalUnit extends UnitType {
  private final String name; //extrinsic state

  public NormalUnit(String name, String weaponType, String armorType) {
    super(weaponType, armorType);
    this.name = name;
    System.out.printf("%s (%s, %s) has been created%n", name, weaponType, armorType);
  }

  @Override
  public void attack() {
    System.out.printf("%s(%s, %s) attacked enemy&n", name, weaponType, armorType);
  }
}
