package com.rationaleemotions.monoids;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class MapMonoidTest {

  @Test
  public void testMethod() {
    BinaryOperator<Integer> quantityAdder = Integer::sum;
    Monoid<Map<String, Integer>> data = new MapMonoid<>(quantityAdder);
    Map<String, Integer> consolidatedOrders = data.reduce(buildFoodOrders().values().stream());
    Map<String, Integer> expected = new Food()
        .addItem("idli", 3)
        .addItem("vada", 2)
        .addItem("paratha", 1)
        .addItem("vangibath", 1)
        .asMap();
    Assertions.assertThat(consolidatedOrders).containsAllEntriesOf(expected);
  }

  private static Map<Integer, Map<String, Integer>> buildFoodOrders() {

    Map<String, Integer> p1 = new Food()
        .addItem("idli", 2)
        .addItem("vada", 1)
        .asMap();
    Map<String, Integer> p2 = new Food()
        .addItem("idli", 1)
        .addItem("paratha", 1)
        .asMap();
    Map<String, Integer> p3 = new Food()
        .addItem("vada", 1)
        .addItem("vangibath", 1)
        .asMap();
    Map<Integer, Map<String, Integer>> individualFoodOrders = new HashMap<>();
    individualFoodOrders.put(1, p1);
    individualFoodOrders.put(2, p2);
    individualFoodOrders.put(3, p3);
    return individualFoodOrders;
  }
}
