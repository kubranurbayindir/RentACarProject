package com.etiya.rentACarSpring.businnes.abstracts.message;

import com.etiya.rentACarSpring.businnes.dtos.message.WordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.CreateWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.DeleteWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.UpdateWordRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface WordService {
    DataResult<List<WordSearchListDto>> getAll();
    Result save(CreateWordRequest createWordRequest);
    Result update(UpdateWordRequest updateWordRequest);
    Result delete(DeleteWordRequest deleteWordRequest);
    Result checkWordIdExists(int wordId);
    Result checkKeyExists(String key);
}
