package com.etiya.rentACarSpring.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.City;


public interface CityDao extends JpaRepository<City, Integer>{
    City getByCityName(String cityName);
}
