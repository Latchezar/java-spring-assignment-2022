package com.latchezar.javaspringassignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.latchezar.javaspringassignment.model.WordRelation;

@Repository
public interface WordRelationRepository extends JpaRepository<WordRelation, Long> {

    boolean existsByWordOneAndWordTwo(String wordOne, String wordTwo);

}
