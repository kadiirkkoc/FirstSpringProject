package com.example.backend.API;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.DTO.UserCreateDTO;
import com.example.backend.DTO.UserUpdateDTO;
import com.example.backend.DTO.UserViewDTO;
import com.example.backend.Result.Result;
import com.example.backend.business.abstracts.UserService;
import com.example.backend.model.User;



@RestController
@RequestMapping("/api")
public class UserController {
	
	UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/v1/user/{id}")
	public ResponseEntity<UserViewDTO> getByUserId(@PathVariable Long id){
			
		return ResponseEntity.ok(userService.getByUserId(id));
	}
	
	@PostMapping("v1/user")
	public ResponseEntity<?> createUser( @Valid @RequestBody UserCreateDTO userCreateDTO){
		userService.createUser(userCreateDTO);
		return ResponseEntity.ok(new Result("olusturuldu"));
	}
	
	@GetMapping("/v1/user/getAll")
	public ResponseEntity<List<UserViewDTO>> getAll(User user){
		return ResponseEntity.ok(userService.getAll());

	}

	
	@PutMapping("/v1/user/{id}")
	public ResponseEntity<UserViewDTO> updateUser(@PathVariable("id") Long id,@RequestBody UserUpdateDTO userUpdateDTO) throws Exception{
		final UserViewDTO user = userService.updateUser(id,userUpdateDTO);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/v1/user/{id}")
	public ResponseEntity<Result> deleteUser(@PathVariable("id")Long id) throws Exception {
		userService.deleteUser(id);
		return ResponseEntity.ok(new Result("user silindi"));
	}
	
	@GetMapping("/v1/user/pagination")
	public ResponseEntity<List<UserViewDTO>> pagination(@RequestParam int page,@RequestParam int pageSize){
		Pageable pageable = PageRequest.of(page, pageSize);
		final List<UserViewDTO> users = userService.pagination(pageable);
		return ResponseEntity.ok(users);
		
		
	}
	
	
}
