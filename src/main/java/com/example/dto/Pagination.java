package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
  private int offset;
  private int limit;
  private String field;
  private String search;
  private String sortBy;
  private String order;
}
