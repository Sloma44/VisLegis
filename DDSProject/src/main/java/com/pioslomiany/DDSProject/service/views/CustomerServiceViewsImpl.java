package com.pioslomiany.DDSProject.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.dao.views.CustomerCaseCourtHearingViewDAOImpl;
import com.pioslomiany.DDSProject.dao.views.CustomerCaseIncomeViewDAOImpl;
import com.pioslomiany.DDSProject.dao.views.CustomerCaseJournalViewDAOImpl;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseCourtHearingView;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseIncomeView;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseJournalView;

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
