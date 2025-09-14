package com.mmkilic.education.dto;

import lombok.Builder;

@Builder
public record AnswerResponse(
		long quizId, 
		long qaPairId,
		String question,
		String answer,
		String correctAnswer,
		boolean correct
		) {}
