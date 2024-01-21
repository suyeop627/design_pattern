package org.designpattern.behavioral_pattern.Strategy;
//Context
public class TransportationApp {
  private PricingStrategy pricingStrategy;
  public void setPricingStrategy(PricingStrategy pricingStrategy) {
    this.pricingStrategy = pricingStrategy;
  }
  public int calculatePrice(double distance){
    return pricingStrategy.calculateCost(distance);
  }
}
