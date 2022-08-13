package com.pioslomiany.VisLegis.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.VisLegis.security.dao.RoleDAOImpl;
import com.pioslomiany.VisLegis.security.dao.UserDAOImpl;
import com.pioslomiany.VisLegis.security.dao.UserRoleViewDAOImpl;
import com.pioslomiany.VisLegis.security.entity.Role;
import com.pioslomiany.VisLegis.security.entity.User;
import com.pioslomiany.VisLegis.security.entity.UserRoleView;

@Service
public class SecurityService {

	@Autowired
	UserDAOImpl userDAOImpl;
	
	@Autowired
	RoleDAOImpl roleDAOImpl;
	
	@Autowired
	UserRoleViewDAOImpl userRoleViewDAOImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public List<UserRoleView> getUsersAndRoles() {
		return userRoleViewDAOImpl.getUsersAndRoles();
	}
	
	@Transactional
	public void changeUserStatusByName(String userName) {
		userDAOImpl.changeUserStatusByName(userName);
	}

	@Transactional
	public void saveUserAndRole(User theUser, Role theRole) {
		theRole.setUserName(theUser.getUserName());
		theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
		userDAOImpl.saveUser(theUser);
		roleDAOImpl.saveRole(theRole);
	}
	
	// There is no relation between enties 
	// so the operation of deleting must be in orded
	// if the role is deleted it returns true
	// if role is deleted the user can also be deleted
	// the role will not be deleted if has role of ADMIN
	// ADMIN role cannot be created or deleted from the application
	@Transactional
	public void deleteUser(String userName) {
		boolean isDeleted = roleDAOImpl.deleteRole(userName);
		if (isDeleted) {
			userDAOImpl.deleteUser(userName);			
		}
	}
	
	@Transactional
	public void saveRole(Role theRole) {
		roleDAOImpl.saveRole(theRole);
	}
	
}
