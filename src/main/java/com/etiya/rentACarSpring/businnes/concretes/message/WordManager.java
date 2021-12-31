package com.etiya.rentACarSpring.businnes.concretes.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.abstracts.message.WordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.message.WordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.CreateWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.DeleteWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.UpdateWordRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.*;
import com.etiya.rentACarSpring.dataAccess.abstracts.message.WordDao;
import com.etiya.rentACarSpring.entities.message.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordManager implements WordService {

    private WordDao wordDao;
    private ModelMapperService modelMapperService;
    private Environment environment;
    private LanguageWordService languageWordService;

    @Autowired
    public WordManager(WordDao wordDao, ModelMapperService modelMapperService, Environment environment,
                       @Lazy LanguageWordService languageWordService) {
        this.wordDao = wordDao;
        this.modelMapperService = modelMapperService;
        this.environment = environment;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<WordSearchListDto>> getAll() {
        List<Word> result = this.wordDao.findAll();
        List<WordSearchListDto> response = result.stream()
                .map(word -> modelMapperService.forDto().map(word, WordSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<WordSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.WordListed,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result save(CreateWordRequest createWordRequest) {
        Result result = BusinnessRules.run(ifKeyDuplicated(createWordRequest.getKey()));
        if (result != null) {
            return result;
        }
        Word word = modelMapperService.forRequest().map(createWordRequest, Word.class);
        this.wordDao.save(word);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.WordAdded,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result update(UpdateWordRequest updateWordRequest) {
        Word word = modelMapperService.forRequest().map(updateWordRequest, Word.class);
        this.wordDao.save(word);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.WordUpdated,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result delete(DeleteWordRequest deleteWordRequest) {
        this.wordDao.deleteById(deleteWordRequest.getWordId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.WordDeleted,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result checkWordIdExists(int wordId) {
        if (this.wordDao.existsById(wordId)) {
            return new SuccesResult();
        }
        return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.WordNotFound,Integer.parseInt(environment.getProperty("language"))));
    }

    @Override
    public Result checkKeyExists(String key) {
        Word word = this.wordDao.getByKey(key);
        if (word != null) {
            return new SuccesResult();
        }
        return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.KeyAlreadyExist,Integer.parseInt(environment.getProperty("language"))));
    }

    private Result ifKeyDuplicated(String key){
        Word word = this.wordDao.getByKey(key);
        if (word != null){
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.KeyDuplicated,Integer.parseInt(environment.getProperty("language"))));
        }
        return new SuccesResult();
    }
}




