package com.etiya.rentACarSpring.dataAccess.abstracts.message;

import com.etiya.rentACarSpring.entities.message.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language, Integer> {
}
