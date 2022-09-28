package com.example.backend.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.backend.DTO.UserCreateDTO;
import com.example.backend.DTO.UserUpdateDTO;
import com.example.backend.DTO.UserViewDTO;
import com.example.backend.business.abstracts.UserService;
import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService{
	
	private final UserRepository userRepository;
	
	@Override
	public UserViewDTO getByUserId(Long id) {
		
		return UserViewDTO.of(userRepository.findById(id).orElseThrow());
	}

	@Override
	@Transactional
	public UserViewDTO createUser(UserCreateDTO userCreateDTO) {
		final User user = userRepository.save(new User(userCreateDTO.getUserName(),userCreateDTO.getFirstName(),userCreateDTO.getLastName()));
		return UserViewDTO.of(user);
	}

	@Override
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public List<UserViewDTO> getAll() {
		return userRepository.findAll().stream().map(UserViewDTO::of).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public UserViewDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) throws Exception {
		final User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException());
		user.setFirstName(userUpdateDTO.getFirstName());
		user.setLastName(userUpdateDTO.getLastName());
		
		final User userUpdatedUser = userRepository.save(user);
		return UserViewDTO.of(userUpdatedUser);
	}

	@Override
	@Transactional
	public void deleteUser(Long id) throws Exception {
		final User user = userRepository.findById(id).orElseThrow(()-> new NotFoundException());
		userRepository.deleteById(user.getId());
	}

	@Override
	@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
	public List<UserViewDTO> pagination(Pageable pageable) {
		return userRepository.findAll(pageable).stream().map(UserViewDTO::of).collect(Collectors.toList());
	}

}
