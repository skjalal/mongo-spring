package com.example.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeDTO {

  private LocalDateTime date;

  private String grade;

  private Integer score;
}
