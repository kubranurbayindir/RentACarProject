package com.etiya.rentACarSpring.businnes.concretes.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageService;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.message.LanguageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.CreateLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.DeleteLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.message.LanguageDao;
import com.etiya.rentACarSpring.entities.message.Language;
import com.etiya.rentACarSpring.entities.message.LanguageWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageManager implements LanguageService {

    private LanguageDao languageDao;
    private ModelMapperService modelMapperService;
    private Environment environment;
    private LanguageWordService languageWordService;

    @Autowired
    public LanguageManager(LanguageDao languageDao, ModelMapperService modelMapperService, Environment environment, @Lazy LanguageWordService languageWordService) {
        this.languageDao = languageDao;
        this.modelMapperService=modelMapperService;
        this.environment=environment;
        this.languageWordService=languageWordService;
    }

    @Override
    public DataResult<List<LanguageSearchListDto>> getAll() {
        List<Language> result = this.languageDao.findAll();
        List<LanguageSearchListDto> response = result.stream()
                .map(language -> modelMapperService.forDto().map(language, LanguageSearchListDto.class)).collect(Collectors.toList());

        return new SuccesDataResult<List<LanguageSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.LanguageListed,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result save(CreateLanguageRequest createLanguageRequest) {
        Language language = modelMapperService.forRequest().map(createLanguageRequest, Language.class);
        this.languageDao.save(language);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.LanguageAdded,Integer.parseInt(environment.getProperty("language"))));

    }

    @Override
    public Result update(UpdateLanguageRequest updateLanguageRequest) {
        Language language = modelMapperService.forRequest().map(updateLanguageRequest, Language.class);
        this.languageDao.save(language);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.LanguageUpdated,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result delete(DeleteLanguageRequest deleteLanguageRequest) {

        this.languageDao.deleteById(deleteLanguageRequest.getLanguageId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.LanguageDeleted,Integer.parseInt(environment.getProperty("language"))));
    }

    public Result checkLanguageExists(int languageId) {
        if (this.languageDao.existsById(languageId)) {
            return new SuccesResult();
        } else if (Integer.parseInt(this.environment.getProperty("language")) != languageId) {
            languageId=1;
            return new SuccesResult();
        } else {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.LanguageNotFound,Integer.parseInt(environment.getProperty("language"))));
        }
    }

}
