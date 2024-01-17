package org.designpattern.creational_pattern.FactoryMethod;

public class ContainerShipFactory implements ShipFactory{

  private long index = 0;
  private static class SingletonInstanceHolder{
    private static final ContainerShipFactory INSTANCE = new ContainerShipFactory();
  }
  public static ContainerShipFactory getInstance(){
    return SingletonInstanceHolder.INSTANCE;
  }

  @Override
  public Ship createShip() {
    System.out.println("ContainerShipFactory : Creating Container Ship..");
    Ship containerShip = new ContainerShip("ContainerShip"+index++, "20t", "red");
    System.out.printf("ContainerShipFactory : %s has been created. details : %s%n", containerShip.name, containerShip);
    return containerShip;
  }
}
