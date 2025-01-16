package com.shah.App.Service;

import java.security.Principal;
import java.util.List;

import com.shah.App.model.User;

public interface UserService {

public User createUser(User user ,Boolean isHostAccount) throws Exception;
	
	public List<User> getAllUser( Principal principal);
	
	public User getUserById(Long id , Principal principal);
	
	public User updateUser(Long id , User user , Principal principal);
	
	public User deleteUser(Long id , Principal principal);
}
