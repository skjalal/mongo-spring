package com.example.domain;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
public class Grade {

  private LocalDateTime date;

  @Field(name = "grade")
  private String type;

  private Integer score;
}
