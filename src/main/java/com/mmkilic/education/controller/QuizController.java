package com.mmkilic.education.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmkilic.education.dto.AnswerRequset;
import com.mmkilic.education.dto.AnswerResponse;
import com.mmkilic.education.dto.Scoreboard;
import com.mmkilic.education.entity.QAPair;
import com.mmkilic.education.entity.Quiz;
import com.mmkilic.education.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
public class QuizController {
    private final QuizService service;

    @GetMapping("/{id}")
    public Quiz getById(@PathVariable long id){
    	return service.getById(id);
    }
    
    @GetMapping
    public List<Quiz> getAll(){
    	return service.getAll();
    }
    
    @GetMapping("/search")
    public List<Quiz> search(@RequestParam String search){
    	return service.search(search);
    }
    
    @PostMapping("/start-new")
    public Quiz start(@RequestParam String quizName) { 
    	return service.startNew(quizName); 
    }

    @PostMapping("/next-qa")
    public QAPair getNextQA(@RequestParam long id) { 
    	return service.getNextQA(id); 
    }
    
    @PostMapping("/answer")
    public AnswerResponse answer(@RequestBody AnswerRequset req) { 
    	return service.answer(req); 
    }
    
    @PostMapping("/score")
    public Scoreboard score(@RequestBody AnswerRequset req) { 
    	return service.score(req); 
    }
    
}