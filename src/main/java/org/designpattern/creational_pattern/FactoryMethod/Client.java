package org.designpattern.creational_pattern.FactoryMethod;

public class Client {
  public static void main(String[] args) {

    for(int i = 0; i<10; i++){
      Ship containerShip = ContainerShipFactory.getInstance().orderShip("email@container.com");
      Ship oilShip = OilTankerShipFactory.getInstance().orderShip("email@oiltanker.com");
      System.out.println("containerShip = " + containerShip);
      System.out.println("oilShip = " + oilShip);
      System.out.println("-------------------------------------------------------------------------------");
    }
  }
}
