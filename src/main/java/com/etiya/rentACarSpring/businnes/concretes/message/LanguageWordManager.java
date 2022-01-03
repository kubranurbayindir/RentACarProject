package com.etiya.rentACarSpring.businnes.concretes.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageService;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.abstracts.message.WordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.message.LanguageWordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.CreateLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.DeleteLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.UpdateLanguageWordRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.message.LanguageWordDao;
import com.etiya.rentACarSpring.entities.message.Language;
import com.etiya.rentACarSpring.entities.message.LanguageWord;
import com.etiya.rentACarSpring.entities.message.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageWordManager implements LanguageWordService {
    @Value("${language}")
    private Integer languageId;
    @Value("${defaultLanguage}")
    private Integer defaultLanguageId;

    private LanguageWordDao languageWordDao;
    private ModelMapperService modelMapperService;
    private LanguageService languageService;
    private WordService wordService;
    private Environment environment;


    @Autowired
    public LanguageWordManager(LanguageWordDao languageWordDao, ModelMapperService modelMapperService
            , LanguageService languageService, WordService wordService, Environment environment) {
        this.languageWordDao = languageWordDao;
        this.modelMapperService = modelMapperService;
        this.languageService = languageService;
        this.wordService = wordService;
        this.environment=environment;
    }

    @Override
    public DataResult<List<LanguageWordSearchListDto>> getAll() {
        List<LanguageWord> result = this.languageWordDao.findAll();
        List<LanguageWordSearchListDto> response = result.stream()
                .map(languageWord -> modelMapperService.forDto().map(languageWord, LanguageWordSearchListDto.class)).collect(Collectors.toList());

        return new SuccesDataResult<List<LanguageWordSearchListDto>>(response, getByLanguageAndKeyId(Messages.LanguageWordListed));
    }

    @Override
    public Result save(CreateLanguageWordRequest createLanguageWordRequest) {
        LanguageWord languageWord = modelMapperService.forRequest().map(createLanguageWordRequest, LanguageWord.class);
        this.languageWordDao.save(languageWord);
        return new SuccesResult(getByLanguageAndKeyId(Messages.LanguageWordAdded));

    }

    @Override
    public Result update(UpdateLanguageWordRequest updateLanguageWordRequest) {
        Result result = BusinnessRules.run(checkIfLanguageWordExists(updateLanguageWordRequest.getId()));
        if (result != null) {
            return result;
        }
        LanguageWord languageWord = modelMapperService.forRequest().map(updateLanguageWordRequest, LanguageWord.class);
        this.languageWordDao.save(languageWord);
        return new SuccesResult(getByLanguageAndKeyId(Messages.LanguageWordUpdated));
    }

    @Override
    public Result delete(DeleteLanguageWordRequest deleteLanguageWordRequest) {
        Result result = BusinnessRules.run(checkIfLanguageWordExists(deleteLanguageWordRequest.getId()));
        if (result != null) {
            return result;
        }
        this.languageWordDao.deleteById(deleteLanguageWordRequest.getId());
        return new SuccesResult(getByLanguageAndKeyId(Messages.LanguageWordDeleted));
    }

    @Override
    public String getByLanguageAndKeyId(String key) {
        getDefaultLanguage();
        String messageContent=this.languageWordDao.getMessageByLanguageIdAndKey(key,this.languageId);
        if (!wordService.checkKeyExists(key).isSuccess()){
            return key;
        }
        if (messageContent!=null){
            return messageContent;
        }
        return this.languageWordDao.getMessageByLanguageIdAndKey(key,this.languageId);
    }

    private void getDefaultLanguage(){
        if(!this.languageService.checkLanguageExists(this.languageId).isSuccess()){
            this.languageId=this.defaultLanguageId;
        }
    }

    public Result checkIfLanguageWordExists(int languageWordId) {
        if (!this.languageWordDao.existsById(languageWordId)) {
            return new ErrorResult(getByLanguageAndKeyId(Messages.LanguageWordNotFound));
        }
        return new SuccesResult();
    }
}