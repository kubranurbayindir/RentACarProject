package com.etiya.rentACarSpring.core.utilities.adapter.posServiceAdapter;

import com.etiya.rentACarSpring.businnes.request.PosServiceRequest;
import com.etiya.rentACarSpring.fakeServices.PosService;
import org.springframework.stereotype.Service;

@Service
public class posSystemAdapter implements posSystemService {

    PosService fakePosService = new PosService();

    @Override
    public boolean checkPayment(PosServiceRequest posServiceRequest) {
        return fakePosService.checkCreditCardBalance(posServiceRequest.getCardNumber(),
                posServiceRequest.getCvv(),posServiceRequest.getPrice());
    }
}

