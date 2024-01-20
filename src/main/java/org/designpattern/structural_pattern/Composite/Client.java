package org.designpattern.structural_pattern.Composite;

public class Client {
  public static void main(String[] args) {
    Shape redCircle = new Circle("red");
    Shape redSquare = new Square("red");

    ShapeGroup redShapes = new ShapeGroup();
    redShapes.addShape(redCircle);
    redShapes.addShape(redSquare);
    redShapes.draw();
    Shape blueCircle = new Circle("blue");
    Shape blueSquare = new Square("blue");

    ShapeGroup blueShapes = new ShapeGroup();
    blueShapes.addShape(blueCircle);
    blueShapes.addShape(blueSquare);
    blueShapes.draw();

    ShapeGroup allShapes = new ShapeGroup();
    allShapes.addShape(redShapes);
    allShapes.addShape(blueShapes);
    allShapes.draw();

  }
}
