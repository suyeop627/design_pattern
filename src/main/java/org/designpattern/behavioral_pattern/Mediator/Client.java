package org.designpattern.behavioral_pattern.Mediator;

public class Client {
  public static void main(String[] args) {
    AirportControl airportControl = new AirportControl();
    Aircraft aircraft1 = new Airplane(airportControl, "Flight 1");
    Aircraft aircraft2 = new Airplane(airportControl, "Flight 2");
    Aircraft aircraft3 = new Airplane(airportControl, "Flight 3");

    airportControl.addAircraft(aircraft1);
    airportControl.addAircraft(aircraft2);
    airportControl.addAircraft(aircraft3);

    Thread aircraftThread1 = new Thread(aircraft1);
    Thread aircraftThread2 = new Thread(aircraft2);
    Thread aircraftThread3 = new Thread(aircraft3);
    aircraftThread1.start();
    aircraftThread2.start();
    aircraftThread3.start();

  }
}
