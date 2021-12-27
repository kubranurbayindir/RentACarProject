package com.etiya.rentACarSpring.ws;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;



import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.rentACarSpring.businnes.abstracts.InvoiceService;
import com.etiya.rentACarSpring.businnes.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.CreateInvoiceDateRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

@RestController
@RequestMapping("api/invoices")
public class InvoicesController {

	private InvoiceService invoiceService;

	@Autowired
	public InvoicesController(InvoiceService invoiceService) {
		super();
		this.invoiceService = invoiceService;
	}

	@GetMapping("getAll")
	public DataResult<List<InvoiceSearchListDto>> getAll() {
		return invoiceService.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody @Valid DropOffCarRequest dropOffCarRequest) {

		return this.invoiceService.add(dropOffCarRequest);
	}

	@PutMapping("update")
	public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
		return this.invoiceService.update(updateInvoiceRequest);
	}

	@DeleteMapping("delete")
	public Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) {
		return this.invoiceService.delete(deleteInvoiceRequest);
	}

	@GetMapping("getInvoiceByDate")
	public DataResult<List<InvoiceSearchListDto>> getInvoiceByDate(@RequestParam("minDate") Date minDate,
			@RequestParam("maxDate") Date maxDate) throws ParseException {

		CreateInvoiceDateRequest createInvoiceDateRequest = new CreateInvoiceDateRequest();
		createInvoiceDateRequest.setMinDate(minDate);
		createInvoiceDateRequest.setMaxDate(maxDate);
		return invoiceService.getInvoiceByDate(createInvoiceDateRequest);
	}


}
