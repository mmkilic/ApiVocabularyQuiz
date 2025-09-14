package com.mmkilic.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmkilic.education.entity.QAPair;

@Repository
public interface QAPairRepository extends JpaRepository<QAPair, Long> {
	
}
