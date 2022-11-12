package com.latchezar.javaspringassignment.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.latchezar.javaspringassignment.common.Constants;
import com.latchezar.javaspringassignment.dto.WordRelationDTO;
import com.latchezar.javaspringassignment.service.WordRelationService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping(Constants.API_BASE + "/word-relations")
@RequiredArgsConstructor
public class WordRelationController {

    private final WordRelationService wordRelationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public WordRelationDTO createWordRelation(@Valid WordRelationDTO wordRelationDTO) {
        return wordRelationService.createWordRelation(wordRelationDTO);
    }

}
