package com.etiya.rentACarSpring.core.utilities.adapter.posServiceAdapter;

import com.etiya.rentACarSpring.businnes.request.PosServiceRequest;
import com.etiya.rentACarSpring.core.utilities.adapter.fakeServices.PosService;
import org.springframework.stereotype.Service;

@Service
public class posSystemAdapter implements posSystemService {
    PosService fakePosService = new PosService();

    @Override
    public boolean withdraw(PosServiceRequest fakePosServiceRequest) {

        return this.fakePosService.checkPos(fakePosServiceRequest.getCardNumber(),
                fakePosServiceRequest.getCardHolderName(), fakePosServiceRequest.getExpirationDate(),
                fakePosServiceRequest.getCvv(), fakePosServiceRequest.getPrice());
    }
}

