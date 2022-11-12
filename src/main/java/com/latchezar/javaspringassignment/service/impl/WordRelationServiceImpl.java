package com.latchezar.javaspringassignment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        String wordOne = wordRelationDTO.getWordOne().trim().toLowerCase();
        String wordTwo = wordRelationDTO.getWordTwo().trim().toLowerCase();
        String relation = wordRelationDTO.getRelation().trim().toLowerCase();

        if (wordRelationRepository.existsByWordOneAndWordTwo(wordOne, wordTwo)
                || wordRelationRepository.existsByWordOneAndWordTwo(wordTwo, wordOne)) {
            throw new ServiceException(ErrorCode.WORD_RELATION_ALREADY_EXISTS);
        }

        WordRelation wordRelation = new WordRelation();
        wordRelation.setWordOne(wordOne);
        wordRelation.setWordTwo(wordTwo);
        wordRelation.setRelation(relation);

        return new WordRelationDTO(wordRelationRepository.save(wordRelation));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WordRelationDTO> listWordRelationEntries(String filter, boolean inverse) {
        List<WordRelation> wordRelations = filter != null && !filter.isEmpty() ?
                wordRelationRepository.findAllByRelation(filter) :
                wordRelationRepository.findAll();

        List<WordRelationDTO> result = wordRelations.stream().map(WordRelationDTO::new).collect(Collectors.toList());

        if (inverse) {
            result.addAll(wordRelations.stream().map(this::createInverseWordRelationDTO).collect(Collectors.toList()));
        }

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public String getRelationPath(String source, String target) {
        String lowerCaseSource = source.trim().toLowerCase();
        String lowerCaseTarget = target.trim().toLowerCase();

        List<WordRelation> sources = wordRelationRepository.findAllByWordOneOrWordTwo(lowerCaseSource, lowerCaseSource);

        for (WordRelation wordRelation : sources) {
            String current = wordRelation.getWordOne().equals(lowerCaseSource) ? wordRelation.getWordOne() :
                    wordRelation.getWordTwo();
            String next = wordRelation.getWordOne().equals(lowerCaseSource) ? wordRelation.getWordTwo() :
                    wordRelation.getWordOne();

            StringBuilder path = new StringBuilder(current)
                    .append(" ==(")
                    .append(wordRelation.getRelation())
                    .append(")=> ")
                    .append(next);

            path.append(findPathToTarget(lowerCaseTarget, next, List.of(wordRelation.getId())));

            if (isTargetFound(lowerCaseTarget, path.toString())) {
                return path.toString();
            }
        }

        throw new ServiceException(ErrorCode.NO_PATH_AVAILABLE);
    }

    private String findPathToTarget(String lowerCaseTarget, String current, List<Long> forbiddenIds) {
        List<WordRelation> relations = wordRelationRepository.findAllByIdNotInAndWordOne(forbiddenIds, current);
        relations.addAll(wordRelationRepository.findAllByIdNotInAndWordTwo(forbiddenIds, current));

        for (WordRelation relation : relations) {
            String next = relation.getWordOne().equals(current) ? relation.getWordTwo() : relation.getWordOne();
            StringBuilder path = new StringBuilder();
            path.append(" ==(")
                .append(relation.getRelation())
                .append(")=> ")
                .append(next);
            if (next.equals(lowerCaseTarget)) {
                return path.toString();
            }
            List<Long> ids = new ArrayList<>(forbiddenIds);
            ids.add(relation.getId());
            path.append(findPathToTarget(lowerCaseTarget, next, ids));

            if (isTargetFound(lowerCaseTarget, path.toString())) {
                return path.toString();
            }
        }
        return "";
    }

    private boolean isTargetFound(String lowerCaseTarget, String path) {
        String[] parts = path.split(" ");
        return parts[parts.length - 1].equals(lowerCaseTarget);
    }

    private WordRelationDTO createInverseWordRelationDTO(WordRelation wordRelation) {
        WordRelationDTO wordRelationDTO = new WordRelationDTO(wordRelation);
        wordRelationDTO.setInverse(true);
        wordRelationDTO.setWordOne(wordRelation.getWordTwo());
        wordRelationDTO.setWordTwo(wordRelation.getWordOne());

        return wordRelationDTO;
    }
}
