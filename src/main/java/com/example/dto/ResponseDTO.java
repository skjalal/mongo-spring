package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDTO {

  private int page;
  private int size;
  private int count;
  private Object data;
}
