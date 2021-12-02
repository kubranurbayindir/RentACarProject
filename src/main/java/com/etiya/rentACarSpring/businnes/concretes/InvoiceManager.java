package com.etiya.rentACarSpring.businnes.concretes;

import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.*;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarUpdateRequest;
import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.CreateInvoiceDateRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.InvoiceDao;
import com.etiya.rentACarSpring.entities.Invoice;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private RentalService rentalService;
	private CarService carService;
	private CityService cityService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService
			,UserService userService,RentalService rentalService,CarService carService,CityService cityService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.userService=userService;
		this.rentalService=rentalService;
		this.carService=carService;
		this.cityService=cityService;
	}

	@Override
	public DataResult<List<InvoiceSearchListDto>> getAll() {
		List<Invoice> result = this.invoiceDao.findAll();
		List<InvoiceSearchListDto> response = result.stream()
				.map(invoice -> modelMapperService.forDto().map(invoice, InvoiceSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccesDataResult<List<InvoiceSearchListDto>>(response);
	}

	@Override
	public Result Add(DropOffCarUpdateRequest dropOffCarUpdateRequest) {

		Invoice invoice=new Invoice();

		invoice.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));

		invoice.setInvoiceNumber(createInvoiceNumber(dropOffCarUpdateRequest.getRentalId()).getData());

		Date rentDateForInvoice= (Date)(rentalService.getById(dropOffCarUpdateRequest.getRentalId()).getRentDate());

		int totalRentDay = calculateDifferenceBetweenDays(dropOffCarUpdateRequest.getReturnDate(),rentDateForInvoice);
		invoice.setTotalRentDay(totalRentDay);

		int dailyPriceOfCar = (int)(carService.getbyId(dropOffCarUpdateRequest.getCarId()).getData().getDailyPrice());
		invoice.setTotalPrice((totalRentDay * dailyPriceOfCar) +
				(ifCarReturnedToDifferentCity(dropOffCarUpdateRequest.getRentalId(),
						dropOffCarUpdateRequest.getReturnCityId()).getData()));


		invoice.setUser(userService.getByUserId(dropOffCarUpdateRequest.getUserId()));
		invoice.setRental(rentalService.getById(dropOffCarUpdateRequest.getRentalId()));


		//Invoice invoice2 = modelMapperService.forRequest().map(dropOffCarUpdateRequest, Invoice.class);

		this.invoiceDao.save(invoice);
		return new SuccesResult(Messages.addedInvoice);
	}

	@Override
	public Result Update(UpdateInvoiceRequest updateInvoiceRequest) {
		Invoice invoice = modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
		this.invoiceDao.save(invoice);
		return new SuccesResult(Messages.updateInvoice);
	}

	@Override
	public Result Delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		this.invoiceDao.deleteById(deleteInvoiceRequest.getInvoiceId());
		return new SuccesResult(Messages.deleteInvoice);

	}

	@Override
	public DataResult<List<InvoiceSearchListDto>> getInvoiceByDate(CreateInvoiceDateRequest createInvoiceDateRequest) {
		List<Invoice> invoices = this.invoiceDao.getByCreationDateBetween(createInvoiceDateRequest.getMinDate(),
				createInvoiceDateRequest.getMaxDate());

		List<Invoice> result = this.invoiceDao.getByCreationDateBetween(createInvoiceDateRequest.getMinDate(), createInvoiceDateRequest.getMaxDate());
		List<InvoiceSearchListDto> response = result.stream()
				.map(invoice -> modelMapperService.forDto().map(invoice, InvoiceSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccesDataResult<List<InvoiceSearchListDto>>(response);
	}

	@Override
	public DataResult<List<InvoiceSearchListDto>> getInvoiceByUserId(int userId) {
		List<InvoiceSearchListDto> response = this.invoiceDao.getInvoiceByUser_UserId(userId).stream()
				.map(invoice -> modelMapperService.forDto().map(invoice, InvoiceSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccesDataResult<List<InvoiceSearchListDto>>(response);
	}

		private int calculateDifferenceBetweenDays(Date maxDate, Date minDate) {
		long difference = (maxDate.getTime() - minDate.getTime())/86400000;
		return Math.abs((int)difference);
	}

	private DataResult<Integer> ifCarReturnedToDifferentCity(int rentalId, int returnCityId) {
		if(this.rentalService.getById(rentalId).getTakeCity() != this.cityService.getbyId(returnCityId).getData())
			return new SuccesDataResult<>(500);
		return new SuccesDataResult<>(0);
	}

	private DataResult<String> createInvoiceNumber(int rentalId){

		long unixTime = System.currentTimeMillis() / 1000L;
		String unique_no1=Long.toHexString(unixTime).toUpperCase();
		String unique_no2=Long.toHexString(unixTime).toUpperCase();
		String invoiceNumber= "REV"+ unique_no1 + "%" + unique_no2+"#";

		return new SuccesDataResult<>(invoiceNumber);
	}



}
