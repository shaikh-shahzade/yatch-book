package com.shah.App.Service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shah.App.Service.UserService;
import com.shah.App.model.User;
import com.shah.App.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user,Boolean isHostAccount) throws Exception {

		Boolean localUser = this.userRepository.findByUsername(user.getUsername()).isEmpty();
		if(!localUser)
			throw new Exception("User Already exists");
		
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		
		user = this.userRepository.save(user);
		return user;
	}

	@Override
	public List<User> getAllUser(Principal principal) {
		checkAccess(0l,principal);
		return (List<User>) userRepository.findAll();
	
	}

	@Override
	public User getUserById(Long id , Principal principal) {
		checkAccess(id,principal);
		User user = userRepository.findById(id).get();
		return user;
	}

	@Override
	public User updateUser(Long id, User user , Principal principal) {
		
		checkAccess(id, principal);
		
		
		User userResult = userRepository.findById(id).orElseThrow();

		
		
		if(user.getFullname()!=null)
			userResult.setFullname(user.getFullname());
		
		if(user.getPassword()!=null)
			userResult.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		if(user.getUsername()!=null)
			userResult.setUsername(user.getUsername());
		
		return userRepository.save(userResult);
	}

	private void checkAccess(Long id, Principal principal) {
		User principal_user = this.userRepository.findByUsername(principal.getName()).orElseThrow();
		
		boolean isHost = true;
		if(isHost||principal_user.getId().equals(id)) return;
		
	}
	

	@Override
	public User deleteUser(Long id , Principal principal) {
		checkAccess(id, principal);
		
		//User userResult = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "User ID", id));
		System.out.println("deletedfwd");
		userRepository.deleteById(id);
		return null;
	}


}
