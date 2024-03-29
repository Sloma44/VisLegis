package com.pioslomiany.VisLegis.views.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.views.entity.CustomerCaseCourtHearingView;

@Repository
public class CustomerCaseCourtHearingViewDAOImpl {

	@Autowired
	EntityManager entityManager;
	
	public List<CustomerCaseCourtHearingView> getAllCourtHearings() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<CustomerCaseCourtHearingView> query = session.createQuery("FROM CustomerCaseCourtHearingView c ORDER BY c.hearingDate DESC, c.hearingHour DESC",
																		CustomerCaseCourtHearingView.class);
		
		return query.getResultList();
	}
	
}
