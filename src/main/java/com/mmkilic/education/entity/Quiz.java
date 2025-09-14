package com.mmkilic.education.entity;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String quizName;
	
	@Builder.Default
	//@JsonIgnore
	@JsonManagedReference
	@OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
	//@OneToMany(mappedBy = "quiz")
	private java.util.List<QAPair> qaPair = new ArrayList<>();

	public Quiz addQA(QAPair qa) {
		qaPair.add(qa);
		qa.setQuiz(this);
		return this;
	}
	
	public int qaCount() {
		return qaPair.size() == 0 ? 0 : qaPair.size() + 1;
	}
	public int correctAnswerCount() {
		return (int) qaPair.stream()
				.filter(qa -> qa.getWord().getTurkish().equalsIgnoreCase(qa.getAnswer()))
				.count();
	}
	public int incorrectAnswerCount() {
		return qaCount() - correctAnswerCount();
	}
	public int successionRatio() {
		return (int)Math.round(100 * (correctAnswerCount() / (double) qaCount()));
	}
}