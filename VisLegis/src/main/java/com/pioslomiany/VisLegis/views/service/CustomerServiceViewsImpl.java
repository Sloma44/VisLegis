package com.pioslomiany.VisLegis.views.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.VisLegis.views.dao.CustomerCaseCourtHearingViewDAOImpl;
import com.pioslomiany.VisLegis.views.dao.CustomerCaseIncomeViewDAOImpl;
import com.pioslomiany.VisLegis.views.dao.CustomerCaseJournalViewDAOImpl;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseCourtHearingView;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseIncomeView;
import com.pioslomiany.VisLegis.views.entity.CustomerCaseJournalView;

@Service
public class CustomerServiceViewsImpl implements CustomerServiceViews {

	@Autowired
	CustomerCaseJournalViewDAOImpl customerCaseJournalViewDAO;
	
	@Autowired
	CustomerCaseCourtHearingViewDAOImpl customerCaseCourtHearingViewDAO;
	
	@Autowired
	CustomerCaseIncomeViewDAOImpl customerCaseIncomeViewDAO;
	
	@Override
	@Transactional
	public List<CustomerCaseJournalView> getAllJournals() {
		return customerCaseJournalViewDAO.getAllJournals();
	}

	@Override
	@Transactional
	public List<CustomerCaseCourtHearingView> getAllCourtHearings() {
		return customerCaseCourtHearingViewDAO.getAllCourtHearings();
	}

	@Override
	@Transactional
	public List<CustomerCaseIncomeView> getAllIncomes() {
		return customerCaseIncomeViewDAO.getAllIncomes();
	}
}
