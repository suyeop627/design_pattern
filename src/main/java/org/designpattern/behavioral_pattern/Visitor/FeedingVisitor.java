package org.designpattern.behavioral_pattern.Visitor;
// Concrete Visitor
public class FeedingVisitor implements AnimalVisitor{
  @Override
  public String visit(Lion lion) {
    return "Feeding meet to lion";
  }

  @Override
  public String visit(Giraffe giraffe) {
    return "Feeding leaves and fruits to giraffe";
  }

  @Override
  public String visit(Shark shark) {
    return "Feeding fishes and fruits to sharks";
  }
}
