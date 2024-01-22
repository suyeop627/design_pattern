package org.designpattern.behavioral_pattern.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Object Structure
public class ZooManager {
  private List<Animal> animals = new ArrayList<>();

  public void addAnimal(Animal animal){
    animals.add(animal);
  }
  public void perform(AnimalVisitor visitor){
    animals.forEach(animal -> System.out.println(animal.accept(visitor)));
  }

  public void collectData(AnimalVisitor visitor){

    Map<String, StringBuilder> areaDataMap = new HashMap<>();

    animals.forEach(animal -> {
      String area = animal.getArea();
      //computeIfAbsent(key, function)
      // - 키에 해당하는 값이 있으면 해당 값을 반환하고,
      //- 키에 해당하는 값이 없으면 새로 지정(key를 인자로 받는 함수로 람다식 작성)
      areaDataMap.computeIfAbsent(area, key -> new StringBuilder().append(key).append("\n"))
          .append(animal.accept(visitor));
    });
    StringBuilder result = new StringBuilder("Animals in Zoo\n");
    areaDataMap.values().forEach(result::append);

    System.out.println(result);
  }
}
