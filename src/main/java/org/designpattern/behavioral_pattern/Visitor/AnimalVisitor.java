package org.designpattern.behavioral_pattern.Visitor;
// Visitor interface
public interface AnimalVisitor {
  String visit(Giraffe giraffe);
  String visit(Lion lion);
  String visit(Shark shark);
}
