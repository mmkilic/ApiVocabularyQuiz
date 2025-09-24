package com.mmkilic.education.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mmkilic.education.entity.AppUser;
import com.mmkilic.education.service.AppUserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AppUserController {
    private final AppUserService userService;

    @GetMapping("/{id}")
    public AppUser getById(@PathVariable long id) { 
    	return userService.getById(id); 
    }
    
    /*
    @GetMapping
    public List<AppUser> all() { return userService.getAll(); }
    */
    
    @GetMapping("/name")
    public AppUser getByName(@RequestParam String name){
    	return userService.getByName(name);
    }

    @PostMapping("/login")
    public AppUser login(@RequestBody AppUser user) { return userService.login(user); }
    
}