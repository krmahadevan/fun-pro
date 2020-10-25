package com.rationaleemotions.monoids;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class MapMonoid<V, K> implements Monoid<Map<K,V>> {

  private final BinaryOperator<V> operator;

  public MapMonoid(BinaryOperator<V> operator) {
    this.operator = operator;
  }

  @Override
  public Map<K, V> identity() {
    return new HashMap<>();
  }

  @Override
  public Map<K, V> operation(Map<K, V> t1, Map<K, V> t2) {
    Map<K, V> result = identity();
    result.putAll(t1);
    for (Map.Entry<K, V> entry : t2.entrySet()) {
      V currentValue = result.get(entry.getKey());
      if (currentValue == null) {
        result.put(entry.getKey(), entry.getValue());
        continue;
      }
      result.put(entry.getKey(), operator.apply(currentValue, entry.getValue()));
    }
    return result;
  }
}
