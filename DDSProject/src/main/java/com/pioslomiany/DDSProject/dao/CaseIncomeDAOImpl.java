package com.pioslomiany.DDSProject.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioslomiany.DDSProject.entity.CaseIncome;
import com.pioslomiany.DDSProject.entity.LawCase;

@Repository
public class CaseIncomeDAOImpl {
	
	@Autowired
	EntityManager entityManager;
	
//	provide a full list of incomes for the specific LawCase sorted by date
	public List<CaseIncome> getAllCaseIncomes(LawCase theLawCase) {
		Session session = entityManager.unwrap(Session.class);

		Query<CaseIncome> query = session.createQuery("FROM CaseIncome c WHERE c.lawCase = :lawCase ORDER BY c.incomeDate DESC", CaseIncome.class);
		query.setParameter("lawCase", theLawCase);
		
		return query.getResultList();
	}
	
	public void saveCaseIncome(CaseIncome theCaseIncome) {
		Session session = entityManager.unwrap(Session.class);
		
		session.saveOrUpdate(theCaseIncome);
	}

	public CaseIncome getCaseIncomeById(int incomeId) {
		Session session = entityManager.unwrap(Session.class);
		
		return session.get(CaseIncome.class, incomeId);
	}

	public void deleteCaseIncomeById(int incomeId) {
		Session session = entityManager.unwrap(Session.class);
		
		CaseIncome theCaseIncome = session.get(CaseIncome.class, incomeId);
		
		session.delete(theCaseIncome);
	}

	public void saveCaseIncome(LawCase theLawCase, CaseIncome theCaseIncome) {
		Session session = entityManager.unwrap(Session.class);
		
		theCaseIncome.setLawCase(theLawCase);
		
		session.saveOrUpdate(theCaseIncome);
	}
}
