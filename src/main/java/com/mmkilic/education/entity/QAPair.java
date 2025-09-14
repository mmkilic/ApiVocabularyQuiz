package com.mmkilic.education.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QAPair {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int no;
	private String question;
	private String answer;
	private boolean result;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	private Word word;
	
	@JsonBackReference
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Quiz quiz;
}