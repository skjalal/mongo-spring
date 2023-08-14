package com.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.dto.Pagination;
import com.example.dto.ResponseDTO;
import com.example.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class RestaurantController {

  private final RestaurantService restaurantService;

  @GetMapping(value = {"/", "/all", ""})
  public ResponseDTO findAll(Pagination pagination) {
    return restaurantService.findAllRestaurants(pagination);
  }
}
