package com.etiya.rentACarSpring.businnes.concretes;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import ch.qos.logback.core.CoreConstants;
import com.etiya.rentACarSpring.businnes.abstracts.*;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.CreateInvoiceRequest;
import com.etiya.rentACarSpring.businnes.request.RentalRequest.DropOffCarRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
    private Environment environment;
    private LanguageWordService languageWordService;

    @Autowired
    public InvoiceManager(InvoiceDao invoiceDao, ModelMapperService modelMapperService
            , UserService userService, RentalService rentalService, CarService carService, CityService cityService, Environment environment,
                          LanguageWordService languageWordService) {
        super();
        this.invoiceDao = invoiceDao;
        this.modelMapperService = modelMapperService;
        this.userService = userService;
        this.rentalService = rentalService;
        this.carService = carService;
        this.cityService = cityService;
        this.environment = environment;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<InvoiceSearchListDto>> getAll() {
        List<Invoice> result = this.invoiceDao.findAll();
        List<InvoiceSearchListDto> response = result.stream()
                .map(invoice -> modelMapperService.forDto().map(invoice, InvoiceSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<InvoiceSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.InvoiceListed,Integer.parseInt(environment.getProperty("language"))));
    }


    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) {
        Result rules = BusinnessRules.run(ifExistRentalIdOnInvoice(createInvoiceRequest.getRentalId())
        );
        if (rules != null) {
            return rules;
        }
        Rental rental = this.rentalService.getById(createInvoiceRequest.getRentalId());
        Car car = this.carService.getById(rental.getCar().getCarId()).getData();

        createInvoiceRequest.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));
        createInvoiceRequest.setInvoiceNumber(createInvoiceNumber(createInvoiceRequest.getRentalId()).getData());

        Date rentDateForInvoice = (Date) (rentalService.getById(createInvoiceRequest.getRentalId()).getRentDate());
        int totalRentDay = calculateDifferenceBetweenDays(rentalService.getById(createInvoiceRequest.getRentalId()).getReturnDate(), rentDateForInvoice);
        createInvoiceRequest.setTotalRentDay(totalRentDay);
        int additionalTotalAmount = rentalService.sumAdditionalServicePriceByRentalId(rental.getRentalId());
        int priceOfReturnDifferentCity = ifCarReturnedToDifferentCity(createInvoiceRequest.getRentalId(), createInvoiceRequest.getReturnCityId()).getData();
        int totalAmount = (car.getDailyPrice() * totalRentDay) + priceOfReturnDifferentCity + additionalTotalAmount;

        createInvoiceRequest.setTotalPrice(totalAmount);
        createInvoiceRequest.setRentalId((createInvoiceRequest.getRentalId()));
        createInvoiceRequest.setRentDate(rental.getRentDate());
        createInvoiceRequest.setReturnDate(rental.getReturnDate());
        car.setKilometer(rental.getReturnKilometer());
        car.setCity(rental.getReturnCity());

        Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        this.invoiceDao.save(invoice);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceAdded,Integer.parseInt(environment.getProperty("language"))));
    }
    
    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {

        Invoice invoice = modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
        this.invoiceDao.save(invoice);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceUpdated,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
        this.invoiceDao.deleteById(deleteInvoiceRequest.getInvoiceId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceDeleted,Integer.parseInt(environment.getProperty("language"))));

    }

    @Override
    public DataResult<List<InvoiceSearchListDto>> getInvoiceByDate(CreateInvoiceDateRequest createInvoiceDateRequest) {
        List<Invoice> invoices = this.invoiceDao.getByCreationDateBetween(createInvoiceDateRequest.getMinDate(),
                createInvoiceDateRequest.getMaxDate());

        List<Invoice> result = this.invoiceDao.getByCreationDateBetween(createInvoiceDateRequest.getMinDate(), createInvoiceDateRequest.getMaxDate());
        List<InvoiceSearchListDto> response = result.stream()
                .map(invoice -> modelMapperService.forDto().map(invoice, InvoiceSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<InvoiceSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.InvoiceByDateListed,Integer.parseInt(environment.getProperty("language"))));
    }

    private int calculateDifferenceBetweenDays(Date maxDate, Date minDate) {
        long difference = (maxDate.getTime() - minDate.getTime()) / 86400000;
        return Math.abs((int) difference);

    }

    private DataResult<Integer> ifCarReturnedToDifferentCity(int rentalId, int returnCityId) {
        if (this.rentalService.getById(rentalId).getTakeCity() != this.cityService.getById(returnCityId).getData())
            return new SuccesDataResult<>(500);
        return new SuccesDataResult<>(0);
    }

    private DataResult<String> createInvoiceNumber(int rentalId) {

        long unixTime = System.currentTimeMillis() / 1000L;
        String unique_no1 = Long.toHexString(unixTime).toUpperCase();
        String unique_no2 = Long.toHexString(unixTime).toUpperCase();
        String invoiceNumber = "REV" + unique_no1 + "%" + unique_no2 + "#";

        return new SuccesDataResult<>(invoiceNumber);
    }

    public Integer rentOfTotalPrice(DropOffCarRequest dropOffCarRequest) {

        int dailyPriceOfCar = this.rentalService.getDailyPriceOfRentedCar(dropOffCarRequest.getRentalId()).getData();
        int priceOfDiffrentCity = ifCarReturnedToDifferentCity(dropOffCarRequest.getRentalId(), dropOffCarRequest.getReturnCityId()).getData();
        int addtionalServicePrice = rentalService.sumAdditionalServicePriceByRentalId(dropOffCarRequest.getRentalId()) * rentOfTotalRentDate(dropOffCarRequest);
        int totalPrice = (rentOfTotalRentDate(dropOffCarRequest) * dailyPriceOfCar) + priceOfDiffrentCity + addtionalServicePrice;
        return totalPrice;
    }

    private Integer rentOfTotalRentDate(DropOffCarRequest dropOffCarRequest) {
        Date rentDateForInvoice = (Date) (rentalService.getById(dropOffCarRequest.getRentalId()).getRentDate());
        int totalRentDay = calculateDifferenceBetweenDays(dropOffCarRequest.getReturnDate(), rentDateForInvoice);
        if (totalRentDay == 0) { // bir günden az kullansa bari bir günlük ücret.
            totalRentDay = 1;
        }
        return totalRentDay;
    }

    private Result ifExistRentalIdOnInvoice(int rentalId) {
        Integer result = this.invoiceDao.countByRental_RentalId(rentalId);
        if (result > 0) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceAlreadyExistForThisRent,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }
}
