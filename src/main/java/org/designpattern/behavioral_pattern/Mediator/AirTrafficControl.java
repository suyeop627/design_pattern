package org.designpattern.behavioral_pattern.Mediator;
//Mediator
public interface AirTrafficControl {
  boolean requestLanding(Aircraft aircraft);

  void acknowledgeLanding(Aircraft aircraft);

  void clearRunway();
}
