package com.example.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginatedResult {

  private List<Restaurant> data;
  private int count;
}
