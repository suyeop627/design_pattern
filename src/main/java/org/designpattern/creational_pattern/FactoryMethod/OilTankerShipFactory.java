package org.designpattern.creational_pattern.FactoryMethod;

public class OilTankerShipFactory implements ShipFactory{

  private long index = 0;
  private static class SingletonInstanceHolder{
    private static final OilTankerShipFactory INSTANCE = new OilTankerShipFactory();
  }

  public static OilTankerShipFactory getInstance(){
    return SingletonInstanceHolder.INSTANCE;
  }

  @Override
  public Ship createShip() {
    System.out.println("OilTankerShipFactory : Creating Container Ship..");
    Ship oilTankerShip = new OilTankerShip("OilTankerShip"+index++, "15t", "black");
    System.out.printf("OilTankerShipFactory : %s has been created. details : %s%n", oilTankerShip.name, oilTankerShip);
    return oilTankerShip;
  }
}
