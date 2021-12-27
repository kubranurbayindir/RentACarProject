package com.etiya.rentACarSpring.businnes.abstracts.message;

import com.etiya.rentACarSpring.businnes.dtos.message.LanguageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.CreateLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.DeleteLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;

import java.util.List;

public interface LanguageService {
    DataResult<List<LanguageSearchListDto>> getAll();
    Result save(CreateLanguageRequest createLanguageRequest);
    Result update(UpdateLanguageRequest updateLanguageRequest);
    Result delete(DeleteLanguageRequest deleteLanguageRequest);
}
