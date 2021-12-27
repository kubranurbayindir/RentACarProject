package com.etiya.rentACarSpring.businnes.concretes.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageService;
import com.etiya.rentACarSpring.businnes.dtos.message.LanguageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.CreateLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.DeleteLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.message.LanguageDao;
import com.etiya.rentACarSpring.entities.message.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageManager implements LanguageService {

    private LanguageDao languageDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public LanguageManager(LanguageDao languageDao,ModelMapperService modelMapperService) {
        this.languageDao = languageDao;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public DataResult<List<LanguageSearchListDto>> getAll() {
        List<Language> result = this.languageDao.findAll();
        List<LanguageSearchListDto> response = result.stream()
                .map(language -> modelMapperService.forDto().map(language, LanguageSearchListDto.class)).collect(Collectors.toList());

        return new SuccesDataResult<List<LanguageSearchListDto>>(response);
    }

    @Override
    public Result save(CreateLanguageRequest createLanguageRequest) {
        Language language = modelMapperService.forRequest().map(createLanguageRequest, Language.class);
        this.languageDao.save(language);
        return new SuccesResult();

    }

    @Override
    public Result update(UpdateLanguageRequest updateLanguageRequest) {
        Language language = modelMapperService.forRequest().map(updateLanguageRequest, Language.class);
        this.languageDao.save(language);
        return new SuccesResult();
    }

    @Override
    public Result delete(DeleteLanguageRequest deleteLanguageRequest) {

        this.languageDao.deleteById(deleteLanguageRequest.getLanguageId());
        return new SuccesResult();
    }
}
