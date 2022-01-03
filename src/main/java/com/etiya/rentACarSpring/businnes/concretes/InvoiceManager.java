package com.etiya.rentACarSpring.businnes.concretes;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.*;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.request.InvoiceRequest.CreateInvoiceRequest;
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

        return new SuccesDataResult<List<InvoiceSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.InvoiceListed));
    }


    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) {
        Result rules = BusinnessRules.run(ifExistRentalIdOnInvoice(createInvoiceRequest.getRentalId()),
                isReturnDateNull(createInvoiceRequest.getRentalId())
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
        if (totalRentDay == 0) {
            totalRentDay = 1;
        }
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
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceAdded));
    }



    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
        Result rules = BusinnessRules.run( checkIfInvoiceExists(updateInvoiceRequest.getInvoiceId())
        );
        if (rules != null) {
            return rules;
        }
        Invoice invoice = modelMapperService.forRequest().map(updateInvoiceRequest, Invoice.class);
        this.invoiceDao.save(invoice);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceUpdated));
    }

    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
        Result rules = BusinnessRules.run( checkIfInvoiceExists(deleteInvoiceRequest.getInvoiceId())
        );
        if (rules != null) {
            return rules;
        }
        this.invoiceDao.deleteById(deleteInvoiceRequest.getInvoiceId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceDeleted));

    }

    @Override
    public DataResult<List<InvoiceSearchListDto>> getInvoiceByDate(CreateInvoiceDateRequest createInvoiceDateRequest) {
        List<Invoice> invoices = this.invoiceDao.getByCreationDateBetween(createInvoiceDateRequest.getMinDate(),
                createInvoiceDateRequest.getMaxDate());

        List<Invoice> result = this.invoiceDao.getByCreationDateBetween(createInvoiceDateRequest.getMinDate(), createInvoiceDateRequest.getMaxDate());
        List<InvoiceSearchListDto> response = result.stream()
                .map(invoice -> modelMapperService.forDto().map(invoice, InvoiceSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<InvoiceSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.InvoiceByDateListed));
    }

    public int calculateDifferenceBetweenDays(Date maxDate, Date minDate) {
        long difference = (maxDate.getTime() - minDate.getTime()) / 86400000;
        return Math.abs((int) difference);

    }

    public DataResult<Integer> ifCarReturnedToDifferentCity(int rentalId, int returnCityId) {
        if (this.rentalService.getById(rentalId).getTakeCity().equals(this.rentalService.getById(rentalId).getReturnCity())){
            return new SuccesDataResult<>(0);}
        return new ErrorDataResult<>(500);
    }

    private DataResult<String> createInvoiceNumber(int rentalId) {

        long unixTime = System.currentTimeMillis() / 1000L;
        String unique_no1 = Long.toHexString(unixTime).toUpperCase();
        String unique_no2 = Long.toHexString(unixTime).toUpperCase();
        String invoiceNumber = "REV" + unique_no1 + "%" + unique_no2 + "#";

        return new SuccesDataResult<>(invoiceNumber);
    }



    private Result ifExistRentalIdOnInvoice(int rentalId) {
        Integer result = this.invoiceDao.countByRental_RentalId(rentalId);
        if (result > 0) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceAlreadyExistForThisRent));
        }
        return new SuccesResult();
    }

    private Result isReturnDateNull(int rentalId){
        Rental result = this.rentalService.getById(rentalId);
        if (result.getReturnDate() == null){
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.RentalNotCompleted));
        }
        return new SuccesResult();
    }

    private Result checkIfInvoiceExists(int invoiceId) {
        if (!this.invoiceDao.existsById(invoiceId)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.InvoiceNotFound));
        }
        return new SuccesResult();
    }
}
