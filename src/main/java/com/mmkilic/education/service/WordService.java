package com.mmkilic.education.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mmkilic.education.entity.Word;
import com.mmkilic.education.repository.WordRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordService {
	private final WordRepository wordRepo;
	
	public Word getWordById(long id) {
		return wordRepo.findById(id).orElseThrow();
	}
	
	public List<Word> getAll(){
		return wordRepo.findAll();
	}
	
	public Word save(Word w) {
		return wordRepo.save(w);
	}
}
