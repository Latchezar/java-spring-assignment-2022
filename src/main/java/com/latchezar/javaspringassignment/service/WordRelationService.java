package com.latchezar.javaspringassignment.service;

import java.util.List;

import com.latchezar.javaspringassignment.dto.WordRelationDTO;

public interface WordRelationService {

    WordRelationDTO createWordRelation(WordRelationDTO wordRelationDTO);

    List<WordRelationDTO> listAllWordRelationEntries();
}
