package com.etiya.rentACarSpring.ws.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageService;
import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.abstracts.message.WordService;
import com.etiya.rentACarSpring.businnes.dtos.message.LanguageSearchListDto;
import com.etiya.rentACarSpring.businnes.dtos.message.LanguageWordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.CreateLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.DeleteLanguageWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageWordRequest.UpdateLanguageWordRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/languageWord")
public class LanguageWordsController {
    private LanguageWordService languageWordService;
    @Autowired
    public LanguageWordsController(LanguageWordService languageWordService) {
        this.languageWordService = languageWordService;
    }
    @GetMapping("all")
    public DataResult<List<LanguageWordSearchListDto>> getAll() {
        return languageWordService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateLanguageWordRequest createLanguageWordRequest) {
        return this.languageWordService.save(createLanguageWordRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateLanguageWordRequest updateLanguageWordRequest) {
        return this.languageWordService.update(updateLanguageWordRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteLanguageWordRequest deleteLanguageWordRequest) {
        return this.languageWordService.delete(deleteLanguageWordRequest);
    }

}

