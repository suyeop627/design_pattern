package org.designpattern.behavioral_pattern.TemplateMethod;

import java.util.List;

public class Client {
    public static void main(String[] args) {

      System.out.println("------------------online-order ");
      List<Item> onlineOrderList = List.of(Item.BASIC_PIZZA, Item.BASIC_PIZZA);
      OrderProcessor onlineOrderProcessor = new OnlineOrderProcessor();
      onlineOrderProcessor.processOrder(onlineOrderList, OrderType.ONLINE);

      System.out.println("------------------online-order with express delivery");
      List<Item> onlineExpressOrderList = List.of(Item.CHEESE_PIZZA, Item.CHEESE_PIZZA, Item.BASIC_PIZZA);
      onlineOrderProcessor.processOrder(onlineExpressOrderList, OrderType.ONLINE_EXPRESS);

      System.out.println("------------------in-store order");
      List<Item> inStoreOrderList = List.of(Item.BASIC_PIZZA, Item.BASIC_PIZZA, Item.BASIC_PIZZA);
      OrderProcessor inStoreOrderProcessor = new InStoreOrderProcessor();
      inStoreOrderProcessor.processOrder(inStoreOrderList, OrderType.IN_STORE);
    }
}
