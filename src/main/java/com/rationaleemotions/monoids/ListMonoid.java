package com.rationaleemotions.monoids;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class ListMonoid<T> implements Monoid<List<T>> {

  private final BinaryOperator<T> operator;

  public ListMonoid(BinaryOperator<T> operator) {
    this.operator = operator;
  }

  @Override
  public List<T> identity() {
    return new ArrayList<>();
  }

  @Override
  public List<T> operation(List<T> t1, List<T> t2) {
    List<T> result = zigZagMerge(t1, t2);
    int size1 = t1.size();
    int size2 = t2.size();
    List<T> restOfTheItems = identity();
    if (size1 < size2) {
      restOfTheItems = t2.subList(size1, size2).stream()
          .map(each -> operator.apply(each, null))
          .collect(Collectors.toList());
    }
    if (size1 > size2) {
      restOfTheItems = t1.subList(size2, size1).stream()
          .map(each -> operator.apply(each, null))
          .collect(Collectors.toList());
    }
    result.addAll(restOfTheItems);
    return result;
  }

  private List<T> zigZagMerge(List<T> t1, List<T> t2) {
    int size = Math.min(t1.size(), t2.size());
    List<T> result = identity();
    for (int i = 0; i < size; i++) {
      result.add(operator.apply(t1.get(i), t2.get(i)));
    }
    return result;
  }
}
