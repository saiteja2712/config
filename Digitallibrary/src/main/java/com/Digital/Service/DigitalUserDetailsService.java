package com.Digital.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Digital.Repository.DigitalRepo;
import com.Digital.entity.Roles;

@Service
public class DigitalUserDetailsService implements UserDetailsService
{
	@Autowired
	private DigitalRepo digitalRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Roles roll=digitalRepo.findByEmail(username);
		if(roll==null) {
			throw new UsernameNotFoundException("user not found" );
		}
		List<GrantedAuthority>authorities=new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(roll.getEmail(),roll.getPassword(), authorities);
				
		
	}
	

}
