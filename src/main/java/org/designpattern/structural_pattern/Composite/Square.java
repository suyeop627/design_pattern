package org.designpattern.structural_pattern.Composite;

public class Square implements Shape{
  private final String color;
  public Square(String color){
    this.color = color;
  }
  @Override
  public void draw() {
    System.out.printf("Drawing %s Square%n", color);
  }
}
