package com.pioslomiany.DDSProject.dao.views;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.views.CustomerCaseCourtHearingView;

@Repository
public class CustomerCaseCourtHearingViewDAOImpl {

	@Autowired
	EntityManager entityManager;
	
	public List<CustomerCaseCourtHearingView> getAllCourtHearings() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<CustomerCaseCourtHearingView> query = session.createQuery("FROM CustomerCaseCourtHearingView c ORDER BY c.hearingDate, c.hearingHour",
																		CustomerCaseCourtHearingView.class);
		
		return query.getResultList();
	}
	
}
