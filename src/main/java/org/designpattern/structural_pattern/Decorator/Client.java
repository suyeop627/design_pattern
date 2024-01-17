package org.designpattern.structural_pattern.Decorator;

public class Client {
  public static void main(String[] args) {
    Pizza pizza = new PlainPizza();
    printOrder(pizza);
    //페퍼로니
    Pizza pepperoniPizza = new PepperoniDecorator(new PlainPizza());
    printOrder(pepperoniPizza);

    //페퍼로니 + 버섯
    Pizza pepperoniMushroomPizza = new PepperoniDecorator(new MushroomDecorator(new PlainPizza()));
    printOrder(pepperoniMushroomPizza);

    //페퍼로니 + 버섯 + 새우
    Pizza allToppingPizza = new ShrimpDecorator(new PepperoniDecorator(new MushroomDecorator(new PlainPizza())));
    printOrder(allToppingPizza);

  }

  private static void printOrder(Pizza pizza){
    System.out.printf("You ordered %s and it's %s won%n", pizza.getDescription(), pizza.getCost());
  }
}
