package com.example.backend.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.example.backend.DTO.UserCreateDTO;
import com.example.backend.DTO.UserUpdateDTO;
import com.example.backend.DTO.UserViewDTO;

public interface UserService {
		
	UserViewDTO getByUserId(Long id);

	UserViewDTO createUser(UserCreateDTO userCreateDTO);

	List<UserViewDTO> getAll();

	UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) throws Exception;

	void deleteUser(Long id) throws Exception;

	List<UserViewDTO> pagination(Pageable pageable);
}
