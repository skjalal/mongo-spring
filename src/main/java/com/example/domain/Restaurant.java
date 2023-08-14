package com.example.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "restaurants")
public class Restaurant {

  @Id
  @Field(name = "_id")
  private ObjectId id;

  private Address address;
  private String borough;
  private String cuisine;
  private String name;
  private List<Grade> grades;

  @Field(name = "restaurant_id")
  private String restaurantId;
}
