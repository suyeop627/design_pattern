package org.designpattern.behavioral_pattern.Strategy;
//Concrete Strategy
public class SharingCarPricingStretegy implements PricingStrategy{
  private final int FIXED_FARE = 10000;
  @Override
  public int calculateCost(double distance) {
    return FIXED_FARE;
  }
}
