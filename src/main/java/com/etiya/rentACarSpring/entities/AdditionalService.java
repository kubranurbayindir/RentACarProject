package com.etiya.rentACarSpring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="additional_services")
public class AdditionalService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "additional_service_id")
    private int additionalServiceId;

    @Column(name = "additional_service_name")
    private String additionalServiceName;

    @Column(name = "additional_service_price")
    private int additionalServicePrice;

    @OneToMany(mappedBy = "additionalService")
    private List<RentalAdditionalService> rentalAdditionalServices;


}
