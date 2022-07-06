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

	public List<Letter> getLetters() {
		Session session = entityManager.unwrap(Session.class);
		
		Query<Letter> query = session.createQuery("FROM CorrespondenceJournal AS c ORDER BY c.letterDate", Letter.class);
		
		List<Letter> correspondenceJournalList = query.getResultList();
		
		return correspondenceJournalList;
	}

	public void saveLetter(LawCase theLawCase, Letter theLetter) {
		Session session = entityManager.unwrap(Session.class);
		
		theLetter.setLawCase(theLawCase);
		
		session.saveOrUpdate(theLetter);
	}

	public Letter getLetterById(int letterId) {
		Session session = entityManager.unwrap(Session.class);
		
		Letter theLetter = session.get(Letter.class, letterId);
		
		return theLetter;
	}
	
	

}
