package com.pioslomiany.VisLegis.views.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.views.entity.CustomerCaseJournalView;

@Repository
public class CustomerCaseJournalViewDAOImpl {
	
	@Autowired
	EntityManager entityManger;

	public List<CustomerCaseJournalView> getAllJournals() {
		Session session = entityManger.unwrap(Session.class);
		
		Query<CustomerCaseJournalView> query = session.createQuery("FROM CustomerCaseJournalView AS c ORDER BY c.letterDate DESC",
																	CustomerCaseJournalView.class);
			
		return query.getResultList();
	}
	
}
