package com.example.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {

  private AddressDTO address;
  private String borough;
  private String cuisine;
  private String name;
  private List<GradeDTO> grades;
  private String restaurantId;
}
