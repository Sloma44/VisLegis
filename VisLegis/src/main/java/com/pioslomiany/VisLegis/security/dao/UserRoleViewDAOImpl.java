package com.pioslomiany.VisLegis.security.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.security.entity.UserRoleView;

@Repository
public class UserRoleViewDAOImpl {

	@Autowired
	EntityManager entityManager;
	
	/* Returns list of all users: userName, status (active / inactive), role */
	public List<UserRoleView> getUsersAndRoles() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<UserRoleView> query = session.createQuery("FROM UserRoleView", UserRoleView.class);
		
		return query.getResultList();
		
	}
	
}
