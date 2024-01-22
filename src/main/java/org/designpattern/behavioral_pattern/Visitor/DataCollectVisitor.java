package org.designpattern.behavioral_pattern.Visitor;
// Concrete Visitor
public class DataCollectVisitor implements AnimalVisitor{

  @Override
  public String visit(Giraffe giraffe) {
    return String.format("  %s(Giraffe)%n", giraffe.getName());
  }

  @Override
  public String visit(Lion lion) {
    return String.format("  %s(Lion)%n", lion.getName());
  }

  @Override
  public String visit(Shark shark) {
    return String.format("  %s(Shark)%n", shark.getName());
  }
}
