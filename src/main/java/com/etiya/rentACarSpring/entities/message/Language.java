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
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "languageId")
    private int languageId;

    @Column(name = "languageName")
    private String name;

    @OneToMany(mappedBy = "language")
    private List<LanguageWord> languageWord;
}
