package com.etiya.rentACarSpring.entities.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "languagesWord")
public class LanguageWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "languageWordId")
    private int id;

    @Column(name = "translation")
    private String translation;

    @ManyToOne
    @JoinColumn(name = "languageId")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "wordId")
    private Word word;


}
