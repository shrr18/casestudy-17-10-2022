package com.gl.caseStudy4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.caseStudy4.bean.MyUsers;


@Repository
public interface UserRepository extends JpaRepository<MyUsers, String> {
	
	Optional<MyUsers> findUserByUsername(String username);
}
