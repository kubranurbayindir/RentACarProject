package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Brand;


public interface BrandDao extends JpaRepository<Brand, Integer> {
    Brand getByBrandName(String brandName);
}
