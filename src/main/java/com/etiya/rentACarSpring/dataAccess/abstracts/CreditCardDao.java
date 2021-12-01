package com.etiya.rentACarSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.rentACarSpring.entities.CreditCard;

public interface CreditCardDao extends JpaRepository<CreditCard, Integer>  {

	boolean existsByCardNumber(String cardNumber);

}
