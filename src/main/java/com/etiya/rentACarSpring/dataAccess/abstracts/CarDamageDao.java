package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.CarDamage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDamageDao extends JpaRepository<CarDamage,Integer> {
}
