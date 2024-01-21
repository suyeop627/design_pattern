package org.designpattern.behavioral_pattern.Strategy;
//Concrete Strategy
public class TaxiPricingStrategy implements PricingStrategy{
  private final int BASE_FARE = 4800;
  private final int ADDITIONAL_FARE_PER_131M = 100;


  @Override
  public int calculateCost(double distance) {
    return  BASE_FARE + ((int)(distance*1000/131 * ADDITIONAL_FARE_PER_131M));
  }
}
