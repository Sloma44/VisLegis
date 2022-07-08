package com.pioslomiany.DDSProject.dao.views;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.views.CustomerCaseIncomeView;

@Repository
public class CustomerCaseIncomeViewDAOImpl {

	@Autowired
	EntityManager entityManager;
	
	public List<CustomerCaseIncomeView> getAllIncomes() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<CustomerCaseIncomeView> query = session.createQuery("FROM CustomerCaseIncomeView c ORDER BY c.incomeDate",
																	CustomerCaseIncomeView.class);
		
		return query.getResultList();
	}
	
}
