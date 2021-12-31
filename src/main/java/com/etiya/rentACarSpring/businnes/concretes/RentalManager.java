package com.etiya.rentACarSpring.businnes.concretes;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.request.CreditCardRentalRequest;
import com.etiya.rentACarSpring.businnes.request.PosServiceRequest;
import com.etiya.rentACarSpring.core.utilities.adapter.posServiceAdapter.posSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
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
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarRequest;
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
    private Environment environment;
    private LanguageWordService languageWordService;

    @Autowired
    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService, CarService carService,
                         UserService userService, CreditCardService creditcardService, @Lazy InvoiceService invoiceService,
                         @Lazy CarMaintenanceService carMaintenanceService, posSystemService posSystemService, Environment environment,
                         LanguageWordService languageWordService) {

        super();
        this.rentalDao = rentalDao;
        this.modelMapperService = modelMapperService;
        this.carService = carService;
        this.userService = userService;
        this.creditcardService = creditcardService;
        this.invoiceService = invoiceService;
        this.carMaintenanceService = carMaintenanceService;
        this.posSystemService = posSystemService;
        this.environment = environment;
        this.languageWordService = languageWordService;

    }

    @Override
    public DataResult<List<RentalSearchListDto>> getAll() {
        List<Rental> result = this.rentalDao.findAll();
        List<RentalSearchListDto> response = result.stream()
                .map(car -> modelMapperService.forDto().map(car, RentalSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<RentalSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.RentalListed,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result add(CreateRentalRequest createRentalRequest) {
        Result result = BusinnessRules.run(checkCarRentalStatus(createRentalRequest.getCarId()),
                checkUserAndCarFindexScore(createRentalRequest.getUserId(), createRentalRequest.getCarId()),
                carMaintenanceService.checkIfCarIsAtMaintenance(createRentalRequest.getCarId()),
                checkIfUserRegisteredSystem(createRentalRequest.getUserId()),
                checkIfCarIsNotExistsInGallery(createRentalRequest.getCarId())
        );

        if (result != null) {
            return result;
        }

        Rental rental = modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        this.rentalDao.save(rental);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.RentalAdded,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result dropOffCar(DropOffCarRequest dropOffCarRequest) {
        Rental result = this.rentalDao.getByRentalId(dropOffCarRequest.getRentalId());
        Rental rental = modelMapperService.forRequest().map(dropOffCarRequest, Rental.class);


        Result rules = BusinnessRules.run(checkCreditCardBalance(dropOffCarRequest,
                        dropOffCarRequest.getCreditCardRentalRequest()),
                checkReturnDate(dropOffCarRequest.getRentalId()),
                creditcardService.checkIfCreditCardCvvFormatIsTrue(dropOffCarRequest.getCreditCardRentalRequest().getCvv()),
                creditcardService.checkIfCreditCardFormatIsTrue(dropOffCarRequest.getCreditCardRentalRequest().getCardNumber()),
                checkDifferenceBetweenDates(result.getRentDate(), dropOffCarRequest.getReturnDate())

        );
        if (rules != null) {
            return rules;
        }

        rental.setRentalId(result.getRentalId());
        rental.setRentDate(result.getRentDate());
        rental.setTakeCity(result.getTakeCity());
        rental.setUser(result.getUser());
        rental.setCar(result.getCar());

        Car car = this.carService.getById(rental.getCar().getCarId()).getData();
        car.setKilometer(rental.getReturnKilometer());
        car.setCity(rental.getReturnCity());

        this.rentalDao.save(rental);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CarReturnedRental,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result delete(DeleteRentaRequest deleteRentalRequest) {
        Result rules = BusinnessRules.run(checkIfRentalExists(deleteRentalRequest.getRentalId())
        );

        if (rules != null) {
            return rules;
        }

        this.rentalDao.deleteById(deleteRentalRequest.getRentalId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.RentalDeleted,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Rental getById(int rentalId) {
        return this.rentalDao.getById(rentalId);
    }

    public Result checkCarRentalStatus(int carId) {
        List<Rental> result = this.rentalDao.getByCar_CarId(carId);
        if (result != null) {
            for (Rental rentals : this.rentalDao.getByCar_CarId(carId)) {
                if (rentals.getReturnDate() == null) {
                    return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CarIsOnRent,Integer.parseInt(environment.getProperty("language"))));
                }
            }
        }
        return new SuccesResult();
    }

    public Integer sumAdditionalServicePriceByRentalId(int rentalId) {
        List<Integer> prices = this.rentalDao.getAdditionalRentalPrice(rentalId);
        int additionalTotalPrice = 0;

        for (int price : prices) {
            additionalTotalPrice += price;
        }
        return additionalTotalPrice;
    }

    public DataResult<Integer> getDailyPriceOfRentedCar(int rentalId) {
        return new SuccesDataResult<>(this.rentalDao.getDailyPriceOfCar(rentalId));
    }

    private Result checkUserAndCarFindexScore(int userId, int carId) {
        if (this.carService.getById(carId).getData().getFindexScore() > this.userService.getById(userId).getData()
                .getFindexScore()) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.FindexScoreNotEnough,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }

    public Result checkReturnDate(int rentalId) {
        Rental result = this.rentalDao.getByRentalId(rentalId);
        if ((result.getReturnDate() != null)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.RentCompleted,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }

    private Result checkCreditCardBalance(DropOffCarRequest dropOffCarRequest, CreditCardRentalRequest creditCardRentalRequest) {

        PosServiceRequest posServiceRequest = new PosServiceRequest();
        posServiceRequest.setPrice(invoiceService.rentOfTotalPrice(dropOffCarRequest));
        posServiceRequest.setCvv(creditCardRentalRequest.getCvv());
        posServiceRequest.setCardNumber(creditCardRentalRequest.getCardNumber());
        if (!this.posSystemService.checkPayment(posServiceRequest)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CreditCardBalanceNotEnough,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }

    private Result checkIfCarIsNotExistsInGallery(int carId) {
        if (!this.carService.checkCarExistsInGallery(carId).isSuccess()) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CarNotFound,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }

    private Result checkIfUserRegisteredSystem(int userId) {
        if (!this.userService.getById(userId).isSuccess()) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.UserNotExist,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }

    @Override
    public Result checkIfRentalExists(int rentalId) {
        if (!this.rentalDao.existsById(rentalId)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.RentalNotFound,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }

    private Result checkDifferenceBetweenDates(Date rentalDate, Date returnDate) {
        if (rentalDate.compareTo(returnDate) < 0) {
            return new SuccesResult();
        }
        return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.InvalidReturnKilometer,Integer.parseInt(environment.getProperty("language"))));
    }

    private Result checkDifferenceBetweenKilometers(int Kilometer, int returnKilometer) {
        if (Kilometer < returnKilometer) {
            return new SuccesResult();
        }
        return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.InvalidReturnRentDate,Integer.parseInt(environment.getProperty("language"))));
    }

}
