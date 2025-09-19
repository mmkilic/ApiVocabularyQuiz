package com.mmkilic.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mmkilic.education.entity.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
	@Query("SELECT w FROM Word w WHERE w.english like %:search% or w.turkish like %:search%")
	List<Word> search(@Param("search") String search);
}
