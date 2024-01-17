package org.designpattern.creational_pattern.Builder.SimpleBuilder;

public class Client {
  public static void main(String[] args) {
    Computer computer =
        new Computer.ComputerBuilder()
            .withCpu("Intel i5")
            .withMemory("8GB RAM")
            .withStorage("512GB SSD")
            .build();
    System.out.println("computer = " + computer);
  }
}
