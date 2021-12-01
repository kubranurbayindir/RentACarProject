package com.etiya.rentACarSpring.dataAccess.abstracts;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.etiya.rentACarSpring.businnes.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.entities.Invoice;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailForColorAndBrand;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

	@Query(value = "Select * From invoices WHERE create_date between :minDate and :maxDate ", nativeQuery = true)
	List<Invoice> getByCreationDateBetween(@Param("minDate")Date minDate,@Param("maxDate")Date maxDate);
	

	List<Invoice> getInvoiceByUser_UserId(int userId);


}
