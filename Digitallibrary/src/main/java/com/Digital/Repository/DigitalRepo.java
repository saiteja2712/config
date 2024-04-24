package com.Digital.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Digital.entity.Roles;


@Repository
public interface DigitalRepo extends JpaRepository<Roles,Integer>
{
	public Roles findByEmail(String email);

}
