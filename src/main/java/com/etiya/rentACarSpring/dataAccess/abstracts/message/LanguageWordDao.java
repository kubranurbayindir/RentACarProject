package com.etiya.rentACarSpring.dataAccess.abstracts.message;

import com.etiya.rentACarSpring.entities.complexTypes.CarDetailForColorAndBrand;
import com.etiya.rentACarSpring.entities.message.Language;
import com.etiya.rentACarSpring.entities.message.LanguageWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LanguageWordDao extends JpaRepository <LanguageWord, Integer>  {

    @Query(value = "select lw.translation from languages l inner join languages_word lw on l.language_id=lw.language_id\n" +
            "inner join words w on w.word_id=lw.word_id where w.key=:key and l.language_id=:languageId", nativeQuery = true)
    String getMessageByLanguageIdAndKey(String key,int languageId);



}


