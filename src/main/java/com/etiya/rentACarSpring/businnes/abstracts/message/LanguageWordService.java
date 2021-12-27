package com.etiya.rentACarSpring.businnes.abstracts.message;

import com.etiya.rentACarSpring.businnes.dtos.message.LanguageWordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.CreateLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.DeleteLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.UpdateLanguageWordRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.core.env.Environment;

import java.util.List;

public interface LanguageWordService {
    DataResult<List<LanguageWordSearchListDto>> getAll();
    Result save(CreateLanguageWordRequest createLanguageWordRequest);
    Result update(UpdateLanguageWordRequest updateLanguageWordRequest);
    Result delete(DeleteLanguageWordRequest deleteLanguageWordRequest);

    String getByLanguageAndKeyId(int wordId, int language);
}
