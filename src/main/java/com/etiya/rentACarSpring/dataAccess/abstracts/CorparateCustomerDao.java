package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.CorparateCustomer;
import com.etiya.rentACarSpring.entities.Rental;

public interface CorparateCustomerDao  extends JpaRepository<CorparateCustomer, Integer> {

    boolean existsByTaxNumber(String taxNumber);
	
}
