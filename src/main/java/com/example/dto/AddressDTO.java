package com.example.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

  private String building;
  private List<Double> coordinates;
  private String street;
  private String zipcode;
}
