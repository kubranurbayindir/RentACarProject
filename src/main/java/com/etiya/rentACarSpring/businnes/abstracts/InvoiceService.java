package com.etiya.rentACarSpring.businnes.abstracts;

import java.util.List;

import com.etiya.rentACarSpring.businnes.dtos.InvoiceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.CreateInvoiceDateRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.DeleteInvoiceRequest;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.UpdateInvoiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarUpdateRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

public interface InvoiceService {
	DataResult<List<InvoiceSearchListDto>> getAll();
	Result Add(DropOffCarUpdateRequest dropOffCarUpdateRequest);
	Result Update(UpdateInvoiceRequest updateInvoiceRequest);
	Result Delete(DeleteInvoiceRequest deleteInvoiceRequest);
	DataResult<List<InvoiceSearchListDto>> getInvoiceByDate(CreateInvoiceDateRequest createInvoiceDateRequest);
	DataResult<List<InvoiceSearchListDto>> getInvoiceByUserId(int userId);
}
