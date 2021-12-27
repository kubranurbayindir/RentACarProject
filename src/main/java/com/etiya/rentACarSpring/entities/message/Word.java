package com.etiya.rentACarSpring.entities.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wordId")
    private int wordId;

    @Column(name = "key")
    private String key;

    @OneToMany(mappedBy = "word")
    private List<LanguageWord> languageWord;



}
