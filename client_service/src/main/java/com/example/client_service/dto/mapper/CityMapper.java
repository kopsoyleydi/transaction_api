package com.example.client_service.dto.mapper;

import com.example.client_service.dto.CityDto;
import com.example.client_service.model.City;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityMapper {

    CityDto toDto(City city);

    City toModel(CityDto cityDto);

    List<CityDto> toDtoList(List<City> list);

    List<City> toModelList(List<CityDto> list);
}
