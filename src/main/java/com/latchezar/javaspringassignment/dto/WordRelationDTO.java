package com.latchezar.javaspringassignment.dto;


import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.latchezar.javaspringassignment.model.WordRelation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WordRelationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Words must not be empty!")
    private String wordOne;

    @NotBlank(message = "Words must not be empty!")
    private String wordTwo;

    @NotBlank(message = "Relation must not be empty!")
    private String relation;

    public WordRelationDTO(WordRelation wordRelation) {
        setId(wordRelation.getId());
        setWordOne(wordRelation.getWordOne());
        setWordTwo(wordRelation.getWordTwo());
        setRelation(wordRelation.getRelation());
    }

}
