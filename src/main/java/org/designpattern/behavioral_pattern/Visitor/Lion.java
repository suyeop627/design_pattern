package org.designpattern.behavioral_pattern.Visitor;

// Concrete Element
public class Lion implements Animal {
  private final String name;
  private final  String area;

  public Lion(String name, String area) {
    this.name = name;
    this.area = area;
  }

  @Override
  public String accept(AnimalVisitor visitor) {
    return visitor.visit(this);
  }

  @Override
  public String getArea() {
    return area;
  }

  @Override
  public String getName() {
    return name;
  }
}