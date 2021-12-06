package com.etiya.rentACarSpring.businnes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorparateCustomerSearchListDto {
    private String companyName;

    private String taxNumber;
}
