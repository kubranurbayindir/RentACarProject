package com.etiya.rentACarSpring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rental_additional_services")
public class RentalAdditionalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_additional_service_id")
    private int rentalAdditionalServiceId;

    @ManyToOne
    @JoinColumn(name = "additional_service_id")
    private AdditionalService additionalService;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

}
