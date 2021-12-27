package com.etiya.rentACarSpring.dataAccess.abstracts.message;

import com.etiya.rentACarSpring.entities.message.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordDao extends JpaRepository<Word, Integer>  {
}
