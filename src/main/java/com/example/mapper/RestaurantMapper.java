package com.example.mapper;

import com.example.domain.Grade;
import com.example.domain.Restaurant;
import com.example.dto.GradeDTO;
import com.example.dto.RestaurantDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper
public interface RestaurantMapper {

  @Mappings(
      value = {
        @Mapping(source = "address", target = "address"),
        @Mapping(source = "borough", target = "borough"),
        @Mapping(source = "cuisine", target = "cuisine"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "restaurantId", target = "restaurantId"),
        @Mapping(source = "grades", target = "grades", qualifiedByName = "mapGrades")
      })
  RestaurantDTO toDTO(Restaurant restaurant);

  @Named("mapGrades")
  default GradeDTO getAudioId(Grade grade) {
    var gradeDTO = new GradeDTO();
    gradeDTO.setDate(grade.getDate());
    gradeDTO.setGrade(grade.getType());
    gradeDTO.setScore(grade.getScore());
    return gradeDTO;
  }
}
