package org.designpattern.creational_pattern.Builder.SimpleBuilder;

public class Computer {
  private String cpu;
  private String memory;
  private String storage;
  // private constructor로 빌더를 통해서만 생성되도록 강제함
  private Computer() {}

  // 심플 빌더
  public static class ComputerBuilder {
    private final Computer computer;

   public ComputerBuilder() {
      this.computer = new Computer();
    }

    // 빌더 메서드들
    public ComputerBuilder withCpu(String cpu) {
      computer.cpu = cpu;
      return this;
    }

    public ComputerBuilder withMemory(String memory) {
      computer.memory = memory;
      return this;
    }

    public ComputerBuilder withStorage(String storage) {
      computer.storage = storage;
      return this;
    }

    // 제품 반환
    public Computer build() {
      return computer;
    }
  }

  @Override
  public String toString() {
    return String.format("cpu : %s, memory : %s, storage : %s", cpu, memory, storage);
  }
}
