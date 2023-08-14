package com.example.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RestaurantEnums {
  RESTAURANT_ID("restaurantId", "restaurant_id"),
  RESTAURANT_NAME("name", "name"),
  RESTAURANT_BOROUGH("borough", "borough"),
  RESTAURANT_CUISINE("cuisine", "cuisine");

  private static final Map<String, String> RESTAURANTS = new HashMap<>();
  private final String restaurantKey;

  @Getter
  private final String restaurantValue;

  public static String find(String key) {
    if (RESTAURANTS.isEmpty()) {
      Stream.of(values()).forEach(d -> RESTAURANTS.put(d.restaurantKey, d.restaurantValue));
    }
    return RESTAURANTS.getOrDefault(key, RESTAURANT_ID.restaurantValue);
  }
}
