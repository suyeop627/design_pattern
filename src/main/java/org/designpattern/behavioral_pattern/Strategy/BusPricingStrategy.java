package org.designpattern.behavioral_pattern.Strategy;
//Concrete Strategy
public class BusPricingStrategy implements PricingStrategy{
  private final int BASE_FARE = 1450;
  private final int ADDITIONAL_FARE_PER_5KM = 100;
  @Override
  public int calculateCost(double distance) {
    distance  = distance > 10 ? distance-10 : 0;
    return BASE_FARE + ((int) (distance)/5 * ADDITIONAL_FARE_PER_5KM);
  }
}
