package org.designpattern.behavioral_pattern.Mediator;

import java.util.ArrayList;
import java.util.List;
//Concrete Mediator
public class AirportControl implements AirTrafficControl{

  private final List<Aircraft> aircraftList = new ArrayList<>();
  private boolean runwayAvailable = true;


  @Override
  public synchronized boolean requestLanding(Aircraft aircraft) {
    System.out.println(aircraft.getFlightNumber() + " requests landing clearance.");
    if(runwayAvailable){
      acknowledgeLanding(aircraft);
      aircraftList.remove(aircraft);
      return true;
    }else{
      System.out.println("Runway is occupied. " + aircraft.getFlightNumber() + " must wait.");
      return false;
    }
  }

  @Override
  public synchronized void acknowledgeLanding(Aircraft aircraft) {
    for(Aircraft a : aircraftList){
      System.out.println(a.getFlightNumber() + " received message : Landing clearance granted for " + aircraft.getFlightNumber() + ".");
    }
    runwayAvailable = false;
  }

  @Override
  public void clearRunway() {
    runwayAvailable = true;
  }

  public void addAircraft(Aircraft aircraft) {
    aircraftList.add(aircraft);
  }
}
