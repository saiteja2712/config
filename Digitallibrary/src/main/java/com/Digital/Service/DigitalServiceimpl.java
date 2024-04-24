package com.Digital.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Digital.Config.JwtProvider;
import com.Digital.Repository.DigitalRepo;
import com.Digital.entity.Roles;

@Service
public class DigitalServiceimpl implements Digitalinterface {

	@Autowired
	private DigitalRepo digitalRepo;
	@Override
	public Roles insert(Roles role) {
		// TODO Auto-generated method stub
		return digitalRepo.save(role);
	}
	@Override
	public Roles findUserByJwt(String jwt) {
		// TODO Auto-generated method stub
		String email=JwtProvider.getEmailFromJwtToken(jwt);
		Roles role=digitalRepo.findByEmail(email);
		return role;
	}
	

}
