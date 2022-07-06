package com.pioslomiany.DDSProject.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioslomiany.DDSProject.dao.views.CustomerCaseJournalViewDAOImpl;
import com.pioslomiany.DDSProject.entity.views.CustomerCaseJournalView;

@Service
public class CustomerServiceViewsImpl implements CustomerServiceViews {

	@Autowired
	CustomerCaseJournalViewDAOImpl customerCaseJournalViewDAO;
	
	@Override
	@Transactional
	public List<CustomerCaseJournalView> getAll() {
		return customerCaseJournalViewDAO.getAll();
	}

}
