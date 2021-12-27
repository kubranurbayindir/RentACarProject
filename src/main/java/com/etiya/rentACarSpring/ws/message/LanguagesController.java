package com.etiya.rentACarSpring.ws.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageService;
import com.etiya.rentACarSpring.businnes.dtos.message.LanguageSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.CreateLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.DeleteLanguageRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.LanguageRequest.UpdateLanguageRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/language")
public class LanguagesController {
    private LanguageService languageService;
    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }
    @GetMapping("all")
    public DataResult<List<LanguageSearchListDto>> getAll() {
        return languageService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateLanguageRequest createLanguageRequest) {
        return this.languageService.save(createLanguageRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateLanguageRequest updateLanguageRequest) {
        return this.languageService.update(updateLanguageRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteLanguageRequest deleteLanguageRequest) {
        return this.languageService.delete(deleteLanguageRequest);
    }


}
