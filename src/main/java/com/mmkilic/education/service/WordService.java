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
	
	public Word getById(long id) {
		return wordRepo.findById(id).orElseThrow();
	}
	
	public List<Word> getAll(){
		return wordRepo.findAll();
	}
	
	public List<Word> search(String search){
		return wordRepo.search(search);
	}
	
	public Word save(Word w) {
		if(w == null || w.getEnglish() == null || w.getTurkish() == null)
			throw new RuntimeException("Missing word!");
		
		w.setEnglish(w.getEnglish().toLowerCase().trim());
		w.setTurkish(w.getTurkish().toLowerCase().trim());
		
		if(w.getSentence() != null)
			w.setSentence(w.getSentence().toLowerCase().trim());
		
		if(w.getSynonym() != null)
			w.setSynonym(w.getSynonym().toLowerCase().trim());
		
		return wordRepo.save(w);
	}
}
