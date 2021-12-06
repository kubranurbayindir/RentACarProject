package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.sql.Date;
import java.util.List;

import com.etiya.rentACarSpring.businnes.concretes.InvoiceManager;
import com.etiya.rentACarSpring.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etiya.rentACarSpring.entities.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    int countByRental_RentalId(int rentalId);

    @Query(value = "Select * From invoices WHERE create_date between :minDate and :maxDate ", nativeQuery = true)
    List<Invoice> getByCreationDateBetween(@Param("minDate") Date minDate, @Param("maxDate") Date maxDate);

}
