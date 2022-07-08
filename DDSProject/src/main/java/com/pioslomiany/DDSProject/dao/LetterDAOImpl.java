package com.pioslomiany.DDSProject.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.Letter;
import com.pioslomiany.DDSProject.entity.LawCase;

@Repository
public class LetterDAOImpl {
	
	@Autowired
	EntityManager entityManager;

	public List<Letter> getLetters(LawCase theLawCase) {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Letter> query = session.createQuery("FROM Letter AS c WHERE c.lawCase = :lawCase ORDER BY c.letterDate", Letter.class);
		query.setParameter("lawCase", theLawCase);
		
		return query.getResultList();
	}

	public void saveLetter(LawCase theLawCase, Letter theLetter) {
		Session session = entityManager.unwrap(Session.class);
		
		theLetter.setLawCase(theLawCase);
		
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
