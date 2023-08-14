package com.example.service;

import com.example.dto.ResponseDTO;
import com.example.dto.Pagination;

public interface RestaurantService {

  ResponseDTO findAllRestaurants(Pagination pagination);
}
