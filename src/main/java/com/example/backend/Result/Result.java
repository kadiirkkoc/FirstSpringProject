package com.example.backend.Result;

import lombok.Data;

@Data
public class Result {
	
	private String message;
	
	
	public Result(String message) {
		this.message=message;
	}
	
	

}
