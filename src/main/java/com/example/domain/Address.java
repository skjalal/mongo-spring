package com.example.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
public class Address {

  private String building;

  @Field(name = "coord")
  private List<Double> coordinates;
  private String street;
  private String zipcode;
}
