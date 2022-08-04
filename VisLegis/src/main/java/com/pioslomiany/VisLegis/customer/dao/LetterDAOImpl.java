package com.pioslomiany.VisLegis.customer.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.VisLegis.customer.entity.LawCase;
import com.pioslomiany.VisLegis.customer.entity.Letter;

@Repository
public class LetterDAOImpl {
	
	@Autowired
	EntityManager entityManager;

//	provide a full list of correspondence (letters) for the specific LawCase sorted by date
	public List<Letter> getLawCaseLetters(LawCase theLawCase) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Letter> query = session.createQuery("FROM Letter AS c WHERE c.lawCase = :lawCase ORDER BY c.letterDate DESC", Letter.class);
		query.setParameter("lawCase", theLawCase);
		
		return query.getResultList();
	}
	
	public void saveLetter(Letter theLetter) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theLetter);
	}

	public Letter getLetterById(int theLetterId) {
		Session session = entityManager.unwrap(Session.class);
		
		Letter theLetter = session.get(Letter.class, theLetterId);
		
		return theLetter;
	}
	
	public void deleteLetter(int theLetterId) {
		Session session = entityManager.unwrap(Session.class);
		
		Letter theLetter = session.get(Letter.class, theLetterId);
		
		session.delete(theLetter);
	}

}
