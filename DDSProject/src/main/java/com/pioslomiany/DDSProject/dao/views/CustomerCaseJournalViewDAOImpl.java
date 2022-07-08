package com.pioslomiany.DDSProject.dao.views;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.views.CustomerCaseJournalView;

@Repository
public class CustomerCaseJournalViewDAOImpl {
	
	@Autowired
	EntityManager entityManger;

	public List<CustomerCaseJournalView> getAll() {
		Session session = entityManger.unwrap(Session.class);
		
		Query<CustomerCaseJournalView> query = session.createQuery("FROM CustomerCaseJournalView AS c ORDER BY c.letterDate", CustomerCaseJournalView.class);
			
		return query.getResultList();
	}
	
}
