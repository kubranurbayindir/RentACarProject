package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CorparateCustomerService;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.CreateCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.DeleteCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CorparateCustomerRequest.UpdateCorparateRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CorparateCustomerDao;
import com.etiya.rentACarSpring.entities.CorparateCustomer;

@Service
public class CorparateCustomerManager implements CorparateCustomerService {

	private CorparateCustomerDao corparateCustomerDao;
	private ModelMapperService modelMapperService;
	@Autowired
	public CorparateCustomerManager(CorparateCustomerDao corparateCustomerDao,ModelMapperService modelMapperService) {
		super();
		this.corparateCustomerDao = corparateCustomerDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result Add(CreateCorparateRequest createCorparateRequest) {

		var result = BusinnessRules.run(checkIfTaxNumberExists(createCorparateRequest.getTaxNumber()));

		if(result != null)
			return result;

		CorparateCustomer corparateCustomer = modelMapperService.forRequest().map(createCorparateRequest, CorparateCustomer.class);
		this.corparateCustomerDao.save(corparateCustomer);
		return new SuccesResult("Ekleme İslemi Basarili");
	}

	@Override
	public Result update(UpdateCorparateRequest updateCorparateRequest) {

		var result = BusinnessRules.run( checkIfTaxNumberExists(updateCorparateRequest.getTaxNumber()));

		if(result != null)
			return result;

		CorparateCustomer corparateCustomer = modelMapperService.forRequest().map(updateCorparateRequest, CorparateCustomer.class);
		this.corparateCustomerDao.save(corparateCustomer);
		return new SuccesResult("Güncelleme İşlemi Başarılı");
	}

	@Override
	public Result delete(DeleteCorparateRequest deleteCorparateRequest) {
		this.corparateCustomerDao.deleteById(deleteCorparateRequest.getCorparateCustomerId());
		return new SuccesResult("Silme İşlemi Başarılı");
	}


	private Result checkIfTaxNumberExists(String taxNumber) {

		if(this.corparateCustomerDao.existsByTaxNumber(taxNumber))
			return new ErrorResult("Vergi numarası sistemde kayıtlıdır.");

		return new SuccesResult();
	}

}
