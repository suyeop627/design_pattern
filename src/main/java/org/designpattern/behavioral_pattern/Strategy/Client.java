package org.designpattern.behavioral_pattern.Strategy;

public class Client {
  public static void main(String[] args) {
    TransportationApp transportationApp = new TransportationApp();
    double distance = 15;

    //bus pricing
    transportationApp.setPricingStrategy(new BusPricingStrategy());
    int fareOfTakingBus = transportationApp.calculatePrice(distance);
    System.out.printf("Price of selected transportation is %d won. %n", fareOfTakingBus);

    //taxi pricing
    transportationApp.setPricingStrategy(new TaxiPricingStrategy());
    int fareOfTakingTaxi = transportationApp.calculatePrice(distance);
    System.out.printf("Price of selected transportation is %d won. %n", fareOfTakingTaxi);

    //sharing car pricing
    transportationApp.setPricingStrategy(new SharingCarPricingStretegy());
    int fareOfSharingCar = transportationApp.calculatePrice(distance);
    System.out.printf("Price of selected transportation is %d won. %n", fareOfSharingCar);
  }
}
