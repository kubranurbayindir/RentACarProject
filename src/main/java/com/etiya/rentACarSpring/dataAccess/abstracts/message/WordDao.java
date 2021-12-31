package com.etiya.rentACarSpring.dataAccess.abstracts.message;

import com.etiya.rentACarSpring.entities.message.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordDao extends JpaRepository<Word, Integer>  {

    Word getByKey(String key);
}
