package com.etiya.rentACarSpring.entities.complexTypes;

import com.etiya.rentACarSpring.entities.message.Language;
import com.etiya.rentACarSpring.entities.message.LanguageWord;
import com.etiya.rentACarSpring.entities.message.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private int id;

    private String translation;

    private int languageId;

    private int wordId;

    private String name;

    private String key;

}
