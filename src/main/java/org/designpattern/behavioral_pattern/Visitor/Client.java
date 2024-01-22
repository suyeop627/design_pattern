package org.designpattern.behavioral_pattern.Visitor;

// Client Code
public class Client {
  public static void main(String[] args) {
    ZooManager zooManager = new ZooManager();
    Lion lion = new Lion("lion1", "area1");
    Giraffe giraffe = new Giraffe("giraffe1", "area1");
    Shark shark = new Shark("shark1", "area2");
    zooManager.addAnimal(lion);
    zooManager.addAnimal(giraffe);
    zooManager.addAnimal(shark);

    AnimalVisitor feedingVisitor = new FeedingVisitor();
    zooManager.perform(feedingVisitor);

    AnimalVisitor dataCollectVisitor = new DataCollectVisitor();
    zooManager.collectData(dataCollectVisitor);

  }
}
