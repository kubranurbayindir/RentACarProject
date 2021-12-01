package com.etiya.rentACarSpring.businnes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.businnes.abstracts.RentalService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.CarMaintenanceSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.CreateCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.DeleteCarMaintenanceRequest;
import com.etiya.rentACarSpring.businnes.request.CarMaintenanceRequest.UpdateCarMaintenanceRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarMaintenanceDao;

import com.etiya.rentACarSpring.entities.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;

	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService,
			RentalService rentalService) {
		super();
		this.carMaintenanceDao = carMaintenanceDao;
		this.modelMapperService = modelMapperService;
		this.rentalService = rentalService;
	}

	@Override
	public DataResult<List<CarMaintenanceSearchListDto>> getAll() {
		List<CarMaintenance> result = this.carMaintenanceDao.findAll();
		List<CarMaintenanceSearchListDto> response = result.stream().map(
				carMaintenance -> modelMapperService.forDto().map(carMaintenance, CarMaintenanceSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccesDataResult<List<CarMaintenanceSearchListDto>>(response);
	}

	@Override
	public Result Add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		Result result = BusinnessRules.run(checkIfCarIsRentedNow(createCarMaintenanceRequest.getCarId()));
		if (result != null) {
			return result;
		}

		CarMaintenance carMaintenance = modelMapperService.forRequest().map(createCarMaintenanceRequest,
				CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccesResult("Ekleme İslemi Basarili");
	}

	@Override
	public Result Update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		CarMaintenance carMaintenance = modelMapperService.forRequest().map(updateCarMaintenanceRequest,
				CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccesResult(Messages.updatedColor);
	}

	@Override
	public Result Delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		this.carMaintenanceDao.deleteById(deleteCarMaintenanceRequest.getCarMaintenanseId());
		return new SuccesResult(Messages.deletedColor);
	}

	@Override
	public DataResult<CarMaintenance> getbyId(int carId) {
		return new SuccesDataResult<CarMaintenance>(this.carMaintenanceDao.getById(carId));
	}

	@Override
	public Result CheckIfCarIsAtMaintenance(int carId) {
		List<CarMaintenance> result = this.carMaintenanceDao.getByCar_CarId(carId);
		if (result != null) {
			for (CarMaintenance carMaintenances : this.carMaintenanceDao.getByCar_CarId(carId)) {
				if (carMaintenances.getReturnDate() == null) {
					return new ErrorResult("Araç bakımdadır..");
				}
			}
		}
		return new SuccesResult();
	}

	private Result checkIfCarIsRentedNow(int carId) {
		Result isCarReturned = rentalService.checkCarRentalStatus(carId);
		if (!isCarReturned.isSuccess()) {
			return new ErrorResult("Araç kirada olduğu için bakıma gönderilemez.");
		}
		return new SuccesResult();
	}

}
