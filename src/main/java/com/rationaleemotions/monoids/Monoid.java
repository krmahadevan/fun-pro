package com.rationaleemotions.monoids;

import java.util.stream.Stream;

public interface Monoid<T> {

  T identity();

  T operation(T t1, T t2);

  default T reduce(Stream<T> stream) {
    return stream.reduce(identity(), this::operation);
  }
}
