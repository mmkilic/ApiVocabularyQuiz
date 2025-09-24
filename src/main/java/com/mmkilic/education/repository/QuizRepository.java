package com.mmkilic.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mmkilic.education.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	@Query("SELECT q FROM Quiz q WHERE q.quizName like %:search%")
	List<Quiz> search(@Param("search") String search);
	
	@Query("SELECT q FROM Quiz q WHERE q.user.name = :name")
	List<Quiz> findByUserName(@Param("name") String name);
}
