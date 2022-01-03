package com.etiya.rentACarSpring.businnes.concretes;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.dtos.ColorSearchListDto;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.entities.Brand;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.ColorService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.CreateColorRequest;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.DeleteColorRequest;
import com.etiya.rentACarSpring.businnes.request.ColorRequest.UpdateColorRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;

import com.etiya.rentACarSpring.dataAccess.abstracts.ColorDao;

import com.etiya.rentACarSpring.entities.Color;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {

    private ColorDao colorDao;
    private ModelMapperService modelMapperService;
    private Environment environment;
    private LanguageWordService languageWordService;

    @Autowired
    public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService, Environment environment, LanguageWordService languageWordService) {
        super();
        this.colorDao = colorDao;
        this.modelMapperService = modelMapperService;
        this.environment = environment;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<ColorSearchListDto>> getAll() {
        List<Color> result = this.colorDao.findAll();
        List<ColorSearchListDto> response = result.stream()
                .map(color -> modelMapperService.forDto().map(color, ColorSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<ColorSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.ColorListed));
    }

    @Override
    public Result save(CreateColorRequest createColorRequest) {
        Result result = BusinnessRules.run(checkColorNameDuplicated(createColorRequest.getColorName())
                );
        if (result != null) {
            return result;
        }
        Color color = modelMapperService.forRequest().map(createColorRequest, Color.class);
        this.colorDao.save(color);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.ColorAdded));
    }

    @Override
    public Result update(UpdateColorRequest updateColorRequest) {
        Result result = BusinnessRules.run(checkIfColorExists(updateColorRequest.getColorId()),
                checkColorNameDuplicated(updateColorRequest.getColorName())

        );
        if (result != null) {
            return result;
        }
        Color color = modelMapperService.forRequest().map(updateColorRequest, Color.class);
        this.colorDao.save(color);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.ColorUpdated));
    }

    @Override
    public Result delete(DeleteColorRequest deleteColorRequest) {
        Result result = BusinnessRules.run(checkIfColorExists(deleteColorRequest.getColorId())
        );
        if (result != null) {
            return result;
        }
        this.colorDao.deleteById(deleteColorRequest.getColorId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.ColorDeleted));
    }

    @Override
    public Result checkIfColorExists(int colorId) {
        if (!this.colorDao.existsById(colorId)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.ColorNotFound));
        }
        return new SuccesResult();
    }

    private Result checkColorNameDuplicated(String colorName) {
        Color color = this.colorDao.getColorByColorName(colorName);
        if (color != null) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.ColorNameDuplicated));
        }
        return new SuccesResult();
    }

}
