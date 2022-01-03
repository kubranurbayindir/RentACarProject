package com.etiya.rentACarSpring;

import java.util.HashMap;


import java.util.Map;
import java.util.NoSuchElementException;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.etiya.rentACarSpring.core.utilities.results.ErrorDataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityNotFoundException;

@SpringBootApplication
@EnableSwagger2
@RestControllerAdvice
public class RentACarSpringApplication {

    @Autowired
    private LanguageWordService languageWordService;

    public static void main(String[] args) {
        SpringApplication.run(RentACarSpringApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.etiya.rentACarSpring"))
                .build();
    }

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException excepiton) {
        Map<String, String> validationErrors = new HashMap<String, String>();

        for (FieldError fieldError : excepiton.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> error = new ErrorDataResult<Object>(validationErrors,
                this.languageWordService.getByLanguageAndKeyId(Messages.ValidationErrors));
        return error;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleNoSuchElementException(NoSuchElementException exception) {

        ErrorResult error = new ErrorResult( this.languageWordService.getByLanguageAndKeyId(Messages.NoSuchElementException));
        return error;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleEntityNotFoundException(EntityNotFoundException exception) {
        ErrorResult error = new ErrorResult( this.languageWordService.getByLanguageAndKeyId(Messages.EntityNotFoundException));
        return error;
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult EmptyResultDataAccessException(EmptyResultDataAccessException exception) {
        ErrorResult error = new ErrorResult( this.languageWordService.getByLanguageAndKeyId(Messages.EmptyResultDataAccessException));
        return error;

    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult EmptyResultDataAccessException(HttpMessageNotReadableException exception) {
        ErrorResult error = new ErrorResult( this.languageWordService.getByLanguageAndKeyId(Messages.HttpMessageNotReadableException));
        return error;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult DataIntegrityViolationException(DataIntegrityViolationException exception) {
        ErrorResult error = new ErrorResult( this.languageWordService.getByLanguageAndKeyId(Messages.DataIntegrityViolationException));
        return error;
    }
}
