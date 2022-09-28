package com.example.backend.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
	
	private final UserRepository userRepository;
	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		
		
		
		return !userRepository.existsUserByUserName(userName);
	}

}
