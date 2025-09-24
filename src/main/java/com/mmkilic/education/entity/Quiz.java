package com.mmkilic.education.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private List<QAPair> qaPair = new ArrayList<>();

	@ManyToOne
	private AppUser user;
	
	
	
	public Quiz addQA(QAPair qa) {
		qaPair.add(qa);
		qa.setQuiz(this);
		return this;
	}
	
	public int getQuestionCount() {
		return qaPair.size() == 0 ? 0 : qaPair.size();
	}
	public int getCorrectAnswerCount() {
		return (int) qaPair.stream()
				.filter(qa -> qa.getWord().getTurkish().equalsIgnoreCase(qa.getAnswer()))
				.count();
	}
	public int getAnsweredQuestionCount() {
		return (int) qaPair.stream()
				.filter(qa -> qa.getAnswer() != null)
				.count();
	}
	public boolean isCompleted() {
		return getQuestionCount() == getAnsweredQuestionCount();
	}
	public int getSuccessionRatio() {
		return (int)Math.round(100 * (getCorrectAnswerCount() / (double) getQuestionCount()));
	}
}