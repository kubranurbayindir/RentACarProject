package com.etiya.rentACarSpring.businnes.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.BrandService;
import com.etiya.rentACarSpring.businnes.abstracts.CityService;
import com.etiya.rentACarSpring.businnes.abstracts.ColorService;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageService;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CarService;
import com.etiya.rentACarSpring.businnes.dtos.CarSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CarRequest.CreateCarRequest;
import com.etiya.rentACarSpring.businnes.request.CarRequest.DeleteCarRequest;
import com.etiya.rentACarSpring.businnes.request.CarRequest.UpdateCarRequest;
import com.etiya.rentACarSpring.core.utilities.adapter.findexScoreServiceAdapter.findexScoreService;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.dataAccess.abstracts.CarDao;
import com.etiya.rentACarSpring.entities.Car;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetail;
import com.etiya.rentACarSpring.entities.complexTypes.CarDetailForColorAndBrand;

@Service
public class CarManager implements CarService {

    private CarDao carDao;
    private ModelMapperService modelMapperService;
    private findexScoreService findexScoreService;
    private Environment environment;
    private LanguageWordService languageWordService;
    private CityService cityService;
    private BrandService brandService;
    private ColorService colorService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService, findexScoreService findexScoreService, Environment environment
            ,LanguageWordService languageWordService,CityService cityService, BrandService brandService, ColorService colorService) {
        super();
        this.carDao = carDao;
        this.modelMapperService = modelMapperService;
        this.findexScoreService = findexScoreService;
        this.environment = environment;
        this.languageWordService = languageWordService;
        this.cityService=cityService;
        this.colorService=colorService;
        this.brandService=brandService;

    }

    @Override
    public DataResult<List<CarSearchListDto>> getAll() {
        List<Car> result = this.carDao.findAll();
        List<CarSearchListDto> response = result.stream()
                .map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

        return new SuccesDataResult<List<CarSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarListed));
    }

    @Override
    public Result save(CreateCarRequest createCarRequest) {
        Result result = BusinnessRules.run( cityService.checkIfCityExists(createCarRequest.getCityId()),
                colorService.checkIfColorExists(createCarRequest.getColorId()),
                brandService.checkIfBrandExists(createCarRequest.getBrandId())
        );
        if (result != null) {
            return result;
        }
        Car car = modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setFindexScore(findexScoreService.sendCarFindexScore());
        this.carDao.save(car);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CarAdded));
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) {
        Result result = BusinnessRules.run( checkCarExistsInGallery(updateCarRequest.getCarId()),
                cityService.checkIfCityExists(updateCarRequest.getCityId()),
                colorService.checkIfColorExists(updateCarRequest.getColorId()),
                brandService.checkIfBrandExists(updateCarRequest.getBrandId())
        );
        if (result != null) {
            return result;
        }
        Car car = modelMapperService.forRequest().map(updateCarRequest, Car.class);
        car.setFindexScore(this.carDao.getById(updateCarRequest.getCarId()).getFindexScore());
        this.carDao.save(car);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CarUpdated));
    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) {
        Result result = BusinnessRules.run( checkCarExistsInGallery(deleteCarRequest.getCarId())
        );
        if (result != null) {
            return result;
        }
        this.carDao.deleteById(deleteCarRequest.getCarId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CarDeleted));
    }

    @Override
    public DataResult<List<CarSearchListDto>> getByDailyPrice(Integer dailyPrice) {

        List<CarSearchListDto> response = this.carDao.getByDailyPrice(dailyPrice).stream()
                .map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

        return new SuccesDataResult<List<CarSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarByDailyPriceListed));
    }

    @Override
    public DataResult<List<CarDetail>> getCarWithBrandAndColorDetails() {

        List<CarDetail> response = this.carDao.getCarWithBrandAndColorDetails();

        return new SuccesDataResult<List<CarDetail>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarDetailedListed));
    }

    @Override
    public DataResult<List<CarDetailForColorAndBrand>> getCarByColor(Integer colorId) {
        List<CarDetailForColorAndBrand> response = this.carDao.getCarDetailByColor(colorId).stream()
                .map(car -> modelMapperService.forDto().map(car, CarDetailForColorAndBrand.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<CarDetailForColorAndBrand>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarByColorIdListed));
    }

    @Override
    public DataResult<List<CarDetailForColorAndBrand>> getCarByBrand(Integer brandId) {
        List<CarDetailForColorAndBrand> response = this.carDao.getCarDetailByBrand(brandId).stream()
                .map(car -> modelMapperService.forDto().map(car, CarDetailForColorAndBrand.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<CarDetailForColorAndBrand>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarByBrandIdListed));
    }

    @Override
    public DataResult<List<CarDetailForColorAndBrand>> getCarByCarId(Integer carId) {
        List<CarDetailForColorAndBrand> response = this.carDao.getCarDetailByCarId(carId).stream()
                .map(car -> modelMapperService.forDto().map(car, CarDetailForColorAndBrand.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<CarDetailForColorAndBrand>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarByCarIdListed));
    }

    @Override
    public DataResult<Car> getById(int carId) {
        return new SuccesDataResult<Car>(this.carDao.getById(carId));
    }

    @Override
    public DataResult<List<CarSearchListDto>> getWithoutMaintenanceOfCar() {
        List<CarSearchListDto> result = this.carDao.getAllWithoutMaintenanceOfCar();
        List<CarSearchListDto> response = result.stream()
                .map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());
        return new SuccesDataResult<List<CarSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarWithoutMaintenanceListed));
    }

    @Override
    public DataResult<List<CarSearchListDto>> getCarByCityId(Integer cityId) {
        List<CarSearchListDto> response = this.carDao.getByCity_CityId(cityId).stream()
                .map(car -> modelMapperService.forDto().map(car, CarSearchListDto.class)).collect(Collectors.toList());

        return new SuccesDataResult<List<CarSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.CarByCityIdListed));
    }

    @Override
    public Result checkCarExistsInGallery(int id) {
        if (!this.carDao.existsById(id)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CarNotFound));
        }
        return new SuccesResult();
    }

}