package com.rationaleemotions.monoids;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Food {
  private final Map<String, Integer> foodItems = new HashMap<>();

  public Food addItem(String name, int quantity) {
    foodItems.put(name, quantity);
    return this;
  }

  public Map<String, Integer> asMap() {
    return Collections.unmodifiableMap(foodItems);
  }
}
