package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.RentalAdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAdditionalServiceDao extends JpaRepository<RentalAdditionalService, Integer> {

}
