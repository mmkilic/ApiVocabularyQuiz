package com.mmkilic.education.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mmkilic.education.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	Optional<AppUser> findByName(String name);
	
	@Query("SELECT u FROM AppUser u WHERE u.name = :name and u.password = :password")
	Optional<AppUser> login(@Param("name") String name, @Param("password") String password);
}
