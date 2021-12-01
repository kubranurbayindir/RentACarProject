package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.sql.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etiya.rentACarSpring.businnes.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.entities.Invoice;
import com.etiya.rentACarSpring.entities.Rental;

public interface RentalDao extends JpaRepository<Rental, Integer> {
	Rental getByReturnDate(Date returnDate);

	List<Rental> getByCar_CarId(int carId);

	Rental getByRentalId(int rentalId);
	

//	@Query("Select new com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto" 
//			+ "(c.carId,c.dailyPrice,c.description) "
//			+ "From Car c Inner Join  c.carMaintenances cm where m.return_date ")
//	List<CarSearchListDto> getAllWithoutMaintenanceOfCar();

//	@Query(value = "select c.id from cars c inner join car_maintenances m on "
//			+ "c.id=m.id where m.return_date is null and c.id=:carId ", nativeQuery = true)
//	CarMaintenanceSearchListDto getByCarIdIfMaintenanceReturnDateIsNull(int carId);
}
