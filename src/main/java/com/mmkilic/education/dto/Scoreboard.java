package com.mmkilic.education.dto;

import lombok.Builder;

@Builder
public record Scoreboard(
		int questionCount, 
		int correctAnswerCount, 
		int answeredQuestionCount, 
		int successionRatio
		) {}
