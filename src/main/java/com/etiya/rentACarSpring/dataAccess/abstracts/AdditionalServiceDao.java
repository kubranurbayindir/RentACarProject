package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {
    AdditionalService getByAdditionalServiceName(String additionalServiceName);
}
