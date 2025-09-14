package com.mmkilic.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmkilic.education.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
}
