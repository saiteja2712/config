package com.Digital.Controller;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Digital.Config.JwtProvider;
import com.Digital.Repository.DigitalRepo;
import com.Digital.Service.DigitalUserDetailsService;
import com.Digital.Service.Digitalinterface;
import com.Digital.entity.Roles;
import com.Digital.loginRequest.LoginRequest;

@RestController

public class DigitalController {
	
	@Autowired
	private Digitalinterface service;
	@Autowired
	private DigitalRepo digitalrepo;
	@Autowired
	private DigitalUserDetailsService digitaluserdetailsservice;
	@PostMapping("/insert")
	public void insert(@RequestBody Roles role)
	{
		service.insert(role);
	}
	@PostMapping("/login")
		public ResponseEntity<String>Signin(@RequestBody LoginRequest loginRequest)
		{
			UserDetails u=digitaluserdetailsservice.loadUserByUsername(loginRequest.getEmail());
			Roles roll=digitalrepo.findByEmail(loginRequest.getEmail());
			if(roll==null)
			{
				throw new UsernameNotFoundException("user not found");
			}
				Authentication authentication=authenticate(loginRequest.getEmail(),loginRequest.getPassword());
				String token=JwtProvider.generateToken(authentication);
				roll.setToken(token);
				digitalrepo.save(roll);
		
			return  ResponseEntity.ok(token);
	}
	private Authentication authenticate(String email, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails=digitaluserdetailsservice.loadUserByUsername(email);
		if(userDetails==null)
		{
			throw new BadCredentialsException("Invalid username");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
}
