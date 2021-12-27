package com.etiya.rentACarSpring.businnes.concretes.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.WordService;
import com.etiya.rentACarSpring.businnes.dtos.CitySearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.message.WordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.CreateWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.DeleteWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.UpdateWordRequest;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.message.WordDao;
import com.etiya.rentACarSpring.entities.message.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordManager implements WordService {

    private WordDao wordDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public WordManager(WordDao wordDao,ModelMapperService modelMapperService) {
        this.wordDao = wordDao;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public DataResult<List<WordSearchListDto>> getAll() {
        List<Word> result = this.wordDao.findAll();
        List<WordSearchListDto> response = result.stream()
                .map(word -> modelMapperService.forDto().map(word, WordSearchListDto.class))
                .collect(Collectors.toList());

        return new SuccesDataResult<List<WordSearchListDto>>(response);
    }

    @Override
    public Result save(CreateWordRequest createWordRequest) {
        Word word = modelMapperService.forRequest().map(createWordRequest, Word.class);
        this.wordDao.save(word);
        return new SuccesResult();
    }

    @Override
    public Result update(UpdateWordRequest updateWordRequest) {
        Word word = modelMapperService.forRequest().map(updateWordRequest, Word.class);
        this.wordDao.save(word);
        return new SuccesResult();
    }

    @Override
    public Result delete(DeleteWordRequest deleteWordRequest) {
        this.wordDao.deleteById(deleteWordRequest.getWordId());
        return new SuccesResult();
    }
}
