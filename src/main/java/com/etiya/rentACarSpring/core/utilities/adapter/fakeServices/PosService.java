package com.etiya.rentACarSpring.core.utilities.adapter.fakeServices;

public class PosService {

    public boolean checkPos(String cardNumber, String cardHolderName, String expirationDate, String cvv, double price) {

        double limit = 200;

        if (price <= limit) {

            return true;
        }

        return false;
    }
}
