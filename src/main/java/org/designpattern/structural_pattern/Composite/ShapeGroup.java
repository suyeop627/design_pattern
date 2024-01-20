package org.designpattern.structural_pattern.Composite;

import java.util.ArrayList;
import java.util.List;

public class ShapeGroup implements Shape{
  private final List<Shape> shapeList = new ArrayList<>();

  public void addShape(Shape shape){
    shapeList.add(shape);
  }
  @Override
  public void draw() {
    System.out.println("-----Drawing group------");
    shapeList.forEach(Shape::draw);

  }
}
