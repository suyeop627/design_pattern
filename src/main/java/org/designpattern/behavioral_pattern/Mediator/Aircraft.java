package org.designpattern.behavioral_pattern.Mediator;
//Colleague interface
public abstract class Aircraft implements Runnable{
  protected AirTrafficControl airTrafficControl;
  protected String flightNumber;

  public String getFlightNumber() {
    return flightNumber;
  }

  public Aircraft(AirTrafficControl airTrafficControl, String flightNumber){
    this.airTrafficControl = airTrafficControl;
    this.flightNumber = flightNumber;
  }

  public abstract void run();
  protected boolean requestLanding(){
     return airTrafficControl.requestLanding(this);
  }

  protected void landing(){
    System.out.println(flightNumber + " landed.");
    airTrafficControl.clearRunway();
  }
}
