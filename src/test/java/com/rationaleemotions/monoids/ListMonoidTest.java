package com.rationaleemotions.monoids;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class ListMonoidTest {

  @Test
  public void testMethod() {
    BinaryOperator<String> operator = (a, b) -> {
      String d1 = Optional.ofNullable(a).orElse("");
      String d2 = Optional.ofNullable(b).orElse("");
      return d1.toLowerCase() + d2.toUpperCase();
    };
    Monoid<List<String>> names = new ListMonoid<>(operator);
    List<List<String>> input = Arrays.asList(
        Arrays.asList("Dragon Warrior", "Master Shifu", "Master Oogway"),
        Arrays.asList("Old Monk", "Jack Daniels", "King Fisher"),
        Arrays.asList("Java", "Kotlin", "Groovy")
    );
    List<String> result = names.reduce(input.stream());
    List<String> expected = Arrays.asList(
        "dragon warriorold monkJAVA",
        "master shifujack danielsKOTLIN",
        "master oogwayking fisherGROOVY"
    );
    Assertions.assertThat(result).containsExactlyElementsOf(expected);
  }

}
