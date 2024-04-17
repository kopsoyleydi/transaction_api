package com.example.client_service.data.repointer;

import com.example.client_service.model.City;

import java.util.List;

public interface CityRepoInter {

    City insert(City city);

    City change(City city);

    City getCityById(Long id);

    List<City> getAllCities();
}
