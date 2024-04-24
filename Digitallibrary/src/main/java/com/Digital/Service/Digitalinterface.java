package com.Digital.Service;

import com.Digital.entity.Roles;

public interface Digitalinterface {
	public Roles insert(Roles role);
	public Roles findUserByJwt(String jwt);

}
