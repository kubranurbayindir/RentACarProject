package com.etiya.rentACarSpring.businnes.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.etiya.rentACarSpring.businnes.abstracts.message.LanguageWordService;
import com.etiya.rentACarSpring.businnes.constants.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.etiya.rentACarSpring.businnes.abstracts.CreditCardService;
import com.etiya.rentACarSpring.businnes.dtos.CreditCardSearchListDto;
import com.etiya.rentACarSpring.businnes.request.CreditCardRequest.CreateCreditCardRequest;
import com.etiya.rentACarSpring.businnes.request.CreditCardRequest.DeleteCreditCardRequest;
import com.etiya.rentACarSpring.businnes.request.CreditCardRequest.UpdateCreditCardRequest;
import com.etiya.rentACarSpring.core.utilities.businnessRules.BusinnessRules;
import com.etiya.rentACarSpring.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACarSpring.core.utilities.results.DataResult;
import com.etiya.rentACarSpring.core.utilities.results.ErrorResult;
import com.etiya.rentACarSpring.core.utilities.results.Result;
import com.etiya.rentACarSpring.core.utilities.results.SuccesDataResult;
import com.etiya.rentACarSpring.core.utilities.results.SuccesResult;
import com.etiya.rentACarSpring.dataAccess.abstracts.CreditCardDao;
import com.etiya.rentACarSpring.entities.CreditCard;

@Service
public class CreditCardManager implements CreditCardService {
    private CreditCardDao creditCardDao;
    private ModelMapperService modelMapperService;
    private LanguageWordService languageWordService;

    @Autowired
    public CreditCardManager(CreditCardDao creditCardDao, ModelMapperService modelMapperService,
                             LanguageWordService languageWordService) {
        super();
        this.creditCardDao = creditCardDao;
        this.modelMapperService = modelMapperService;
        this.languageWordService = languageWordService;
    }

    @Override
    public DataResult<List<CreditCardSearchListDto>> getAll() {
        List<CreditCard> result = this.creditCardDao.findAll();
        List<CreditCardSearchListDto> response = result.stream()
                .map(creditCard -> modelMapperService.forDto().map(creditCard, CreditCardSearchListDto.class))
                .collect(Collectors.toList());
        return new SuccesDataResult<List<CreditCardSearchListDto>>(response, languageWordService.getByLanguageAndKeyId(Messages.CreditCardListed));
    }

    @Override
    public Result add(CreateCreditCardRequest createCreditCardRequest) {
        Result result = BusinnessRules.run(checkIfCreditCardFormatIsTrue(createCreditCardRequest.getCardNumber()),
                checkExistCardNumber(createCreditCardRequest.getCardNumber()),
                checkIfCreditCardCvvFormatIsTrue(createCreditCardRequest.getCvv()));
        if (result != null) {
            return result;
        }
        CreditCard creditCard = modelMapperService.forRequest().map(createCreditCardRequest, CreditCard.class);
        this.creditCardDao.save(creditCard);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CreditCardAdded));
    }

    @Override
    public Result update(UpdateCreditCardRequest updateCreditCardRequest) {
        Result result = BusinnessRules.run(checkIfCreditCardFormatIsTrue(updateCreditCardRequest.getCardNumber()),
                checkIfCreditCardCvvFormatIsTrue(updateCreditCardRequest.getCvv()),
                checkIfCardExists(updateCreditCardRequest.getCreditCardId()));
        if (result != null) {
            return result;
        }

        CreditCard creditCard = modelMapperService.forRequest().map(updateCreditCardRequest, CreditCard.class);
        this.creditCardDao.save(creditCard);
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CreditCardUpdated));
    }

    @Override
    public Result delete(DeleteCreditCardRequest deleteCreditCardRequest) {
        Result result = BusinnessRules.run(checkIfCardExists(deleteCreditCardRequest.getCreditCardId()));
        if (result != null) {
            return result;
        }
        this.creditCardDao.deleteById(deleteCreditCardRequest.getCreditCardId());
        return new SuccesResult(languageWordService.getByLanguageAndKeyId(Messages.CreditCardDeleted));
    }

    public Result checkIfCreditCardFormatIsTrue(String cardNumber) {

        String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" + "(?<mastercard>5[1-5][0-9]{14})|"
                + "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" + "(?<amex>3[47][0-9]{13})|"
                + "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" + "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cardNumber);
        if (!matcher.find()) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CreditCardFormatIsNotCorrect));
        }

        return new SuccesResult();

    }

    private Result checkExistCardNumber(String cardNumber) {

        if (this.creditCardDao.existsByCardNumber(cardNumber)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CreditCardAlreadyExist));
        }
        return new SuccesResult();
    }


    public Result checkIfCreditCardCvvFormatIsTrue(String cvv) {

        String regex = "^[0-9]{3,3}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cvv);

        if (!matcher.matches())
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CvvFormatIsNotCorrect));

        return new SuccesResult();
    }

    private Result checkIfCardExists(int creditCardId) {
        if (!this.creditCardDao.existsById(creditCardId)) {
            return new ErrorResult(languageWordService.getByLanguageAndKeyId(Messages.CreditCardNotFound));
        }
        return new SuccesResult();
    }
}
