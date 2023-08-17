package com.example.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.dto.Pagination;
import com.example.dto.ResponseDTO;
import com.example.service.RestaurantService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

@WebMvcTest(controllers = {RestaurantController.class})
class RestaurantControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  RestaurantController restaurantController;

  @MockBean
  RestaurantService restaurantService;

  @BeforeEach
  void setUp() {
    when(restaurantService.findAllRestaurants(any(Pagination.class))).thenReturn(new ResponseDTO());
  }

  @WithMockUser
  @ParameterizedTest
  @ValueSource(strings = {"/api", "/api/", "/api/all"})
  void testFindAll(String url) throws Exception {
    var queryParams = new LinkedMultiValueMap<String, String>();
    queryParams.put("offset", List.of("0"));
    queryParams.put("limit", List.of("10"));
    queryParams.put("field", List.of("name"));
    queryParams.put("search", List.of("Test"));
    queryParams.put("sortBy", List.of("id"));
    queryParams.put("order", List.of("asc"));
    mockMvc.perform(get(url).queryParams(queryParams)).andExpect(status().isOk()).andDo(print());
  }

  @Test
  void unitTestFindAll() {
    var result = restaurantController.findAll(new Pagination());
    assertNotNull(result);
  }
}
