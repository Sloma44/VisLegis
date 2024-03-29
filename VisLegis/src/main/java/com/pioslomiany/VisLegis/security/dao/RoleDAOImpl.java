package com.pioslomiany.VisLegis.security.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.security.entity.Role;

@Repository
public class RoleDAOImpl {

	@Autowired
	EntityManager entityManger;
	
	public void saveRole(Role theRole) {
		Session session = entityManger.unwrap(Session.class);
		
		session.save(theRole);
	}
	
	/*
	 * If the role is not an ADMIN it can be deleted. If returned value is true it
	 * means that user can be deleted. If false -> the user cannot be deleted
	 * because it is an Admin
	 */
	public boolean deleteRole(String userName) {
		Session session = entityManger.unwrap(Session.class);
		
		Role role = session.get(Role.class, userName);
		
		String roleName = role.getRole();
		
		if(!roleName.equals("ROLE_ADMIN")) {
			session.delete(role);
			return true;
		}
			
		return false;
	}
	
}
