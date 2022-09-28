package com.example.backend.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.backend.validator.UniqueUserName;

@Data

public class UserCreateDTO {
	
	@NotNull(message = "userName can not be null")
	@Size(min=4,max=20,message = "should be at least 4 max 20 characters")
	@UniqueUserName
	private String userName;
	
	@NotNull(message = "firstName can not be null")
	@Size(min=2,max=20,message = "should be at least 2 max 20 characters")
	private String firstName;
	
	@NotNull(message = "firstName can not be null")
	@Size(min=2,max=20,message = "should be at least 2 max 20 characters")
	private String lastName;
	

}
