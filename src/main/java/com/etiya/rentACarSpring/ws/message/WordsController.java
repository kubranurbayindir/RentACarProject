package com.etiya.rentACarSpring.ws.message;

import com.etiya.rentACarSpring.businnes.abstracts.message.WordService;
import com.etiya.rentACarSpring.businnes.dtos.message.WordSearchListDto;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.CreateWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.DeleteWordRequest;
import com.etiya.rentACarSpring.businnes.request.MessageRequest.WordRequest.UpdateWordRequest;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/word")
public class WordsController {
    private WordService wordService;
    @Autowired
    public WordsController(WordService wordService) {
        this.wordService = wordService;
    }
    @GetMapping("all")
    public DataResult<List<WordSearchListDto>> getAll() {
        return wordService.getAll();
    }

    @PostMapping("add")
    public Result add(@RequestBody @Valid CreateWordRequest createWordRequest) {
        return this.wordService.save(createWordRequest);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Valid UpdateWordRequest updateWordRequest) {
        return this.wordService.update(updateWordRequest);
    }

    @DeleteMapping("delete")
    public Result delete(@RequestBody @Valid DeleteWordRequest deleteWordRequest) {
        return this.wordService.delete(deleteWordRequest);
    }


}
