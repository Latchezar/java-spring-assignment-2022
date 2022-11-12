package com.latchezar.javaspringassignment.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.latchezar.javaspringassignment.dto.WordRelationDTO;
import com.latchezar.javaspringassignment.exception.ServiceException;
import com.latchezar.javaspringassignment.exception.model.ErrorCode;
import com.latchezar.javaspringassignment.model.WordRelation;
import com.latchezar.javaspringassignment.repository.WordRelationRepository;
import com.latchezar.javaspringassignment.service.WordRelationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordRelationServiceImpl implements WordRelationService {

    private final WordRelationRepository wordRelationRepository;

    @Override
    @Transactional
    public WordRelationDTO createWordRelation(WordRelationDTO wordRelationDTO) {
        if (wordRelationRepository.existsByWordOneAndWordTwo(wordRelationDTO.getWordOne(), wordRelationDTO.getWordTwo())) {
            throw new ServiceException(ErrorCode.WORD_RELATION_ALREADY_EXISTS);
        }

        WordRelation wordRelation = new WordRelation();
        wordRelation.setWordOne(wordRelationDTO.getWordOne());
        wordRelation.setWordTwo(wordRelationDTO.getWordTwo());
        wordRelation.setRelation(wordRelationDTO.getRelation());

        return new WordRelationDTO(wordRelationRepository.save(wordRelation));
    }
}
