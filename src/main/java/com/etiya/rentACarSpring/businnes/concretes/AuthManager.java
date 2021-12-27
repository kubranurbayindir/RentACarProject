package com.etiya.rentACarSpring.businnes.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.AuthService;
import com.etiya.rentACarSpring.businnes.abstracts.CorparateCustomerService;
import com.etiya.rentACarSpring.businnes.abstracts.IndividualCustomerService;
import com.etiya.rentACarSpring.businnes.abstracts.UserService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.CorparateRegisterRequest;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.IndividualRegisterRequest;
import com.etiya.rentACarSpring.businnes.request.AuthRequest.LoginRequest;
import com.etiya.rentACarSpring.businnes.request.CorparateCustomerRequest.CreateCorparateRequest;
import com.etiya.rentACarSpring.businnes.request.IndividualCustomerRequest.CreateIndividualCustomerRequest;

import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.core.utilities.adapter.findexScoreServiceAdapter.findexScoreService;

@Service
public class AuthManager implements AuthService {

    private UserService userService;
    private IndividualCustomerService individualCustomerService;
    private CorparateCustomerService corparateCustomerService;
    private ModelMapperService modelMapperService;
    private findexScoreService findexScoreService;

    @Autowired
    public AuthManager(UserService userService, IndividualCustomerService individualCustomerService,
                       CorparateCustomerService corparateCustomerService, ModelMapperService modelMapperService,
                       findexScoreService findexScoreService) {
        super();
        this.userService = userService;
        this.individualCustomerService = individualCustomerService;
        this.corparateCustomerService = corparateCustomerService;
        this.modelMapperService = modelMapperService;
        this.findexScoreService = findexScoreService;

    }

    @Override
    public Result individualRegister(IndividualRegisterRequest individualRegisterRequest) {
        Result result = BusinnessRules.run(checkEmailIfExists(individualRegisterRequest.getEmail()));

        if (result != null) {
            return result;
        }

        CreateIndividualCustomerRequest crateCreateIndividualCustomerRequest = modelMapperService.forRequest()
                .map(individualRegisterRequest, CreateIndividualCustomerRequest.class);

        crateCreateIndividualCustomerRequest.setFindexScore(findexScoreService.getIndividualFindexScore(individualRegisterRequest.getIdentityNumber()));
        this.individualCustomerService.save(crateCreateIndividualCustomerRequest);

        return new SuccesResult(Messages.individualRegister);
    }

    @Override
    public Result corparateRegister(CorparateRegisterRequest corparateRegisterRequest) {
        Result result = BusinnessRules.run(checkEmailIfExists(corparateRegisterRequest.getEmail()));

        if (result != null) {
            return result;
        }
        CreateCorparateRequest createCorparateRequest = modelMapperService.forRequest().map(corparateRegisterRequest,
                CreateCorparateRequest.class);
        createCorparateRequest.setFindexScore(findexScoreService.getCorparateFindexScore(corparateRegisterRequest.getTaxNumber()));
        this.corparateCustomerService.add(createCorparateRequest);
        return new SuccesResult(Messages.corparateRegister);
    }

    @Override
    public Result login(LoginRequest loginRequest) {
        var result = BusinnessRules.run(checkCustomerEmailIsTrue(loginRequest), checkCustomerPasswordIsTrue(loginRequest));

        if (result != null) {
            return result;
        }

        return new SuccesResult(Messages.login);
    }

    @Override
    public Result checkCustomerEmailIsTrue(LoginRequest loginRequest) {
        if (this.userService.existByEmail(loginRequest.getEmail()).isSuccess()) {
            return new ErrorResult(Messages.mailDontFind);
        }
        return new SuccesResult();
    }

    private Result checkCustomerPasswordIsTrue(LoginRequest loginRequest) {

        if (checkCustomerEmailIsTrue(loginRequest).isSuccess()) {

            if (!this.userService.getByEmail(loginRequest.getEmail()).getData().getPassword()
                    .equals(loginRequest.getPassword())) {
                return new ErrorResult("Hatalı Şifre Girdiniz!");
            }
        }
        return new SuccesResult();
    }

    private Result checkEmailIfExists(String email) {
        if (!this.userService.existByEmail(email).isSuccess()) {
            return new ErrorResult("Mail Adresi Zaten bulunmaktadır.");
        }
        return new SuccesResult();
    }

}

