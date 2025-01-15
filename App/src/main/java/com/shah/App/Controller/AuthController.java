package com.shah.App.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shah.App.Security.JwtService;
import com.shah.App.model.JwtRequest;
import com.shah.App.model.JwtResponse;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostMapping("/generate")
	public ResponseEntity<JwtResponse> getToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		// assuming request dont have token
		String username = jwtRequest.getUsername();
		String password = jwtRequest.getPassword();
		this.authenticate(username, password);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		final String token = jwtUtil.generateToken(userDetails);
		return new ResponseEntity<JwtResponse>(new JwtResponse(token,userDetails), HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
