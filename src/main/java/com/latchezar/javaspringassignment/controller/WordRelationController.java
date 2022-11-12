package com.latchezar.javaspringassignment.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.latchezar.javaspringassignment.common.Constants;
import com.latchezar.javaspringassignment.dto.WordRelationDTO;
import com.latchezar.javaspringassignment.service.WordRelationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.API_BASE + "/word-relations")
@RequiredArgsConstructor
public class WordRelationController {

    private final WordRelationService wordRelationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public WordRelationDTO createWordRelation(@Valid @RequestBody WordRelationDTO wordRelationDTO) {
        return wordRelationService.createWordRelation(wordRelationDTO);
    }

    @GetMapping
    public List<WordRelationDTO> listWordRelationEntries(@PathParam("filter") String filter, @PathParam("inverse") boolean inverse) {
        return wordRelationService.listWordRelationEntries(filter, inverse);
    }

    @GetMapping("/path")
    public String getRelationPath(@PathParam("source") String source, @PathParam("target") String target) {
        return wordRelationService.getRelationPath(source, target);
    }

}
