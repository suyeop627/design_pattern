package org.designpattern.creational_pattern.Builder.DirectorBuilder;

public class Computer {
  private String cpu;
  private String memory;
  private String storage;

  // private constructor로 빌더를 통해서만 생성되도록 강제함
  private Computer() {}
   interface ComputerBuilder {
    ComputerBuilder setCpu(String cpu);

    ComputerBuilder setMemory(String memory);

    ComputerBuilder setStorage(String storage);

    Computer build();
  }

  public static class ConcreteComputerBuilder implements ComputerBuilder {
    private final Computer computer;

    ConcreteComputerBuilder() {
      this.computer = new Computer();
    }

    @Override
    public ComputerBuilder setCpu(String cpu) {
      computer.cpu = cpu;
      return this;
    }

    @Override
    public ComputerBuilder setMemory(String memory) {
      computer.memory = memory;
      return this;
    }

    @Override
    public ComputerBuilder setStorage(String storage) {
      computer.storage = storage;
      return this;
    }

    @Override
    public Computer build() {
      return computer;
    }
  }

  @Override
  public String toString() {
    return "Computer{" +
        "cpu='" + cpu + '\'' +
        ", memory='" + memory + '\'' +
        ", storage='" + storage + '\'' +
        '}';
  }
}
