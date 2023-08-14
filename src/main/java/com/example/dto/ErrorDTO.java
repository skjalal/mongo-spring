package com.example.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {

  private int code;
  private String message;
  private LocalDateTime timestamp;
}
