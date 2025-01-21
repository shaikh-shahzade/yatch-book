package com.shah.App.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shah.App.model.User;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
	
		
		// TODO Auto-generated method stub
		String requestToken = request.getHeader("Authorization");
		String token , username=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer"))
		{
			
			token = requestToken.substring(7);
			try {
				username = jwtService.getUsernameFromToken(token);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if(username!=null&&SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UserDetails userDetails= userDetailsService.loadUserByUsername(username);
				if(this.jwtService.validateToken(token,(User) userDetails))
				{

					UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken( userDetails,null,null);
					upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(upat);
							
				}
				
			}
		}
		filterChain.doFilter(request, response);
	}

	
	
}