package com.example.service.impl;

import com.example.domain.PaginatedResult;
import com.example.dto.Pagination;
import com.example.dto.ResponseDTO;
import com.example.enums.RestaurantEnums;
import com.example.exception.RestaurantNotFound;
import com.example.mapper.RestaurantMapper;
import com.example.repository.RestaurantRepository;
import com.example.service.RestaurantService;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RestaurantServiceImpl implements RestaurantService {

  private final RestaurantRepository restaurantRepository;
  private final RestaurantMapper restaurantMapper;

  @Override
  public ResponseDTO findAllRestaurants(Pagination pagination) {
    int offset = pagination.getOffset();
    int limit = pagination.getLimit();
    var sortBy =
        Optional.ofNullable(pagination.getSortBy())
            .map(RestaurantEnums::find)
            .orElseGet(RestaurantEnums.RESTAURANT_ID::getRestaurantValue);
    int order = pagination.getOrder().equalsIgnoreCase("asc") ? 1 : -1;
    var fieldName = Optional.ofNullable(pagination.getField()).orElseGet(String::new);
    var fieldValue = Optional.ofNullable(pagination.getSearch()).orElseGet(String::new);
    PaginatedResult result;
    if (Stream.of(fieldName, fieldValue).allMatch(Predicate.not(String::isBlank))) {
      result =
          Optional.ofNullable(
                  restaurantRepository.findAllRestaurants(
                      offset, limit, fieldName, fieldValue, sortBy, order))
              .orElseThrow(RestaurantNotFound::new);
    } else {
      result =
          Optional.ofNullable(restaurantRepository.findAllRestaurants(offset, limit, sortBy, order))
              .orElseThrow(RestaurantNotFound::new);
    }
    int page;
    if (offset <= 0) {
      page = 1;
    } else {
      page = ((offset - 1) + limit) / limit;
    }
    var response = new ResponseDTO();
    response.setCount(result.getCount());
    response.setData(result.getData().stream().map(restaurantMapper::toDTO).toList());
    response.setSize(limit);
    response.setPage(page);
    return response;
  }
}
