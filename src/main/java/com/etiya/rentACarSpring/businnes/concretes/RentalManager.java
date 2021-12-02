package com.etiya.rentACarSpring.businnes.concretes;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.dtos.CreditCardSearchListDto;
import com.etiya.rentACarSpring.businnes.request.PosServiceRequest;
import com.etiya.rentACarSpring.core.utilities.adapter.posServiceAdapter.posSystemService;
import com.etiya.rentACarSpring.entities.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CarMaintenanceService;
import com.etiya.rentACarSpring.businnes.abstracts.CarService;
import com.etiya.rentACarSpring.businnes.abstracts.CreditCardService;
import com.etiya.rentACarSpring.businnes.abstracts.InvoiceService;
import com.etiya.rentACarSpring.businnes.abstracts.RentalService;
import com.etiya.rentACarSpring.businnes.abstracts.UserService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.RentalSearchListDto;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.CreateRentalRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DeleteRentaRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarUpdateRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.RentalDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.Rental;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private ModelMapperService modelMapperService;
	private CarService carService;
	private UserService userService;
	private CreditCardService creditcardService;
	private InvoiceService invoiceService;
	private CarMaintenanceService carMaintenanceService;
	private posSystemService posSystemService;

	@Autowired
	public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService,
			UserService userService, CreditCardService creditcardService,@Lazy InvoiceService invoiceService,
			@Lazy CarMaintenanceService carMaintenanceService,posSystemService posSystemService) {

		super();
		this.rentalDao = rentalDao;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
		this.userService = userService;
		this.creditcardService = creditcardService;
		this.invoiceService = invoiceService;
		this.carMaintenanceService = carMaintenanceService;
		this.posSystemService=posSystemService;

	}

	@Override
	public DataResult<List<RentalSearchListDto>> getAll() {
		List<Rental> result = this.rentalDao.findAll();
		List<RentalSearchListDto> response = result.stream()
				.map(car -> modelMapperService.forDto().map(car, RentalSearchListDto.class))
				.collect(Collectors.toList());

		return new SuccesDataResult<List<RentalSearchListDto>>(response);
	}

	@Override
	public Result Add(CreateRentalRequest createRentalRequest) {
		Result result = BusinnessRules.run(checkCarRentalStatus(createRentalRequest.getCarId()),
				checkUserAndCarFindexScore(createRentalRequest.getUserId(), createRentalRequest.getCarId()),
				carMaintenanceService.CheckIfCarIsAtMaintenance(createRentalRequest.getCarId()),
				checkIfCarIsNotExistsInGallery(createRentalRequest.getCarId()),
				checkIfUserRegisteredSystem(createRentalRequest.getUserId()));

		if (result != null) {
			return result;
		}

		Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);

		this.rentalDao.save(rental);

		return new SuccesResult(Messages.succesRental);
	}

	@Override
	public Result dropOffCarUpdate(DropOffCarUpdateRequest dropOffCarUpdateRequest) {

		Rental rental = modelMapperService.forRequest().map(dropOffCarUpdateRequest, Rental.class);

		Car car = this.carService.getbyId(dropOffCarUpdateRequest.getCarId()).getData();


		Rental result = this.rentalDao.getByRentalId(dropOffCarUpdateRequest.getRentalId());
		rental.setRentDate(result.getRentDate());
		rental.setTakeCity(result.getTakeCity());
		rental.setUser(result.getUser());
		rental.setCar(result.getCar());
		car.setKilometer(rental.getReturnKilometer());
		car.setCity(rental.getReturnCity());

		this.rentalDao.save(rental);


			this.invoiceService.Add(dropOffCarUpdateRequest);
			return new SuccesResult("Araç kiradan döndü ve fatura oluşturuldu.");
	}


	@Override
	public Result Delete(DeleteRentaRequest deleteRentalRequest) {
		this.rentalDao.deleteById(deleteRentalRequest.getRentalId());
		return new SuccesResult(Messages.deletedRental);
	}

	@Override
	public DataResult<List<Rental>> getByCar(int carId) {
		return null;
	}

	public Result checkCarRentalStatus(int carId) {
		List<Rental> result = this.rentalDao.getByCar_CarId(carId);
		if (result != null) {
			for (Rental rentals : this.rentalDao.getByCar_CarId(carId)) {
				if (rentals.getReturnDate() == null) {
					return new ErrorResult("Araç bir başkası tarafından kiralanmıştır.");
				}
			}
		}
		return new SuccesResult();
	}

	@Override
	public Rental getById(int rentalId) {
		return this.rentalDao.getById(rentalId);
	}

	private Result checkUserAndCarFindexScore(int userId, int carId) {
		if (this.carService.getbyId(carId).getData().getFindexScore() > this.userService.getById(userId).getData()
				.getFindexScore()) {
			return new ErrorResult("Findex Puanı yeterli değildir.");
		}

		return new SuccesResult();
	}


	private Result checkCreditCardBalance(CreditCardSearchListDto creditCardSearchListDto, double price){

		PosServiceRequest fakePosServiceRequest = new PosServiceRequest();
		fakePosServiceRequest.setCardNumber(creditCardSearchListDto.getCardNumber());
		fakePosServiceRequest.setPrice(price);
		if (this.posSystemService.withdraw(fakePosServiceRequest)){
			return  new ErrorResult("Limit Yeterli Değil");
		}


		return new SuccesResult();
	}

	private Result checkIfCarIsNotExistsInGallery(int carId) {
		if (!this.carService.checkCarExistsInGallery(carId).isSuccess()) {
			return new ErrorResult("Böyle bir araba galeride bulunmamaktadır.");
		}
		return new SuccesResult();
	}

	private Result checkIfUserRegisteredSystem(int userId) {
		if (!this.userService.getById(userId).isSuccess()) {
			return new ErrorResult("Böyle bir kullanıcı sisteme kayıtlı değil, öncelikle kayıt olunuz.");
		}
		return new SuccesResult();
	}



}
