package org.designpattern.structural_pattern.Composite;

public class Circle implements Shape{
  private String color;
  public Circle(String color){
    this.color = color;
  }
  @Override
  public void draw() {
    System.out.printf("Drawing %s Circle%n", color);
  }
}
