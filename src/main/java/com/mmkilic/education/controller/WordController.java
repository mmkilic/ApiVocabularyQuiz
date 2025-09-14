package com.mmkilic.education.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmkilic.education.entity.Word;
import com.mmkilic.education.service.WordService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordController {
    private final WordService wordService;

    @GetMapping
    public List<Word> all() { return wordService.getAll(); }

    @PostMapping
    public Word save(@RequestBody Word w) { return wordService.save(w); }
    
}