package com.latchezar.javaspringassignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.latchezar.javaspringassignment.model.WordRelation;

@Repository
public interface WordRelationRepository extends JpaRepository<WordRelation, Long> {

    boolean existsByWordOneAndWordTwo(String wordOne, String wordTwo);

    List<WordRelation> findAllByRelation(String filter);

    List<WordRelation> findAllByWordOneOrWordTwo(String wordOne, String wordTwo);

    List<WordRelation> findAllByIdNotInAndWordOne(List<Long> ids, String word);

    List<WordRelation> findAllByIdNotInAndWordTwo(List<Long> ids, String word);

}
