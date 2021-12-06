package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.Image;

public interface ImageDao extends JpaRepository<Image, Integer>  {

	int countByCar_CarId(int carId);
	
	List<Image> getByCar_CarId(int carId);
	
	boolean existsByCar_CarId(int carId);
}
