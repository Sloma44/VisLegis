package com.pioslomiany.VisLegis.security.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.security.entity.Role;
import com.pioslomiany.VisLegis.security.entity.User;

@Repository
public class UserDAOImpl {

	@Autowired
	EntityManager entityManager;
	
	public List<User> getUsers() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<User> query = session.createQuery("FROM User", User.class);
		
		return query.getResultList();
	}
	
	public User getUserByName(String userName) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(User.class, userName);
	}
	
	//
	public void changeUserStatusByName(String userName) {
		Session session = entityManager.unwrap(Session.class);
		
		User user = session.get(User.class, userName);
		Role role = session.get(Role.class, userName);
		
		String roleName = role.getRole();
		
		if(!roleName.equals("ROLE_ADMIN")) {
			if (user.getEnabled()==1) {
				user.setEnabled(0);
			} else {
				user.setEnabled(1);
			}			
			
			session.saveOrUpdate(user);
		}
	}
	
	public void saveUser(User theUser) {
		Session session = entityManager.unwrap(Session.class);
		
		session.save(theUser);
	}
	
	public void deleteUser(String userName) {
		Session session = entityManager.unwrap(Session.class);
		
		User user = session.get(User.class, userName);
		
		session.delete(user);
	}
	
}
