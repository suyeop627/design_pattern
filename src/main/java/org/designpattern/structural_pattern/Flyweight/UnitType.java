package org.designpattern.structural_pattern.Flyweight;
//ConcreteFlyweight
public class UnitType implements Unit{
  protected final String weaponType; //intrinsic state
  protected final String armorType; //intrinsic state

  public String getWeaponType() {
    return weaponType;
  }

  public String getArmorType() {
    return armorType;
  }

  public UnitType(String weaponType, String armorType) {
    this.weaponType = weaponType;
    this.armorType = armorType;
  }

  @Override
  public void attack() {
    System.out.printf("Attacked enemy with %s%n", weaponType);
  }
}
