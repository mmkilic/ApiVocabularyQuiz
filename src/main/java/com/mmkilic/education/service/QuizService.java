package com.mmkilic.education.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mmkilic.education.dto.AnswerRequset;
import com.mmkilic.education.dto.AnswerResponse;
import com.mmkilic.education.dto.NewQuizRequset;
import com.mmkilic.education.dto.Scoreboard;
import com.mmkilic.education.entity.QAPair;
import com.mmkilic.education.entity.Quiz;
import com.mmkilic.education.entity.Word;
import com.mmkilic.education.repository.AppUserRepository;
import com.mmkilic.education.repository.QuizRepository;
import com.mmkilic.education.repository.WordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final WordRepository wordRepo;
    private final QuizRepository quizRepo;
    private final AppUserRepository userRepo;
    
    public Quiz getById(long id){
    	return quizRepo.findById(id).orElseThrow();
    }
    
    public List<Quiz> getAll(){
    	return quizRepo.findAll();
    }
    
    public List<Quiz> search(String search){
		return quizRepo.search(search);
	}
    
    public List<Quiz> getByUserName(String name){
    	return quizRepo.findByUserName(name);
    }
    
    public Quiz save(Quiz quiz) {
    	return quizRepo.save(quiz);
    }
    
    public Quiz startNew(NewQuizRequset req) {
    	var user = userRepo.findByName(req.userName()).orElseThrow();
    	var words = wordRepo.findAll();
    	Collections.shuffle(words);
    	
    	Quiz quiz = new Quiz();
    	quiz.setQuizName(req.quizName());
    	int questionNo = 1;
    	for (Word word : words) {
			quiz.addQA(QAPair.builder()
					.no(questionNo++)
					.word(word)
					.question(word.getEnglish())
					.build());
		}
    	quiz.setUser(user);
    	
    	return save(quiz);
    }
    
    public QAPair getNextQA(long id) {
    	var quiz = getById(id);
    	for (var qa : quiz.getQaPair()) {
			if(qa.getAnswer() == null)
				return qa;
		}
    	return new QAPair();
    }
    
    public AnswerResponse answer(AnswerRequset req) {
    	var quiz = getById(req.quizId());
    	
    	for (var qa : quiz.getQaPair()) {
			if(qa.getWord().getEnglish().equals(req.qaPair().getQuestion())) {
				qa.setAnswer(req.qaPair().getAnswer().trim());
				qa.setResult(qa.getWord().getTurkish().equalsIgnoreCase(qa.getAnswer()));
				save(quiz);
				return AnswerResponse.builder()
						.quizId(quiz.getId())
						.qaPairId(qa.getId())
						.no(qa.getNo())
						.question(qa.getQuestion())
						.answer(qa.getAnswer())
						.correctAnswer(qa.getWord().getTurkish())
						.english2English(qa.getWord().getEnglish2English())
						.synonym(qa.getWord().getSynonym())
						.sentence(qa.getWord().getSentence())
						.correct(qa.isResult())
						.build();
			}
		}
    	return null;
    }
    
    public Scoreboard score(AnswerRequset req) {
    	var quiz = getById(req.quizId());
    	var lastQA = quiz.getQaPair().get(quiz.getQaPair().size() - 1);
    	
    	if(!lastQA.getWord().getEnglish().equals(req.qaPair().getQuestion()))
    		throw new RuntimeException("Last QA has not been matched!");
    	
    	lastQA.setAnswer(req.qaPair().getAnswer());
		lastQA.setResult(lastQA.getWord().getTurkish().equalsIgnoreCase(lastQA.getAnswer()));
		quiz = save(quiz);
		
    	return Scoreboard.builder()
    			.questionCount(quiz.getQuestionCount())
    			.correctAnswerCount(quiz.getCorrectAnswerCount())
    			.answeredQuestionCount(quiz.getAnsweredQuestionCount())
    			.successionRatio(quiz.getSuccessionRatio())
    			.build();
    }
}