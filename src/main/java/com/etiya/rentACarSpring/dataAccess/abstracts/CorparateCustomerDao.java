package com.etiya.rentACarSpring.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.CorparateCustomer;


public interface CorparateCustomerDao  extends JpaRepository<CorparateCustomer, Integer> {

    boolean existsByTaxNumber(String taxNumber);
	
}
