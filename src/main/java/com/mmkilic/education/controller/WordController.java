package com.mmkilic.education.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmkilic.education.entity.Word;
import com.mmkilic.education.service.WordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordController {
    private final WordService wordService;

    @GetMapping("/{id}")
    public Word getById(@PathVariable long id) { 
    	return wordService.getById(id); 
    }
    
    @GetMapping
    public List<Word> all() { return wordService.getAll(); }
    
    @GetMapping("/search")
    public List<Word> search(@RequestParam String search){
    	return wordService.search(search);
    }

    @PostMapping
    public Word save(@RequestBody Word w) { return wordService.save(w); }
    
}