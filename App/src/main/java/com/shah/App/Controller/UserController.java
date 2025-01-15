package com.shah.App.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.shah.App.Service.UserService;
import com.shah.App.model.User;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins ="*")
public class UserController {

	@Autowired
	UserService  userService;
	
	@GetMapping("v1/{id}")
	public User getUserById(@PathVariable(name = "id") Long id, Principal principal) {
		return this.userService.getUserById(id,principal);
	}


	@GetMapping("v1")
	public List<User> getAlluser(Principal principal) {

		return this.userService.getAllUser(principal);
	}

	@PostMapping("v1")
	public User createUser(
			@RequestBody User user,  
			@RequestParam(defaultValue = "false") boolean isHostAccount
			) throws Exception 
	{
		return this.userService.createUser(user, isHostAccount);
	}
	
	@PatchMapping("v1/{id}" )
	public User updateUser(
			
			@RequestPart(name = "user") User user,
			@PathVariable Long id,
			Principal principal
			
			
			)
	{
		return this.userService.updateUser(id,user,principal);
	}
	
	@DeleteMapping("v1/{id}")
	public User deleteUser(@PathVariable Long id, Principal principal)
	{
		return this.userService.deleteUser(id,principal);
	}
	
}