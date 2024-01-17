package org.designpattern.creational_pattern.Builder.DirectorBuilder;

import org.designpattern.creational_pattern.Builder.DirectorBuilder.Computer.ComputerBuilder;
import org.designpattern.creational_pattern.Builder.DirectorBuilder.Computer.ConcreteComputerBuilder;


public class Client {
  public static void main(String[] args) {
    ComputerBuilder builder = new ConcreteComputerBuilder();
    ComputerDirector computerDirector = new ComputerDirector();

    Computer gamingComputer = computerDirector.buildGamingComputer(builder);

    ComputerBuilder builder2 = new ConcreteComputerBuilder();
    ComputerDirector computerDirector2 = new ComputerDirector();
    Computer officeComputer = computerDirector2.buildOfficeComputer(builder2);


    System.out.println("officeComputer = " + officeComputer);
    System.out.println("gamingComputer = " + gamingComputer);

  }
}
