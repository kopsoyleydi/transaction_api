package com.example.client_service.data.repository;

import com.example.client_service.model.City;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CityRepository extends JpaRepository<City, Long> {

    City findAllById(Long id);
}
