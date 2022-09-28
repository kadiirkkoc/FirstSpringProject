package com.example.backend.repository;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.example.backend.model.User;


@Repository
public interface UserRepository extends JpaRepositoryImplementation<User, Long>{

	boolean existsUserByUserName(String userName);
	
}
