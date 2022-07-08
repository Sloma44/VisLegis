package com.pioslomiany.DDSProject.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.CourtHearing;
import com.pioslomiany.DDSProject.entity.LawCase;

@Repository
public class CourtHearingDAOImpl {
	
	@Autowired
	private EntityManager entityManager;
	
	public List<CourtHearing> getAllCaseCourtHearing(LawCase theLawCase) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<CourtHearing> query = session.createQuery("FROM CourtHearing c WHERE c.lawCase = :lawCase ORDER BY c.hearingDate, c.hearingHour",
																		CourtHearing.class);
		query.setParameter("lawCase", theLawCase);
		
		return query.getResultList();
	}

	public void saveCourtHearing(LawCase theLawCase, CourtHearing theCourtHearing) {
		Session session = entityManager.unwrap(Session.class);
		
		theCourtHearing.setLawCase(theLawCase);
		
		session.saveOrUpdate(theCourtHearing);
	}

	public CourtHearing getHearingById(int hearingId) {
		Session session = entityManager.unwrap(Session.class);
		
		CourtHearing theCourtHearing = session.get(CourtHearing.class, hearingId);
		
		return theCourtHearing;
	}

	public void deleteHearingById(int hearingId) {
		Session session = entityManager.unwrap(Session.class);
		
		CourtHearing theCourtHearing = session.get(CourtHearing.class, hearingId);
		
		session.delete(theCourtHearing);	
	}

}
