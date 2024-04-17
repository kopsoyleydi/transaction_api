package com.example.client_service.data;

import com.example.client_service.data.repointer.CityRepoInter;
import com.example.client_service.data.repository.CityRepository;
import com.example.client_service.model.City;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CityImpl implements CityRepoInter {

    private final CityRepository cityRepository;
    @Override
    public City insert(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City change(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City getCityById(Long id) {
        return cityRepository.findAllById(id);
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}
