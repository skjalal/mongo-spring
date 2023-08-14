package com.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.domain.PaginatedResult;
import com.example.domain.Restaurant;
import com.example.dto.Pagination;
import com.example.mapper.RestaurantMapperImpl;
import com.example.repository.RestaurantRepository;
import com.example.service.RestaurantService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {RestaurantServiceImpl.class, RestaurantMapperImpl.class})
class RestaurantServiceImplTest {

  @Autowired
  RestaurantService restaurantService;

  @MockBean
  RestaurantRepository restaurantRepository;

  PaginatedResult result;
  Pagination pagination;

  @BeforeEach
  void setUp() {
    result = new PaginatedResult();
    var data = List.of(new Restaurant());
    result.setCount(data.size());
    result.setData(data);
    pagination = new Pagination();
    pagination.setOffset(0);
    pagination.setLimit(10);
    pagination.setSortBy("name");
    pagination.setOrder("desc");
  }

  @Test
  void testFindAllRestaurants() {
    when(restaurantRepository.findAllRestaurants(anyInt(), anyInt(), anyString(), anyInt()))
        .thenReturn(result);
    var data = restaurantService.findAllRestaurants(pagination);
    assertNotNull(data);
    assertEquals(1, data.getCount());
  }

  @Test
  void testFindAllRestaurantsBySearch() {
    pagination.setField("restaurantId");
    pagination.setSearch("3421");
    when(restaurantRepository.findAllRestaurants(
            anyInt(), anyInt(), anyString(), anyString(), anyString(), anyInt()))
        .thenReturn(result);
    var data = restaurantService.findAllRestaurants(pagination);
    assertNotNull(data);
    assertEquals(1, data.getCount());
  }
}
