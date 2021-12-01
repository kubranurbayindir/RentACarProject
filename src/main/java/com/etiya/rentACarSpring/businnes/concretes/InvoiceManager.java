package com.etiya.rentACarSpring.businnes.concretes;

import java.sql.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.RentalService;
import com.etiya.rentACarSpring.businnes.abstracts.UserService;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.CreateRentalRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.InvoiceService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.CreateInvoiceDateRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.CreateInvoiceRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.InvoiceDao;
import com.etiya.rentACarSpring.entities.Invoice;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailForColorAndBrand;

@Service
public class InvoiceManager implements InvoiceService {

	private InvoiceDao invoiceDao;
	private ModelMapperService modelMapperService;
	private UserService userService;
	private RentalService rentalService;

	@Autowired
	public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService
			,UserService userService,RentalService rentalService) {
		super();
		this.invoiceDao = invoiceDao;
		this.modelMapperService = modelMapperService;
		this.userService=userService;
		this.rentalService=rentalService;
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
		//Random rand=new Random();


		invoice.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));
		invoice.setInvoiceNumber("fdsfdsfds8042");
		//invoice.setUser(userService.getByUserId(rentalService.getById());
		


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

	

	

}
