package com.latchezar.javaspringassignment.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Only alphabetical characters and spaces are allowed")
    private String wordOne;

    @NotBlank(message = "Words must not be empty!")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Only alphabetical characters and spaces are allowed")
    private String wordTwo;

    @NotBlank(message = "Relation must not be empty!")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Only alphabetical characters and spaces are allowed")
    private String relation;

    private Boolean inverse;

    public WordRelationDTO(WordRelation wordRelation) {
        setId(wordRelation.getId());
        setWordOne(wordRelation.getWordOne());
        setWordTwo(wordRelation.getWordTwo());
        setRelation(wordRelation.getRelation());
        setInverse(Boolean.FALSE);
    }

}
