package com.mmkilic.education.dto;

import lombok.Builder;

@Builder
public record AnswerResponse(
		long quizId, 
		long qaPairId,
		int no,
		String question,
		String answer,
		String correctAnswer,
		String english2English,
		String synonym,
		String sentence,
		boolean correct
		) {}
