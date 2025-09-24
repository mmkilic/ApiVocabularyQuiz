package com.mmkilic.education.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mmkilic.education.entity.AppUser;
import com.mmkilic.education.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService {
	private final AppUserRepository userRepo;
	
	public AppUser getById(long id) {
		return userRepo.findById(id).orElseThrow();
	}
	
	public List<AppUser> getAll(){
		return userRepo.findAll();
	}
	
	public AppUser getByName(String name) {
		return userRepo.findByName(name).orElse(null);
	}
	
	public AppUser login(AppUser user) {
		return userRepo.login(user.getName(), user.getPassword()).orElse(null);
	}
}
