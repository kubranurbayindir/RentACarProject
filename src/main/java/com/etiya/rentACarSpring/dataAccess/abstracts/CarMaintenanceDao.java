package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.CarMaintenance;
import com.etiya.rentACarSpring.entities.Rental;

public interface CarMaintenanceDao extends JpaRepository<CarMaintenance, Integer>{

	List<CarMaintenance> getByCar_CarId(int carId);
}
