package org.designpattern.creational_pattern.Builder.DirectorBuilder;

import org.designpattern.creational_pattern.Builder.DirectorBuilder.Computer.ComputerBuilder;

public class ComputerDirector {
  Computer buildGamingComputer(ComputerBuilder builder) {
    return builder
        .setCpu("Intel i7")
        .setMemory("16GB RAM")
        .setStorage("1TB SSD")
        .build();
  }

  Computer buildOfficeComputer(ComputerBuilder builder) {
    return builder
        .setCpu("Intel i5")
        .setMemory("8GB RAM")
        .setStorage("512GB SSD")
        .build();
  }
}
