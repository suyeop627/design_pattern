package org.designpattern.structural_pattern.Flyweight;
//Client - flyweight을 사용하며, extrinsic state를 제공함(context)
public class Client {
  public static void main(String[] args) {
    UnitFactory unitFactory = new UnitFactory();
    long startTime = System.currentTimeMillis();
    Unit normalTanker = unitFactory.createUnit("tanker1", "tanker");
    Unit normalArcher = unitFactory.createUnit("archer1", "archer");
    Unit normalWorrier = unitFactory.createUnit("worrier1", "worrier");

    System.out.println("=============Create unit task completed in " + (System.currentTimeMillis() - startTime) + " milliseconds.=============");
    //use cached object
    long startTime1 = System.currentTimeMillis();
    Unit normalTankerCached = unitFactory.createUnit("tanker2", "tanker");
    Unit normalArcherCached = unitFactory.createUnit("archer2", "archer");
    Unit normalWorrierCached = unitFactory.createUnit("worrier2", "worrier");
    System.out.println("=============Create unit using caching completed in " + (System.currentTimeMillis() - startTime1) + " milliseconds.=============");
  }
}
