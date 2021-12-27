package com.etiya.rentACarSpring.businnes.concretes.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.dtos.message.LanguageWordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.CreateLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.DeleteLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.UpdateLanguageWordRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.message.LanguageWordDao;
import com.etiya.rentACarSpring.entities.message.LanguageWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageWordManager implements LanguageWordService {

    private LanguageWordDao languageWordDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public LanguageWordManager(LanguageWordDao languageWordDao,ModelMapperService modelMapperService) {
        this.languageWordDao = languageWordDao;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public DataResult<List<LanguageWordSearchListDto>> getAll() {
        List<LanguageWord> result = this.languageWordDao.findAll();
        List<LanguageWordSearchListDto> response = result.stream()
                .map(languageWord -> modelMapperService.forDto().map(languageWord, LanguageWordSearchListDto.class)).collect(Collectors.toList());

        return new SuccesDataResult<List<LanguageWordSearchListDto>>(response);
    }

    @Override
    public Result save(CreateLanguageWordRequest createLanguageWordRequest) {
        LanguageWord languageWord = modelMapperService.forRequest().map(createLanguageWordRequest, LanguageWord.class);
        this.languageWordDao.save(languageWord);
        return new SuccesResult();

    }

    @Override
    public Result update(UpdateLanguageWordRequest updateLanguageWordRequest) {
        LanguageWord languageWord = modelMapperService.forRequest().map(updateLanguageWordRequest, LanguageWord.class);
        this.languageWordDao.save(languageWord);
        return new SuccesResult();
    }

    @Override
    public Result delete(DeleteLanguageWordRequest deleteLanguageWordRequest) {

        this.languageWordDao.deleteById(deleteLanguageWordRequest.getId());
        return new SuccesResult();
    }

    @Override
    public String getByLanguageAndKeyId(int wordId, int language) {
       return this.languageWordDao.getMessageByLanguageIdAndKey(wordId,language);

    }
}
