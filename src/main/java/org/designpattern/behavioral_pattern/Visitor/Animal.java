package org.designpattern.behavioral_pattern.Visitor;
// Element interface
public interface Animal {
  String accept(AnimalVisitor visitor);
  String getArea();
  String getName();
}
