package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Color;

public interface ColorDao extends JpaRepository<Color, Integer>{

}
