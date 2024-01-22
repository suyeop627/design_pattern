package org.designpattern.behavioral_pattern.Mediator;

//Concrete Colleague
public class Airplane extends Aircraft {
  boolean isLanded = false;

  public Airplane(AirTrafficControl airTrafficControl, String flightNumber) {
    super(airTrafficControl, flightNumber);
  }

  @Override
  public void run() {
    while (!isLanded) {
      if (requestLanding()) {
        stopOneSecond();
        landing();
        isLanded = true;
      } else {
        System.out.println(flightNumber + "is waiting for ATC getting available.");
        stopOneSecond();
      }

    }
  }

  private void stopOneSecond() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
